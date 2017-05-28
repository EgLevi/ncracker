package ru.ncteam.levelchat.service;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;
import java.util.Set;

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

    UserInfo getUserByLogin(String login) throws HibernateException;

    public long getCategoryIDByCatName(String categoryName);

    public long getUSER_ID(String username);

    public List<String> getUsersInterests(long userId, long categoryId);

    public void addCategoryInterests(CategoryInterest catInteres);

    public List<CategoryInterest> getCategories();

    public void addInter(Interests inter);

    public void putInterestsUser(long UserID, Set<Interests> interests);

    public CategoryInterest getCategorie(String name);

    public Set<Interests> getInterestsByInteresName(List<String> interestsName);

    public UserInfo getUser(long userId);

    public List<Interests> getListInterests(CategoryInterest name);

    
    List<UserInfo> getUsersChat(String city, String country, String sex, int otAge, int doAge, Long group, Long userid);

    void putInterestList(List<Interests> interestLists, Long groupId) throws HibernateException;

    Long getId();

    void putDashboard(Long userid);
}