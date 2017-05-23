package ru.ncteam.levelchat.controllers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


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
	private static Logger log = Logger.getLogger("h");
	
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
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfo userInfo = userLogService.getUserByLogin(user.getUsername());
		if(userInfo.getPhoto_ava()==null)
		{
			userInfo.setPhoto_ava("photo/ava.png");
		}
		map.put("userInfo",userInfo);
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
	
	@RequestMapping("/postregistrationPhoto")
	public String postRegistrationPhotoPage() {
		return "postregistrationPhoto";
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

	@RequestMapping(value = "/anketa", method = RequestMethod.GET)	
	public String anketa (ModelMap model)
	{		
		List<CategoryInterest> category = new ArrayList<CategoryInterest>();
		category = userLogService.getAllCategory();
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
	}		
	
	@RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> handleGetHelp(Locale loc, String code, HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    
	    JSONObject json = new JSONObject();		
	    
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String name = data.get("name").getAsString();
		System.out.println(name);
		
		CategoryInterest cat = new CategoryInterest();
	    cat = userLogService.getCategorie(name);
	    
		List<Interests> interestsList = new ArrayList<Interests>();
		interestsList = userLogService.getInterestsByCatName(name);
		int count = 0;
		for(Interests curr : interestsList)
		{
			json.put( String.valueOf(count), curr.getInterestName() );
			count++;
		}
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<Interests> interests = new HashSet<Interests>();
		
		UserInfo userInfo = new UserInfo();
		interests = userInfo.getInterests();
		
		
		System.out.println(String.valueOf(userLogService.getUSER_ID(user.getUsername())));
		
	    return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ajaxFullInterest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getFullInterests(Locale loc) throws JsonSyntaxException, JsonIOException, IOException {
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    
	    JSONObject json = new JSONObject();
	    
	    List<CategoryInterest> Category = new ArrayList<CategoryInterest>();	
	    Category = userLogService.getAllCategory();
	    
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int count = 0;
	    for(CategoryInterest nameCategory:Category){	    	
	    	json.put( String.valueOf(count), nameCategory.getCategoryName());
	    	/*System.out.println(String.valueOf(userLogService.getCategoryIDByCatName(nameCategory.getCategoryName()))+" : ");
	    	List<String> interests = userLogService.getUsersInterests(userLogService.getUSER_ID(user.getUsername()), userLogService.getCategoryIDByCatName(nameCategory.getCategoryName()));
	    	
			for(String inter:interests)
			{
				json.put( String.valueOf(count), inter);
				System.out.println(inter+";");
				count++;
			}	*/
	    	count++;
	    }		
			
	    return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ajaxGetInterestCat", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getUserInterests(Locale loc, String code, HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException {
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    
	    JSONObject json = new JSONObject();		
	    
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String nameCategory = data.get("name").getAsString();		
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		
		System.out.println(String.valueOf(userLogService.getCategoryIDByCatName(nameCategory)));
		
		List<String> interests = userLogService.getUsersInterests(userLogService.getUSER_ID(user.getUsername()), userLogService.getCategoryIDByCatName(nameCategory));
		int count = 0;
		for(String inter:interests)
		{
			json.put( String.valueOf(count), inter);
			count++;
		}		
	    return new ResponseEntity<String>(json.toString(), responseHeaders, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/ajaxSave", method = RequestMethod.POST)
	@ResponseBody
	public void saveInterestsForUser(HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    JSONObject json = new JSONObject();	    
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		int count = data.get("count").getAsInt();
		List<String> NamesInterests = new ArrayList<String>();
		for(int i = 0; i < count; i++){
			NamesInterests.add(data.get(String.valueOf(i)).getAsString());
		}
		long id_category = userLogService.getCategoryIDByCatName(data.get("category").getAsString());		
		Set<Interests> InterstsList = userLogService.getInterestsByInteresName(NamesInterests);		
		long userID = userLogService.getUSER_ID(user.getUsername());
		userLogService.putInterestsUser(userID, InterstsList);		
		for(Interests curr:InterstsList){
			System.out.println(curr.getInterestId());
		}
		
		/*List<String> listInterestsName =  new ArrayList<String>();
		JsonObject massJson = data.
		for(JsonObject curr:data){
			
		}*/
		//String nameCategory = data.get("1").getAsString();				
		//System.out.println("ID Пользователя: "+String.valueOf(userLogService.getUSER_ID(user.getUsername()))+"\nRequets: "+nameCategory);	
	}
	
	@RequestMapping(value = "/ajaxDelete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteInterestsForUser(HttpServletRequest request) throws JsonSyntaxException, JsonIOException, IOException{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    JSONObject json = new JSONObject();	    
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		int count = data.get("count").getAsInt();
		List<String> NamesInterests = new ArrayList<String>();
		for(int i = 0; i < count; i++){
			NamesInterests.add(data.get(String.valueOf(i)).getAsString());
		}
		long id_category = userLogService.getCategoryIDByCatName(data.get("category").getAsString());
	}
	
}
