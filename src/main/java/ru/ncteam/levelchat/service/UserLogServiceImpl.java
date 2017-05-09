package ru.ncteam.levelchat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncteam.levelchat.dao.UserLogDAO;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {

	@Autowired
    private UserLogDAO userLogDAO;

    public void setUserLogDAO(UserLogDAO userLogDAO) {
        this.userLogDAO = userLogDAO;
    }

    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo);
    }
    
    public String updateUserInfo(UserInfo userInfo) {
        return userLogDAO.updateUserInfo(userInfo);
    }
    
    public String updateUserInfoPhoto(UserInfo userInfo) {
        return userLogDAO.updateUserInfoPhoto(userInfo);
    }

    /*public List<UsersLog> listUser() {

        return userLogDAO.listUser();
    }

    public void removeUser(Integer iduserlog) {
        userLogDAO.removeUser(iduserlog);
    }*/
    
    public List<Interests> getListInterests(CategoryInterest name)
    {
    	return userLogDAO.getListInterests(name);
    }
    
    public CategoryInterest getCategorie(String name)
    {
    	return userLogDAO.getCategorie(name);
    }

    public List<String> getMessages(String username) {
        return userLogDAO.getMessages(username);
    }

    public List<String> getMessages(String username, int mid) {
        return userLogDAO.getMessages(username, mid);
    }
    
    public void addInter(Interests inter)
    {
    	System.out.println(userLogDAO.addInterest(inter));
    }
    
    public void addCategoryInterests(CategoryInterest catInteres)
    {
    	System.out.println(userLogDAO.addCategory(catInteres));    	
    }
    
    public List<CategoryInterest> getCategories()
    {
    	return userLogDAO.getCategory();
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
    }
}
