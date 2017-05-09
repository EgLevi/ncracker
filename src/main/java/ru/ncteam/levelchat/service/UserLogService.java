package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.web.multipart.MultipartFile;


public interface UserLogService {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public boolean checkLogin(String login);
	
	public String uploadUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava);
	
	public String updateUserInfoPhoto(UserInfo userInfo, String filename);

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
    //public List<String> getMessages(String username);
    
    //public List<String> getMessages(String username, int mid);
    
    //public void addMessage(String username, String message, int mid);
    
    public void autoLogin(String username, String password);
    
    public List<CategoryInterest> getAllCategory();
    
    public List<Interests> getInterestsByCatId(long categoryId);
    
    public List<Interests> getInterestsByCatName(String categoryName);
    
    public void addMessage(String username, String message, int mid);
    
    public void addCategoryInterests(CategoryInterest catInteres);
    
    public List<CategoryInterest> getCategories();
    
    public void addInter(Interests inter);
    
    public CategoryInterest getCategorie(String name);
    
    public List<Interests> getListInterests(CategoryInterest name);

    public void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException;
    
    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException;
    
    public void deleteInterests(List<Interests> interests,String categoryName) throws HibernateException;
    
    public void deleteCategory(String categoryName) throws HibernateException;
    
    public void updateInterests(List<Interests> interests) throws HibernateException;
    
    public void putCategoryInterestByName(String categoryName) throws HibernateException;

    public UserInfo getUserByLogin(String login) throws HibernateException;

}