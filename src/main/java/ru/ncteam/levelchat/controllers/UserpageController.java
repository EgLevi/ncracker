package ru.ncteam.levelchat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncteam.levelchat.dao.ChatDAO;
import ru.ncteam.levelchat.dao.UserInfoDAO;
import ru.ncteam.levelchat.entity.*;
import ru.ncteam.levelchat.service.UserLogService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
public class UserpageController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserLogService userLogService;


    @RequestMapping(value = {"/userpage", "/"})
    public String userPage(Map<String, Object> map) {
        SecurityContextHolder.getContext().getAuthentication();
        Authentication a;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("userInfo", userInfo);
        userLogService.putDashboard(userInfo.getUser_id());
        List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
        map.put("chats", chats);
//        TODO:проверь, не вылетает ли ошибка если уже есть такой интерес.
        return "userpage";
    }


    @RequestMapping(value = "/userpage", method = RequestMethod.POST)
    @ResponseBody
    public List<DashboardUrl> getUrlDashBoard(@RequestBody Long userid){

        return null;
    }


    @RequestMapping(value = "/search")
    public String getSearchPage() {
        return "search";
    }

    @RequestMapping(value = "/search/getCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryInterest> getCategories() {
        return userLogService.getAllCategory();
    }

    @RequestMapping(value = "/search/getInterests/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Interests> getInterestsByNameForSearch(@PathVariable String categoryName) {
        return userLogService.getInterestsByCatName(categoryName);
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String putInterestsForSearch(@RequestBody List<Interests> interests,
                                        BindingResult result) {

        userLogService.putInterestList(interests,userLogService.getId());
        return "success";
    }

    @RequestMapping(value = "/search/getUsersForChat",method = RequestMethod.GET)
    @ResponseBody
    public String getUsersForChat(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        userInfo.getUser_id();

        JSONObject object = new JSONObject();
        object.put("usersName",userLogService.getUsersChat("",",","",20,21,userLogService.getId(),userInfo.getUser_id()));

        return object.toJSONString();
    }

    @RequestMapping(value = "/edituserinfo", method = RequestMethod.GET)
    public String editInfo() {
        return "editInfo";
    }

    @RequestMapping(value = "/edituserinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserInfo(@RequestBody @Valid UserInfo userInfo,
                                              BindingResult result) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        if (result.hasErrors()) {
            String code;
            List<FieldError> listErrors = result.getFieldErrors();
            for (FieldError listError : listErrors) {
                code = listError.getCode();
                if (!code.equals("typeMismatch")) {
                    map.put(listError.getField() + "Error", listError.getDefaultMessage());
                } else {
                    map.put(listError.getField() + "Error", "Недопустимое значение");
                }
            }
            return map;
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userInfo.setSex(userInfo.getSex().substring(0, 1));
        userInfo.setLogin(user.getUsername());

        if (userLogService.updateUserInfo(userInfo).equals("success")) {
            return null;
        }
        map.put("DataBaseError", "DataBaseError");
        return map;
    }

    @RequestMapping(value = "/postregistrationPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadUserInfoPhoto(@RequestParam(value = "photo_ava", required = false) MultipartFile photo_ava) {
        if (!photo_ava.isEmpty()) {
            if (!(photo_ava.getContentType().equals("image/jpeg") || photo_ava.getContentType().equals("image/png"))) {
                return "wrong format of photo";
            }
            try {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserInfo userInfo = new UserInfo();
                userInfo.setLogin(user.getUsername());
                return userLogService.uploadUserInfoPhoto(userInfo, photo_ava);
            } catch (Exception e) {
                return "fail";
            }
        } else {
            return "failed your photo is empty";
        }
    }

    @RequestMapping(value = "/postregistrationPhoto/save", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfoPhoto(@RequestBody String relativePath, HttpServletRequest request) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserInfo userInfo = new UserInfo();
            userInfo.setLogin(user.getUsername());
            return userLogService.updateUserInfoPhoto(userInfo, relativePath);
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping(value = "/userPhoto", method = RequestMethod.GET)
    @ResponseBody
    public String getUserPhoto() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<UserData> data = userInfoDAO.getUserData(user.getUsername());
            Iterator<UserData> it = data.iterator();
            UserData userData;
            String link;
            while (it.hasNext()) {
                userData = it.next();
                link = userData.getDataLink();
                if (!link.contains(".jpg")) {
                    data.remove(userData);
                }
            }
            List<String> listLink = new CopyOnWriteArrayList<>();
            it = data.iterator();
            while (it.hasNext()) {
                listLink.add(it.next().getDataLink());
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(listLink);
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping("/chats")
    public String messages(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
        map.put("userInfo", userInfo);
        map.put("chats", chats);
        return "messages";
    }

}
