package ru.ncteam.levelchat.dao;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


public interface UserLogDAO {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public String updateUserInfoPhoto(UserInfo userInfo);

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
	public boolean existUser(UserInfo userInfo);
	
	public boolean existUser(String login); 
	
	//public List<String> getMessages(String username);
	
	//public List<String> getMessages(String username,int mid);
	
	//public void addMessage(String username, String message, int mid);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public long getIdImg();
    
    public void setIdImg(long idImg);
    
    public List<CategoryInterest> getAllCategory();
    
    public List<Interests> getInterestsByCatId(long categoryId);
    
    public List<Interests> getInterestsByCatName(String categoryNAme);
    
    public void putInterestsByCatId(long categoryId,List<Interests> interests) throws HibernateException;
    
    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException;
    
    public void deleteInterests(List<Interests> interests, String categoryName) throws HibernateException;
    
    public void deleteCategory(String categoryName) throws HibernateException;
    
    public void updateInterests(List<Interests> interests) throws HibernateException;
    
    public void putCategoryInterestByName(String categoryName) throws HibernateException;

}