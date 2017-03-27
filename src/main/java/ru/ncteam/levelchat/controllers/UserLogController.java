package ru.ncteam.levelchat.controllers;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileWriter;

import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ru.ncteam.levelchat.annotation.CurrentUser;

@Controller
public class UserLogController {
	@Autowired
	private UserLogService userLogService;

	/*@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("users_log", new UsersLog());
		map.put("users_logList", userLogService.listUser());

		return "index";
	}*/
	
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
		
		//List<String> listMessages = userLogService.getMessages(user.getUsername());
		//map.put("listMessages", userLogService.getMessages(user.getUsername()));
		//map.put("Message", "Text of exchange...");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername();
		return "userpage";
	}
	
	
	@RequestMapping(value = "/ajaxadd", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> ajaxAddMessage(@CurrentUser User user, String mes, int mid) {
	 
		File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        userLogService.addMessage(user.getUsername(), mes, mid);
        Set<String> records = new HashSet<String>();
        records.add("Record #1");
        records.add("Record #2");
        return records;
    }
	
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<String> ajaxChat(@CurrentUser User user, int mid) {
		 
	        /*Set<String> records = new HashSet<String>();
	        records.add("Record #1");
	        records.add("Record #2");*/
	        
	        List<String> response = userLogService.getMessages(user.getUsername(), mid);
	        
	        
	        return response;
	    }
	 
	 
	@RequestMapping("/adminpage")
	public String adminPage() {
		return "adminpage";
	}
	
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("usersLog") UserInfo userInfo,
			BindingResult result) {

		if(userLogService.addUser(userInfo).equals("success"))
		{
			return "postregistration";
		}
		return "redirect:/registration?error";
	}
	
	@RequestMapping("/postregistration")
	public String postRegistrationPage() {
		return "postregistration";
	}
	
	@RequestMapping(value = "/postregistration", method = RequestMethod.POST)
	public String updateUserInfo(@ModelAttribute("usersLog") UserInfo userInfo,
			BindingResult result) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userInfo.setLogin(user.getUsername());

		if(userLogService.updateUserInfo(userInfo).equals("success"))
		{
			return "postregistrationPhoto";
		}
		return "redirect:/postregistration?error";
	}
	
	@RequestMapping("/posregistrationPhoto")
	public String postRegistrationPhotoPage() {
		return "posregistrationPhoto";
	}
	
	@RequestMapping(value = "/postregistrationPhoto", method = RequestMethod.POST)
	public String updateUserInfoPhoto(@ModelAttribute("usersLog") UserInfo userInfo,
			@CurrentUser User user,
			BindingResult result) {
		userInfo.setLogin(user.getUsername());

		if(userLogService.updateUserInfoPhoto(userInfo).equals("success"))
		{
			return "postregistrationPhoto";
		}
		return "redirect:/postregistration?error";
	}
}
