package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface UserLogService {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public boolean checkLogin(String login);
	
	public String updateUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava);

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
    //public List<String> getMessages(String username);
    
    //public List<String> getMessages(String username, int mid);
    
    //public void addMessage(String username, String message, int mid);
    
    public void autoLogin(String username, String password);
}