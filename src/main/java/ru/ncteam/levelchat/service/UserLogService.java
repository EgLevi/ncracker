package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.entity.UsersLog;

import java.util.List;


public interface UserLogService {

	public String addUser(UserInfo userInfo);

	public List<UsersLog> listUser();

	public void removeUser(Integer iduserlog);
	
    public List<String> getMessages(String username);
    
    public List<String> getMessages(String username, int mid);
    
    public void addMessage(String username, String message, int mid);
}