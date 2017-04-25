package ru.ncteam.levelchat.service;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
	
	private static String UPLOADED_FOLDER = "C://apache-tomcat-8.5.9//webapps//ru.ncteam.levelchat//resources//images//";

	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
    private UserLogDAO userLogDAO;
	
	@Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public void setUserLogDAO(UserLogDAO userLogDAO) {
        this.userLogDAO = userLogDAO;
    }
    
    
    public boolean checkLogin(String login)
    {
    	return userLogDAO.existUser(login,getQuery("hql/UserInfoByLogin.hql"));
    }

    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo,getQuery("hql/UserInfoByLogin.hql"));
    }
    
    public String updateUserInfo(UserInfo userInfo) {
        return userLogDAO.updateUserInfo(userInfo, getQuery("hql/updateUserInfo.hql"));
    }
    
    public String updateUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava) {
    	long idImg = userLogDAO.getIdImg(getQuery("hql/getIdImage.hql"));
    	idImg++;
    	userInfo.setPhoto_ava(idImg);
    	Path path = Paths.get(UPLOADED_FOLDER + idImg+".jpg");
    	try {
			Files.write(path, photo_ava.getBytes());
			if(userLogDAO.updateUserInfoPhoto(userInfo).equals("success"))
	    	{
	    		userLogDAO.setIdImg(idImg,getQuery("hql/setIdImage.hql"));
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
    	return userLogDAO.getAllCategory(getQuery("hql/allCategory.hql"));
    }
    
    public List<Interests> getInterestsByCatId(long categoryId)
    {
    	return userLogDAO.getInterestsByCatId(categoryId,getQuery("hql/getInterestsByCatId.hql"));
    }
    
    public List<Interests> getInterestsByCatName(String categoryName)
    {
    	
    	return userLogDAO.getInterestsByCatName(categoryName, getQuery("hql/getInterestByCatName.hql"));
    }
    
    public void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException
    {
    	userLogDAO.putInterestsByCatId(categoryId,interests,getQuery("hql/putInterestsByCatId.hql"));
    }
    
    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException
    {
    	return userLogDAO.putInterests(interests, categoryName, getQuery("hql/CategoryInterestByName.hql"));
    }
    
    public void deleteInterests(List<Interests> interests,String categoryName) throws HibernateException
    {
    	userLogDAO.deleteInterests(interests,categoryName,getQuery("hql/CategoryInterestByName.hql"));
    }
    
    public void deleteCategory(String categoryName) throws HibernateException
    {
    	userLogDAO.deleteCategory(categoryName, getQuery("hql/getInterestByCatName.hql"));
    }
    
    public void updateInterests(List<Interests> interests) throws HibernateException
    {
    	userLogDAO.updateInterests(interests,getQuery("hql/updateInterests.hql"));
    }
    
    public void putCategoryInterestByName(String categoryName)  throws HibernateException
    {
    	userLogDAO.putCategoryInterestByName(categoryName);
    }
    
    private String getQuery(String filename)
    {
    	try 
    	{
        	Resource res = appContext.getResource("classpath:"+filename);
    		InputStream is = res.getInputStream();
    		File file  = res.getFile();
    		byte[] fileContent = ByteBuffer.allocate((int)file.length()).array();
    		long actualLength = is.read(fileContent, 0, fileContent.length);
    		String query = new String(fileContent,0,(int)actualLength);
    		return query;
		} 
    	catch (IOException e) 
    	{
    		e=e;
		}
    	catch(Exception ne)
    	{
    		ne=ne;
    	}
    	return null;
    }
    
    
}
