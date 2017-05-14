package ru.ncteam.levelchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;
import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.dao.ChatDAO;
import ru.ncteam.levelchat.dao.UserInfoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class UserpageController {

	@Autowired
	private ChatDAO chatDAO;

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Autowired
	private UserLogService userLogService;

	
	@RequestMapping("/userpage")
	public String userPage(Map<String, Object> map) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication a;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		map.put("userInfo",userInfo);
		List<Chat> chats = userInfoDAO.getUserChats(user.getUsername());
		map.put("chats", chats);
		return "userpage";
	}
	 



	@RequestMapping(value = "/search")
	public String getSearchPage() {
		return "search";
	}

	@RequestMapping(value = "/search/getCategories",method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryInterest> getCategories() {
		List<CategoryInterest> listCategories = userLogService.getAllCategory();
		return listCategories;
	}

	@RequestMapping(value = "/search/getInterests/{categoryName}",method = RequestMethod.GET)
	@ResponseBody
	public List<Interests> getInterestsByNameForSearch(@PathVariable String categoryName) {
		List<Interests> listInterests = userLogService.getInterestsByCatName(categoryName);
		return listInterests;
	}


	@RequestMapping(value = "/search",method = RequestMethod.POST)
	@ResponseBody
	public String putInterestsForSearch(@RequestBody ArrayList<Interests> interests,
								  BindingResult result) {
		return "success";
	}

	
	@RequestMapping(value = "/edituserinfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserInfo(@RequestBody @Valid UserInfo userInfo,
			BindingResult result) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(result.hasErrors())
		{
			String code;
			List<FieldError> listErrors = result.getFieldErrors();
			for (int i=0;i<listErrors.size();i++)
			{
				code = listErrors.get(i).getCode();
				if(!code.equals("typeMismatch"))
				{
					map.put(listErrors.get(i).getField()+"Error", listErrors.get(i).getDefaultMessage());
				}
				else
				{
					map.put(listErrors.get(i).getField()+"Error", "Недопустимое значение");
				}
			}
			return map;
		}
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userInfo.setSex(userInfo.getSex().substring(0, 1));
		userInfo.setLogin(user.getUsername());

		if(userLogService.updateUserInfo(userInfo).equals("success"))
		{
			return null;
		}
		map.put("DataBaseError","DataBaseError");
		return map;
	}
	
	@RequestMapping(value = "/postregistrationPhoto",method = RequestMethod.POST)
	@ResponseBody
	public String uploadUserInfoPhoto(@RequestParam(value = "photo_ava", required=false) MultipartFile photo_ava) {
		if (!photo_ava.isEmpty()) {
			if(!(photo_ava.getContentType().equals("image/jpeg") || photo_ava.getContentType().equals("image/png")))
			{
				return "wrong format of photo";
			}
	            try {
		                User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		        		UserInfo userInfo = new UserInfo();
		        		userInfo.setLogin(user.getUsername());
		        		String str = userLogService.uploadUserInfoPhoto(userInfo,photo_ava);
		        		return str;
	            } catch (Exception e) {
	            	return "fail";
	            }
	        } else {
	        	return "failed your photo is empty";
	        }
	}
	
	@RequestMapping(value = "/postregistrationPhoto/save",method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfoPhoto(@RequestBody String relativePath, HttpServletRequest request) {
		try 
		{
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		UserInfo userInfo = new UserInfo();
    		userInfo.setLogin(user.getUsername());
    		String str = userLogService.updateUserInfoPhoto(userInfo,relativePath);
    		return str;
		} 
		catch (Exception e) 
		{
			return "fail";
		}
	}
	
}
