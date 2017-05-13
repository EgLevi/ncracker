package ru.ncteam.levelchat.dao;

import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.List;


public interface UserLogDAO {

    String addUser(UserInfo userInfo, String queryString);

    String updateUserInfo(UserInfo userInfo, String queryString);

    String updateUserInfoPhoto(UserInfo userInfo, String queryString);

    boolean existUser(UserInfo userInfo, String queryString);

    boolean existUser(String login, String queryString);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<CategoryInterest> getAllCategory(String queryString);

    List<Interests> getInterestsByCatId(long categoryId, String queryString);

    List<Interests> getInterestsByCatName(String categoryName, String queryString);

    void putInterestsByCatId(long categoryId, List<Interests> interests, String queryString) throws HibernateException;

    List<Long> putInterests(List<Interests> interests, String categoryName, String queryString) throws HibernateException;

    void deleteInterests(List<Interests> interests, String categoryName, String queryString) throws HibernateException;

    void deleteCategory(String categoryName, String queryString) throws HibernateException;

    void updateInterests(List<Interests> interests, String queryString) throws HibernateException;

    void putCategoryInterestByName(String categoryName) throws HibernateException;

    UserInfo getUserByLogin(String login, String queryString) throws HibernateException;


}