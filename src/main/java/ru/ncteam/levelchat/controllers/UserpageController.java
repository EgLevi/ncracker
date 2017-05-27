package ru.ncteam.levelchat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncteam.levelchat.dao.*;
import ru.ncteam.levelchat.entity.*;
import ru.ncteam.levelchat.service.UserLogService;
import ru.ncteam.levelchat.utils.ApplicationUtil;

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
    private ChatDAO chatDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private PhotoLibDAO photoLibDAO;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private ApplicationUtil util;

    @Autowired
    private LevelsDAO levelsDAO;

    @Autowired
    private UserChatDAO userChatDAO;


    @RequestMapping(value = {"/userpage", "/"})
    public String userPagecc(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("userInfo", userInfo);
        //userLogService.putDashboard(userInfo.getUser_id());
        return "userpage";
    }

	@RequestMapping(value = "/search")
	public String getSearchPage(Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
		UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		map.put("userInfo", userInfo);
		return "search";
	}

	@RequestMapping(value = "/chats")
	public String getChats(Map<String, Object> map)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
		UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		map.put("chats", chats);
		map.put("userInfo", userInfo);
		Map<Long,Boolean> mapReadUnread = userInfoDAO.getMapReadUnread(user.getUsername());
		map.put("mapReadUnread", mapReadUnread);
		return "chats";
	}

	@RequestMapping(value = "/myPhotos")
	public String getPhoto(Map<String, Object> map)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PhotoLib> photoLibs = userInfoDAO.getUserPhotos(user.getUsername());
		photoLibs.sort(PhotoLib::compareTo);
		UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		//map.put("photos", photoLibs);
		map.put("userInfo", userInfo);
		return "myPhoto";
	}


	@RequestMapping(value = "/myPhotos/getAllPhoto",method = RequestMethod.GET)
	@ResponseBody
	public List<PhotoLib> getPhoto()
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PhotoLib> photoLibs = userInfoDAO.getUserPhotos(user.getUsername());
		photoLibs.sort(PhotoLib::compareTo);
		return photoLibs;
	}



	@RequestMapping(value = "/addPhoto",method = RequestMethod.POST)
	@ResponseBody
	public String uploadPhoto(@RequestParam(value = "photo", required=false) MultipartFile photo) {
		if (!photo.isEmpty()) {
			if(!(photo.getContentType().equals("image/jpeg") || photo.getContentType().equals("image/png")))
			{
				return "fail, wrong format of photo";
			}
			try {
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				UserInfo userInfo = new UserInfo();
				userInfo.setLogin(user.getUsername());
				String str = util.uploadFile(photo);
				return str;
			} catch (Exception e) {
				return "fail";
			}
		} else {
			return "failed your photo is empty";
		}
	}

    @RequestMapping(value = "/userpage", method = RequestMethod.POST)
    @ResponseBody
    public List<DashboardUrl> getUrlDashBoard(@RequestBody Long userid) {


        return null;
    }


    @RequestMapping(value = "/search")
    public String getSearchPage(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("userInfo", userInfo);
        return "search";
    }

    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getAllUserMessages() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> messages = userInfoDAO.getUserMessages(user.getUsername());
        messages.sort(Message::compareTo);
        return messages;
    }





	/*@RequestMapping(value = "/search")
    public String getSearchPage(Map<String, Object> map) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
		UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		map.put("userInfo", userInfo);
		return "search";
	}*/

    @RequestMapping(value = "/chats")
    public String getChats(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
        UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("chats", chats);
        map.put("userInfo", userInfo);
        Map<Long, Boolean> mapReadUnread = userInfoDAO.getMapReadUnread(user.getUsername());
        map.put("mapReadUnread", mapReadUnread);
        return "chats";
    }

    @RequestMapping(value = "/myPhotos")
    public String getPhoto(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PhotoLib> photoLibs = userInfoDAO.getUserPhotos(user.getUsername());
        photoLibs.sort(PhotoLib::compareTo);
        UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("photos", photoLibs);
        map.put("userInfo", userInfo);
        return "myPhoto";
    }


    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(@RequestParam(value = "photo", required = false) MultipartFile photo) {
        if (!photo.isEmpty()) {
            if (!(photo.getContentType().equals("image/jpeg") || photo.getContentType().equals("image/png"))) {
                return "fail, wrong format of photo";
            }
            try {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserInfo userInfo = new UserInfo();
                userInfo.setLogin(user.getUsername());
                return util.uploadFile(photo);
            } catch (Exception e) {
                return "fail";
            }
        } else {
            return "failed your photo is empty";
        }
    }

    @RequestMapping(value = "/addPhoto/save", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfoPhoto(@RequestBody String relativePathPhoto) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserInfo userInfo = userInfoDAO.getUserInfoByLogin(user.getUsername());
            return photoLibDAO.create(relativePathPhoto, userInfo);
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping(value = "/myPhoto", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUserInfoPhoto(@RequestBody String relativePathPhoto) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return photoLibDAO.delete(relativePathPhoto, user.getUsername());
        } catch (Exception e) {
            return "fail";
        }
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
    public String putInterestsForSearch(@RequestBody ArrayList<Interests> interests,
                                        BindingResult result) {
        userLogService.putInterestList(interests, userLogService.getId());
        return "success";
    }

    @RequestMapping(value = "/search/getUsersForChat", method = RequestMethod.GET)
    @ResponseBody
    public String getUsersForChat() {

        JSONObject object = new JSONObject();
        object.put("usersName", userLogService.getUsersChat("", ",", "", 20, 21, userLogService.getId()));

        return object.toJSONString();
    }

    @RequestMapping(value = "/search/startchat", method = RequestMethod.POST)
    @ResponseBody
    public String createNewChat(@RequestBody JSONObject userLogin) {
        Chat chat = new Chat();
        UserChat userChat1 = new UserChat();
        UserChat userChat2 = new UserChat();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userInfoDAO.getUserInfoByLogin(userLogin.get("ulogin").toString()).getUser_id();

        UserInfo firstUser = userInfoDAO.getUserInfoByLogin(user.getUsername());
        UserInfo secondUser = userInfoDAO.getEntityById(userId);

        chat.setNameChat(firstUser.getLogin() + " и " + secondUser.getLogin());
        chat.setStatusChat();
        chat.setPersonalChat();

        chat = chatDAO.createAndReturn(chat);

        userChat1.setChat(chat);
        userChat1.setUserInfo(firstUser);
        userChatDAO.create(userChat1);

        userChat2.setChat(chat);
        userChat2.setUserInfo(secondUser);
        userChatDAO.create(userChat2);

        return null;
    }

    @RequestMapping(value = "/edituserinfo", method = RequestMethod.GET)
    public String editUseInfo(Map<String, Object> map) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
        if (userInfo.getPhoto_ava() == null) {
            userInfo.setPhoto_ava("photo/ava.png");
        }
        map.put("userInfo", userInfo);
        return "edituserinfo";
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
                if (link.indexOf(".jpg") < 0) {
                    data.remove(userData);
                }
            }
            List<String> listLink = new CopyOnWriteArrayList<String>();
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
}
