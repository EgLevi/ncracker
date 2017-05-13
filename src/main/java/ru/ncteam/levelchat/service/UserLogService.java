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

    void autoLogin(String username, String password);

    List<CategoryInterest> getAllCategory();

    List<Interests> getInterestsByCatId(long categoryId);

    List<Interests> getInterestsByCatName(String categoryName);

    void putInterestsByCatId(long categoryId, List<Interests> interests) throws HibernateException;

    List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException;

    void deleteInterests(List<Interests> interests, String categoryName) throws HibernateException;

    void deleteCategory(String categoryName) throws HibernateException;

    void updateInterests(List<Interests> interests) throws HibernateException;

    void putCategoryInterestByName(String categoryName) throws HibernateException;

    public UserInfo getUserByLogin(String login) throws HibernateException;

}