package ru.ncteam.levelchat.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.ncteam.levelchat.dao.*;
import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.entity.Message;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.listener.ChatPublisher;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class ChatController {
    @Autowired
    private ChatPublisher publisher;

    @Autowired
    private HashMap<Long, ConcurrentLinkedQueue<DeferredResult<String>>> deferredResultMap;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private ApplicationUtil util;

    @Autowired
    private UserDataDAO userDataDAO;

    @Autowired
    private UserChatDAO userChatDAO;

    @RequestMapping(value = "/chats/{chatId}/chat", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    DeferredResult<String> chatGet(@PathVariable Long chatId) {
        DeferredResult<String> result = new DeferredResult<>();
        ConcurrentLinkedQueue<DeferredResult<String>> deferredResults = deferredResultMap.get(chatId);
        if (deferredResults == null) {
            deferredResults = new ConcurrentLinkedQueue<>();
        }
        deferredResults.add(result);
        deferredResultMap.put(chatId, deferredResults);
        return result;

    }

    @RequestMapping(value = "/chats/{chatId}/read", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String setRead(@PathVariable Long chatId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userChatDAO.setReadableForUser(user.getUsername(),chatId, false);
        return "success";

    }

    @RequestMapping(value = "/chats/{chatId}/chat", method = RequestMethod.POST)
    @ResponseBody
    protected void doPost(@RequestBody String request,
                          @PathVariable Long chatId) throws JsonProcessingException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(request);
        Long dataId = (Long) json.get("dataId");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message message = gson.fromJson(gson.toJson(json.get("message")), Message.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userChatDAO.setReadable(chatId, true);
        message.setChat(chatDAO.getEntityById(chatId));
        message.setUserInfo(userInfoDAO.getUserInfoByLogin(user.getUsername()));
        if (dataId != -1)
            message.setUserData(userDataDAO.getEntityById(dataId));

        publisher.sendMessage(message);
    }

    @RequestMapping(value = "/chats/{idChat}", method = RequestMethod.GET)
    protected String gotoChat(@PathVariable Long idChat,
                                    Map<String, Object> map) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
        for (Chat chat : chats) {
            if (chat.getChatId() == idChat) {
                List<Message> messages = messageDAO.allMessagesByChatIdWithInfo(idChat);
                map.put("messages", messages);
                map.put("chatId", idChat);
                UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
                if(userInfo.getPhoto_ava()==null)
                {
                    userInfo.setPhoto_ava("photo/ava.png");
                }
                map.put("userInfo", userInfo);
                return "messages";
            }
        }
        return "redirect:/chats";
    }

    @RequestMapping(value = "/chats/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        String link = util.uploadFile(file);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
        Long dataId = userDataDAO.create(link, userInfo);
        JSONObject object = new JSONObject();
        object.put("dataId", dataId);
        object.put("dataLink", link);

        return object.toJSONString();
    }

    @Async
    @EventListener
    void handle(Message event) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(event);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonInString);
        //теперь нам нужно достать из json userInfo, чтобы не отправлять клиенту лишней информации
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        UserInfo userInfo = gson.fromJson(gson.toJson(json.get("userInfo")), UserInfo.class);
        userInfo.setPassword("");//не отправляем клиенту пароль
        //теперь необходимо вставить userInfo обратно в json
        if(userInfo.getPhoto_ava()==null)
        {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        jsonInString = mapper.writeValueAsString(userInfo);
        json.put("userInfo", parser.parse(jsonInString));

        json.put("username", event.getUserInfo().getLogin());
        if (event.getUserData() != null) {
            json.put("link", event.getUserData().getDataLink());
        }

        messageDAO.create(event);
        ConcurrentLinkedQueue<DeferredResult<String>> results = deferredResultMap.get(event.getChat().getChatId());
        for (DeferredResult<String> result : results) {
            result.setResult(json.toString());
            results.poll();
        }
    }
}