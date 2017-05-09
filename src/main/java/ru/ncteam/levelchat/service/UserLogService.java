package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;


public interface UserLogService {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public String updateUserInfoPhoto(UserInfo userInfo);

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
    public List<String> getMessages(String username);
    
    public List<String> getMessages(String username, int mid);
    
    public void addMessage(String username, String message, int mid);
    
    public void addCategoryInterests(CategoryInterest catInteres);
    
    public List<CategoryInterest> getCategories();
    
    public void addInter(Interests inter);
    
    public CategoryInterest getCategorie(String name);
    
    public List<Interests> getListInterests(CategoryInterest name);
}