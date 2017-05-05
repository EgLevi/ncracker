package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.web.multipart.MultipartFile;


public interface UserLogService {

    String addUser(UserInfo userInfo);

    String updateUserInfo(UserInfo userInfo);

    boolean checkLogin(String login);

    String uploadUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava);

    String updateUserInfoPhoto(UserInfo userInfo, String filename);

    //public List<UsersLog> listUser();

    //public void removeUser(Integer iduserlog);

    //public List<String> getMessages(String username);

    //public List<String> getMessages(String username, int mid);

    //public void addMessage(String username, String message, int mid);

    void autoLogin(String username, String password);

    public List<CategoryInterest> getAllCategory();

    public List<Interests> getInterestsByCatId(long categoryId);

    public List<Interests> getInterestsByCatName(String categoryName);

    public void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException;

    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException;

    public void deleteInterests(List<Interests> interests, String categoryName) throws HibernateException;

    public void deleteCategory(String categoryName) throws HibernateException;

    public void updateInterests(List<Interests> interests) throws HibernateException;

    public void putCategoryInterestByName(String categoryName) throws HibernateException;
}