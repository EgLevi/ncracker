package ru.ncteam.levelchat.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.ncteam.levelchat.entity.CategoryInterest;
import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.Role;
import ru.ncteam.levelchat.entity.UserInfo;


import java.util.*;

@Repository
public class UserLogDAOImpl implements UserDetailsService, UserLogDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public BCryptPasswordEncoder getBcryptEncoder() {
		return bcryptEncoder;
	}

	public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
		this.bcryptEncoder = bcryptEncoder;
	}


	@Transactional
    public String addUser(UserInfo userInfo,String queryString) {

        if (existUser(userInfo,queryString))
        {
            userInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
            Role role = new Role();
            role.setRole("ROLE_USER");
            role.setId(1);
            Set<Role> setRole = new HashSet<Role>();
            setRole.add(role);
            userInfo.setRoles(setRole);
            try {
                sessionFactory.getCurrentSession().save(userInfo);
                return "success";
            } catch (HibernateException e) {
                return e.getMessage();
            }
        } else {
            return "A user with this login already exist";
        }
    }
    
	@Transactional
    public String updateUserInfo(UserInfo userInfo,String queryString) {

    	try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("email", userInfo.getEmail());
            query.setParameter("country", userInfo.getCountry());
            query.setParameter("city", userInfo.getCity());
            query.setParameter("name", userInfo.getName());
            query.setParameter("surname", userInfo.getSurname());
            query.setParameter("age", userInfo.getAge());
            query.setParameter("sex", userInfo.getSex());
            query.setParameter("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }
	
	@Transactional
    public String updateUserInfoPhoto(UserInfo userInfo, String queryString) {

    	try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("photo_ava", userInfo.getPhoto_ava());
            query.setParameter("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }
	

    @Transactional
    public boolean existUser(UserInfo userInfo,String queryString) {
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", userInfo.getLogin());
        return query.getResultList().isEmpty();
    }
    
    @Transactional
    public boolean existUser(String login,String queryString) {
    	Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", login);
        return query.getResultList().isEmpty();
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        Set<Role> roles = null;
        try 
        {
        	Query query = sessionFactory.getCurrentSession().createQuery("from UserInfo where login=:login");
            query.setParameter("login", username);
            userInfo = (UserInfo) query.uniqueResult();
            roles = userInfo.getRoles();
        } catch (HibernateException e) {
        	e=e;
        }
        catch(Exception e1)
        {
        	e1=e1;
        }

        Iterator<Role> it = roles.iterator();
        Collection<GrantedAuthority> collectionGA = new ArrayList<GrantedAuthority>();
        while (it.hasNext()) {
            collectionGA.add(new SimpleGrantedAuthority(it.next().getRole()));
        }

        UserDetails user = new User(username, userInfo.getPassword(), true, true, true, true, collectionGA);
        return user;
    }

    
    @Transactional
    public List<CategoryInterest> getAllCategory(String queryString) {
    	
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            return query.getResultList();
        } catch (HibernateException e) {
        	return null;
           
        }
    }
    
    @Transactional
    public List<Interests> getInterestsByCatId(long categoryId,String queryString)
    {
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("categoryId", categoryId);
            List<Interests> listInterests = query.getResultList();
            return listInterests;
        } catch (HibernateException e) {
        	return null;
           
        }
    }
      
    @Transactional
    public List<Interests> getInterestsByCatName(String categoryName,String queryString)
    {
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("categoryName", categoryName);
            List<Interests> listInterests = query.getResultList();
            return listInterests;
        } catch (HibernateException e) {
        	return null;
           
        }
    }
      
    @Transactional
    public void putInterestsByCatId(long categoryId,List<Interests> interests,String queryString) throws HibernateException
    {
    	Query query=sessionFactory.getCurrentSession().createQuery(queryString);
    	for(int i=0;i<interests.size();i++)
    	{
    		query.setParameter("interestName", interests.get(i).getInterestName());
    		query.setParameter("interestId", interests.get(i).getInterestId());
            query.executeUpdate();
    	}
    }
        
    @Transactional
    public void updateInterests(List<Interests> interests,String queryString) throws HibernateException
    {
    	Query query=sessionFactory.getCurrentSession().createQuery(queryString);
    	for(int i=0;i<interests.size();i++)
    	{
    		query.setParameter("interestName", interests.get(i).getInterestName());
    		query.setParameter("interestId", interests.get(i).getInterestId());
            query.executeUpdate();
    	}
    }
    
    @Transactional
    public List<Long> putInterests(List<Interests> interests, String categoryName,String queryString) throws HibernateException
    {
    	CategoryInterest categoryInterest = getCategoryInterestByName(categoryName, queryString);
    	Interests interest=null;
    	List<Long> listId = new ArrayList<Long>();
    	for(int i=0;i<interests.size();i++)
    	{
    		interest = interests.get(i);
    		interest.setCategoryInterest(categoryInterest);
    		listId.add((Long)sessionFactory.getCurrentSession().save(interest));
    	}
    	return listId;
    }
    
    @Transactional
    public void deleteInterests(List<Interests> interests,String categoryName, String queryString) throws HibernateException
    {
    	CategoryInterest categoryInterest = getCategoryInterestByName(categoryName,queryString);
    	Interests interest;
    	for(int i=0;i<interests.size();i++)
    	{
    		interest = interests.get(i);
    		interest.setCategoryInterest(categoryInterest);
    		sessionFactory.getCurrentSession().delete(interest);
    	}
    }
    
    @Transactional
    public void deleteCategory(String categoryName, String queryString) throws HibernateException
    {
    	Session session = sessionFactory.getCurrentSession();
    	CategoryInterest categoryInterest = getCategoryInterestByName(categoryName,queryString);
        session.delete(categoryInterest);
    }
    
    @Transactional
    private CategoryInterest getCategoryInterestByName(String categoryName,String queryString)
    {
    	Query query=sessionFactory.getCurrentSession().createQuery(queryString);
    	query.setParameter("categoryName", categoryName);
    	return (CategoryInterest)query.uniqueResult();
    }
    
    @Transactional
    public void putCategoryInterestByName(String categoryName) throws HibernateException
    {
    	CategoryInterest categoryInterest = new CategoryInterest();
    	categoryInterest.setCategoryName(categoryName);
    	sessionFactory.getCurrentSession().save(categoryInterest);
    }

    @Transactional
    public UserInfo getUserByLogin(String login,String queryString) throws HibernateException
    {
        Query query=sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("login", login);
        return (UserInfo)query.uniqueResult();
    }
    

}
