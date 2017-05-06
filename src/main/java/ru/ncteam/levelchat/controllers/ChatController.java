package ru.ncteam.levelchat.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
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
import ru.ncteam.levelchat.dao.ChatDAO;
import ru.ncteam.levelchat.dao.MessageDAO;
import ru.ncteam.levelchat.dao.UserInfoDAO;
import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.entity.Message;
import ru.ncteam.levelchat.listener.ChatPublisher;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/demoChat", method = RequestMethod.GET)
    public ModelAndView demo() {
        Message message = new Message();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        List<Message> messages = messageDAO.getAll();
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("demoChat");
        return modelAndView;
    }


    @RequestMapping(value = "/chats/chat/{chatId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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

    @RequestMapping(value = "/chats/chat/{chatId}", method = RequestMethod.POST)
    @ResponseBody
    protected void doPost(@ModelAttribute Message message, @PathVariable Long chatId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setChat(chatDAO.getEntityById(chatId));
        message.setUserInfo(userInfoDAO.getUserInfoByLogin(user.getUsername()));
        publisher.sendMessage(message);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    protected ModelAndView selectChat() {
        ModelAndView modelAndView = new ModelAndView();
        List<Chat> chats = chatDAO.getAllChatsByLogin();
        modelAndView.setViewName("selectChat");
        modelAndView.addObject("chats", chats);
        return modelAndView;
    }

    @RequestMapping(value = "/chats/{idChat}", method = RequestMethod.GET)
    protected ModelAndView gotoChat(@PathVariable Long idChat) {
        ModelAndView modelAndView = new ModelAndView();
        List<Chat> chats = chatDAO.getAllChatsByLogin();
        for (Chat chat : chats) {
            if (chat.getChatId() == idChat) {
                List<Message> messages = messageDAO.allMessagesByChatId(idChat);
                modelAndView.setViewName("demoChat");
                modelAndView.addObject("messages", messages);
                modelAndView.addObject("chatId", idChat);
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/chats");
        return modelAndView;
    }

    @RequestMapping(value = "/chats/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        return util.uploadFile(file);
    }

    @Async
    @EventListener
    void handle(Message event) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(event);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonInString);
        json.put("username", event.getUserInfo().getLogin());

        messageDAO.create(event);
        ConcurrentLinkedQueue<DeferredResult<String>> results = deferredResultMap.get(event.getChat().getChatId());
        for (DeferredResult<String> result : results) {
            result.setResult(json.toString());
            results.poll();
        }
    }
}