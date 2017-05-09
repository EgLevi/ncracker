package ru.ncteam.levelchat.controllers;

import java.util.Map;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.UserLogService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

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
	public String addContact(@ModelAttribute("usersLog") UserInfo userInfo, BindingResult result) {

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
			BindingResult result) 
	{
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
	
	/*@RequestMapping(value="/anketa", method = RequestMethod.GET)
	public String anketa(Map<String, Object> model){		
		//Set<Interests> set = cat.getInterests();
		//userLogService.add
		cat.setCategoryName("Фильмы");
		List<String> l = new ArrayList<String>();
		if(userLogService.addCategoryInterests(cat).equals("success"))
		{
			model.put("InterestList", l);			
			return "anketa";
		}
		else{
			return "redirect:/anketa?error";
		}
		//l.add(userLogService.addCategoryInterests(cat));
		/*for (int i = 0; i < 5;i++){
			l.add(String.valueOf(i));
		}*/
		/*for(Interests current : set ){
			l.add(current.getInterestName());
		}*/
		
	//}
	//@SuppressWarnings("null")	
	@RequestMapping(value = "/anketa", method = RequestMethod.GET)	
	public String anketa (ModelMap model)
	{		
		List<CategoryInterest> category = new ArrayList<CategoryInterest>();
		category = userLogService.getCategories();
		List<String> l = new ArrayList<String>();		
		for(CategoryInterest currСategory : category){
		
			l.add(currСategory.getCategoryName());
			
			//получаю лист с интересами и засовываю в model для .jsp
			List<Interests> lost = new ArrayList<Interests>();
			lost = userLogService.getListInterests(currСategory);
			model.put(currСategory.getCategoryName(), lost);
		}
		model.put("CategoryList", l);	
		return "anketa";
		
		//Map<String, Object> model = new HashMap<String, Object>();
		
				//CategoryInterest cat = new CategoryInterest();
				//cat.setCategoryName("Интересы");
				//cat.set
				//userLogService.addCategoryInterests(cat);
		
		//save()
		/*CategoryInterest cat = new CategoryInterest();
		cat.setCategoryName("Музыка");
		
		userLogService.addCategoryInterests(cat);
		List<String> l = new ArrayList<String>();
		l.add(cat.getCategoryName());*/
		
		/*CategoryInterest cat = new CategoryInterest();
		
		List<String> l = new ArrayList<String>();
		
		cat = userLogService.getCategorie("Музыка");
		
		System.out.println(cat.getCategoryId());
		List<Interests> lost = new ArrayList<Interests>();
		lost = userLogService.getListInterests(cat);
		for(Interests curr : lost)
		{
			System.out.println(curr.getInterestName() + curr.getCategoryInterest().getCategoryId());
		}*/
		//Interests inter = new Interests();
		//inter.setInterestName("AC/DC");
		//inter.setCategoryInterest(cat);				
		
	}	
	
	
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> handleGetHelp(Locale loc, String code, HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    
	    JSONObject json = new JSONObject();		
	    
		/*List<CategoryInterest> category = new ArrayList<CategoryInterest>();
		category = userLogService.getCategories();
		List<String> l = new ArrayList<String>();	
		int count = 0;
		for(CategoryInterest currСategory : category){
			l.add(currСategory.getCategoryName());
			json.put( String.valueOf(count), currСategory.getCategoryName() );
			count++;
		}	*/	
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String name = data.get("name").getAsString();
		System.out.println(name);
		CategoryInterest cat = new CategoryInterest();
	    cat = userLogService.getCategorie(name);
	    List<Interests> lost = new ArrayList<Interests>();
		lost = userLogService.getListInterests(cat);	
		int count = 0;
		for(Interests curr : lost)
		{
			json.put( String.valueOf(count), curr.getInterestName() );
			count++;
			//System.out.println(curr.getInterestName() + curr.getCategoryInterest().getCategoryId());
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getUsername());
	    return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
	}

}
