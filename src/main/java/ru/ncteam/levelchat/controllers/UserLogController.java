package ru.ncteam.levelchat.controllers;

import java.util.Map;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Controller
public class UserLogController {
	@Autowired
	private UserLogService userLogService;
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
	public String startPage() {
		return "userpage";
	}
	
	@RequestMapping("/userpage")
	public String userPage(Map<String, Object> map) {
		return "userpage";
	}
	 
	 
	@RequestMapping(value = "/adminpage",method = RequestMethod.GET)
	public String adminPage(Map<String, Object> map) {
		map.put("categoryInterestsList", userLogService.getAllCategory());
		return "adminpage";
	}
	
	@RequestMapping(value = "/adminpage",method = RequestMethod.POST)
	@ResponseBody
	public String putCategoryInterest(@RequestBody String categoryName) {
		try
		{
			userLogService.putCategoryInterestByName(categoryName);
			return categoryName;
		}
		catch(Exception e)
		{
			return "fail";
		}
	}
	
	
	@RequestMapping(value = "/adminpage/{categoryName}",method = RequestMethod.GET)
	@ResponseBody
	public List<Interests> getInterestsByName(@PathVariable String categoryName) {
		List<Interests> listInterests = userLogService.getInterestsByCatName(categoryName); 
		return listInterests;
	}
	
	
	@RequestMapping(value = "/adminpage/update",method = RequestMethod.POST)
	@ResponseBody
	public String updateInterests(@RequestBody ArrayList<Interests> interests,
			BindingResult result) {
		try
		{
			userLogService.updateInterests(interests);
			return "success";
		}
		catch (Exception e)
		{
			return "fail";
		}
	}
	
	@RequestMapping(value = "/adminpage/put/{categoryName}",method = RequestMethod.POST)
	@ResponseBody
	public List<Long> putInterests(@PathVariable String categoryName,
			@RequestBody ArrayList<Interests> interests,
			BindingResult result) {
		try
		{
			return userLogService.putInterests(interests,categoryName);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	@RequestMapping(value = "/adminpage/delete/{categoryName}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteInterests(@PathVariable String categoryName,
			@RequestBody ArrayList<Interests> interests,
			BindingResult result) {
		try
		{
			userLogService.deleteInterests(interests,categoryName);
			return "success";
		}
		catch (Exception e)
		{
			return "fail";
		}
	}
	
	@RequestMapping(value = "/adminpage/deleteCategory/{categoryName}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCategory(@PathVariable String categoryName) {
		try
		{
			userLogService.deleteCategory(categoryName);
			return "success";
		}
		catch (Exception e)
		{
			return "fail";
		}
	}
	
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("usersLog") @Valid UserInfo userInfo,
			BindingResult result,
			Model model) {
		String userPassword = userInfo.getPassword();
		if(result.hasErrors())
		{
			String code;
			List<FieldError> listErrors = result.getFieldErrors();
			for (int i=0;i<listErrors.size();i++)
			{
				code = listErrors.get(i).getCode();
				if(!code.equals("typeMismatch"))
				{
					model.addAttribute(listErrors.get(i).getField()+"Error", listErrors.get(i).getDefaultMessage());
				}
			}
			return "registration";
		}
		if(userLogService.addUser(userInfo).equals("success"))
		{
			userLogService.autoLogin(userInfo.getLogin(), userPassword);
			return "postregistration";
		}
		else
		{
			model.addAttribute("loginError", "Пользователь с таким логином уже существует");
			return "registration";
		}
	}
	
	@RequestMapping(value = "/registration/check", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(@RequestParam(value = "login", required=false) String login) {
		if(userLogService.checkLogin(login))
		{
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping("/postregistration")
	public String postRegistrationPage() {
		return "postregistration";
	}
	
	@RequestMapping(value = "/postregistration", method = RequestMethod.POST)
	public String updateUserInfo(@ModelAttribute("usersLog") @Valid UserInfo userInfo,
			BindingResult result,
			Model model) {
		if(result.hasErrors())
		{
			String code;
			List<FieldError> listErrors = result.getFieldErrors();
			for (int i=0;i<listErrors.size();i++)
			{
				code = listErrors.get(i).getCode();
				if(!code.equals("typeMismatch"))
				{
					model.addAttribute(listErrors.get(i).getField()+"Error", listErrors.get(i).getDefaultMessage());
				}
				else
				{
					model.addAttribute(listErrors.get(i).getField()+"Error", "Недопустимое значение");
				}
			}
			return "postregistration";
		}
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userInfo.setSex(userInfo.getSex().substring(0, 1));
		userInfo.setLogin(user.getUsername());

		if(userLogService.updateUserInfo(userInfo).equals("success"))
		{
			return "postregistrationPhoto";
		}
		return "redirect:/postregistration?error";
	}
	
	@RequestMapping("/postregistrationPhoto")
	public String postRegistrationPhotoPage() {
		return "postregistrationPhoto";
	}
	
	@RequestMapping(value = "/postregistrationPhoto",method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfoPhoto(@RequestParam(value = "photo_ava", required=false) MultipartFile photo_ava) {
		if (!photo_ava.isEmpty()) {
			if(!(photo_ava.getContentType().equals("image/jpeg") || photo_ava.getContentType().equals("image/png")))
			{
				return "fail";
			}
	            try {
		                User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		        		UserInfo userInfo = new UserInfo();
		        		userInfo.setLogin(user.getUsername());
		        		String str = userLogService.updateUserInfoPhoto(userInfo,photo_ava);
		        		if(!str.equals("fail"))
		        		{
		        			return ("resources/images/"+str);
		        		}
		        		return "fail";
	            } catch (Exception e) {
	            	return "fail";
	            }
	        } else {
	        	return "fail";
	        }
		

		
	}
}
