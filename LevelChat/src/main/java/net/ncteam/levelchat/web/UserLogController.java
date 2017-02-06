package net.ncteam.levelchat.web;

import java.util.Map;
import java.util.List;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.session.SessionInformation;

@Controller
public class UserLogController {
	@Autowired
	@Qualifier("UserLogService")
	private UserLogService userLogService;

	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("users_log", new UsersLog());
		map.put("users_logList", userLogService.listUser());

		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
	public String startPage() {
		return "userpage";
	}
	
	
	@RequestMapping("/userpage")
	public String userPage() {
		return "userpage";
	}
	
	@RequestMapping("/adminpage")
	public String adminPage() {
		return "adminpage";
	}
}
