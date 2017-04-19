package ru.ncteam.levelchat.service;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.ncteam.levelchat.dao.UserLogDAO;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
	
	private static String UPLOADED_FOLDER = "C://apache-tomcat-8.5.9//webapps//ru.ncteam.levelchat//resources//images//";

	@Autowired
    private UserLogDAO userLogDAO;
	
	@Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public void setUserLogDAO(UserLogDAO userLogDAO) {
        this.userLogDAO = userLogDAO;
    }
    
    
    public boolean checkLogin(String login)
    {
    	return userLogDAO.existUser(login);
    }

    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo);
    }
    
    public String updateUserInfo(UserInfo userInfo) {
        return userLogDAO.updateUserInfo(userInfo);
    }
    
    public String updateUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava) {
    	long idImg = userLogDAO.getIdImg();
    	idImg++;
    	userInfo.setPhoto_ava(idImg);
    	Path path = Paths.get(UPLOADED_FOLDER + idImg+".jpg");
    	try {
			Files.write(path, photo_ava.getBytes());
			if(userLogDAO.updateUserInfoPhoto(userInfo).equals("success"))
	    	{
	    		userLogDAO.setIdImg(idImg);
	    		return (""+idImg+".jpg");
	    	}
	    	else
	    	{
	    		return "fail";
	    	}
		} catch (IOException e) {
			return "fail";
		}
    	
    }

    /*public List<UsersLog> listUser() {

        return userLogDAO.listUser();
    }

    public void removeUser(Integer iduserlog) {
        userLogDAO.removeUser(iduserlog);
    }*/


    /*public List<String> getMessages(String username) {
        return userLogDAO.getMessages(username);
    }

    public List<String> getMessages(String username, int mid) {
        return userLogDAO.getMessages(username, mid);
    }

    public void addMessage(String username, String messages, int mid) {
        File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write("in Service\n");
            fr.close();

        } catch (IOException e) {
        }
        userLogDAO.addMessage(username, messages, mid);
    }*/
    
    
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userLogDAO.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        
        try{
        	authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        }
        catch(Exception e)
        {
        	e=e;
        }
    }
    
    public List<CategoryInterest> getAllCategory()
    {
    	return userLogDAO.getAllCategory();
    }
    
    public List<Interests> getInterestsByCatId(long categoryId)
    {
    	return userLogDAO.getInterestsByCatId(categoryId);
    }
    
    public List<Interests> getInterestsByCatName(String categoryName)
    {
    	return userLogDAO.getInterestsByCatName(categoryName);
    }
    
    public void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException
    {
    	userLogDAO.putInterestsByCatId(categoryId,interests);
    }
    
    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException
    {
    	return userLogDAO.putInterests(interests, categoryName);
    }
    
    public void deleteInterests(List<Interests> interests,String categoryName) throws HibernateException
    {
    	userLogDAO.deleteInterests(interests,categoryName);
    }
    
    public void deleteCategory(String categoryName) throws HibernateException
    {
    	userLogDAO.deleteCategory(categoryName);
    }
    
    public void updateInterests(List<Interests> interests) throws HibernateException
    {
    	userLogDAO.updateInterests(interests);
    }
    
    public void putCategoryInterestByName(String categoryName)  throws HibernateException
    {
    	userLogDAO.putCategoryInterestByName(categoryName);
    }
    
    
}
