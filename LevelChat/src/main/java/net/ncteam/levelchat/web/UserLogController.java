package net.ncteam.levelchat.web;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.OutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import net.ncteam.levelchat.domain.UserInfo;
import net.ncteam.levelchat.domain.UsersLog;
import net.ncteam.levelchat.service.UserLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.session.SessionInformation;
import net.ncteam.levelchat.annotation.CurrentUser;

@Controller
public class UserLogController {
	@Autowired
	@Qualifier("UserLogService")
	private UserLogService userLogService;

	//это пока лишний метод
	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("users_log", new UsersLog());
		map.put("users_logList", userLogService.listUser());

		return "index";
	}
	
	//отправляет пользователя на страницу login'а
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//при обращении пользователя на сайт, отправка ему страницы пользователя
	@RequestMapping("/")
	public String startPage() {
		return "userpage";
	}
	
	//отправляет пользователя на страницу user'а
	@RequestMapping("/userpage")
	public String userPage(@CurrentUser User user,Map<String, Object> map) {
		
		//List<String> listMessages = userLogService.getMessages(user.getUsername());
		//map.put("listMessages", userLogService.getMessages(user.getUsername()));
		//map.put("Message", "Text of exchange...");
		return "userpage";
	}
	
	
	//этот метод пока не используется
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
	
	//этот метод пока не используется
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	@ResponseBody
	public List<String> ajaxChat(@CurrentUser User user, int mid) {
		 
	        /*Set<String> records = new HashSet<String>();
	        records.add("Record #1");
	        records.add("Record #2");*/
	        
	        List<String> response = userLogService.getMessages(user.getUsername(), mid);
	        
	        
	        return response;
	    }
	 
	 
	//отправляет пользователя на страницу admin'а
	@RequestMapping("/adminpage")
	public String adminPage() {
		return "adminpage";
	}
	
	
	//отправляет пользователя на страницу registration
	@RequestMapping("/registration")
	public String registrationPage() {
		return "registration";
	}
	
	//пользователь ввёл данные для регистрации, контроллер пытется добавить пользователя через service-слой
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("usersLog") UserInfo userInfo,
			BindingResult result) {

		if(userLogService.addUser(userInfo).equals("success"))
		{
			return "redirect:/userpage";
		}
		return "redirect:/registration?error";
	}
}
