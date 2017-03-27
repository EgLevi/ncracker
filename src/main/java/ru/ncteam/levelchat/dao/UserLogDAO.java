package ru.ncteam.levelchat.dao;

import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;


public interface UserLogDAO {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public String updateUserInfoPhoto(UserInfo userInfo);

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
	public boolean existUser(UserInfo userInfo); 
	
	public List<String> getMessages(String username);
	
	public List<String> getMessages(String username,int mid);
	
	public void addMessage(String username, String message, int mid);

}