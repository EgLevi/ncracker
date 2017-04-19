package ru.ncteam.levelchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserLogController {
	@Autowired
	private UserLogService userLogService;
	
	/*@Autowired
	private Trial trial;*/

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
		//trial.incCounter();
		//trial.incCounter();
		//int cnt=trial.getCounter();
		return "userpage";
	}
	
	@RequestMapping("/userpage")
	public String userPage(Map<String, Object> map) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		map.put("uname", userLogService.getUserInfo(user.getUsername()));
		
		//List<String> listMessages = userLogService.getMessages(user.getUsername());
		//map.put("listMessages", userLogService.getMessages(user.getUsername()));
		//map.put("Message", "Text of exchange...");
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    //String name = user.getUsername();
	    //trial.incCounter();
		return "userpage";
	}
	
	
	/*@RequestMapping(value = "/ajaxadd", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> ajaxAddMessage(@CurrentUser User user, String mes, int mid) {
	 
		File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        userLogService.addMessage(user.getUsername(), mes, mid);
        Set<String> records = new HashSet<String>();
        records.add("Record #1");
        records.add("Record #2");
        return records;
    }*/
	
	/*@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<String> ajaxChat(@CurrentUser User user, int mid) {
		 
	        Set<String> records = new HashSet<String>();
	        records.add("Record #1");
	        records.add("Record #2");
	        
	        List<String> response = userLogService.getMessages(user.getUsername(), mid);
	        
	        
	        return response;
	    }*/
	 
	 
	@RequestMapping("/adminpage")
	public String adminPage() {
		return "adminpage";
	}
	
	
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("usersLog") @Valid UserInfo userInfo,
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
			}
			return "registration";
		}
		if(userLogService.addUser(userInfo).equals("success"))
		{
			userLogService.autoLogin(userInfo.getLogin(), userInfo.getPassword());
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
			if(!(photo_ava.getContentType().equals("jpg") || photo_ava.getContentType().equals("png")))
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
	
	/*@RequestMapping(value = "/ajaxadd", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> ajaxAddMessage(@CurrentUser User user, String mes, int mid) {
	 
		File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        userLogService.addMessage(user.getUsername(), mes, mid);
        Set<String> records = new HashSet<String>();
        records.add("Record #1");
        records.add("Record #2");
        return records;
    }*/
}
