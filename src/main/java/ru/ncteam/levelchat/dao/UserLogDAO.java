package ru.ncteam.levelchat.dao;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserLogDAO {

	public String addUser(UserInfo userInfo,String queryString);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public String updateUserInfoPhoto(UserInfo userInfo, String queryString);
	
	public boolean existUser(UserInfo userInfo,String queryString);
	
	public boolean existUser(String login,String queryString); 
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    public List<CategoryInterest> getAllCategory(String queryString);
    
    public List<Interests> getInterestsByCatId(long categoryId,String queryString);
    
    public List<Interests> getInterestsByCatName(String categoryName,String queryString);
    
    public void putInterestsByCatId(long categoryId,List<Interests> interests,String queryString) throws HibernateException;
    
    public List<Long> putInterests(List<Interests> interests, String categoryName,String queryString) throws HibernateException;
    
    public void deleteInterests(List<Interests> interests, String categoryName, String queryString) throws HibernateException;
    
    public void deleteCategory(String categoryName,String queryString) throws HibernateException;
    
    public void updateInterests(List<Interests> interests,String queryString) throws HibernateException;
    
    public void putCategoryInterestByName(String categoryName) throws HibernateException;

    public void putInterestList(List<Interests> interestLists, Long groupid);

    public UserInfo getUserByLogin(String login,String queryString) throws HibernateException;

    public ArrayList getUsersForChat(String city, String country, String sex, int otAge, int doAge, Long group);

    public Long getId();

    public void putDashBoard(Long userid);



}