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
    public String addUser(UserInfo userInfo) {

        if (existUser(userInfo))
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
    public String updateUserInfo(UserInfo userInfo) {

    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("update UserInfo set "
            		+ "email=:email,"
            		+ " country=:country,"
            		+ " city=:city,"
            		+ " name=:name,"
            		+ " surname=:surname,"
            		+ " age=:age,"
            		+ " sex=:sex"
            		+ " where login=:login");
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
    public String updateUserInfoPhoto(UserInfo userInfo) {

    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("update UserInfo set "
            		+ "photo_ava=:photo_ava "
            		+ "where login=:login");
            query.setLong("photo_ava", userInfo.getPhoto_ava());
            query.setString("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }
	

    @Transactional
    public boolean existUser(UserInfo userInfo) {
        return sessionFactory.getCurrentSession().createQuery("from UserInfo u where u.login='"
                + userInfo.getLogin() + "'").getResultList().isEmpty();
    }
    
    @Transactional
    public boolean existUser(String login) {
        return sessionFactory.getCurrentSession().createQuery("from UserInfo u where u.login='"
                + login + "'").getResultList().isEmpty();
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String strQuery = "from UserInfo u where u.login='" + username + "'";
        UserInfo userInfo = null;
        Set<Role> roles = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
            userInfo = (UserInfo) query.uniqueResult();
            roles = userInfo.getRoles();
        } catch (HibernateException e) {
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
    public long getIdImg() {
    	try{
        	Query query = sessionFactory.getCurrentSession().createQuery("select i.value from IdImg i where i.id=0");
        	List<Long> ll = query.list();
        	long idImg = ((Long)query.list().get(0)).longValue();
            return idImg;
    	}
    	catch(Exception e)
    	{
    		e=e;
    		return 0;
    	}
    }
    
    @Transactional
    public void setIdImg(long idImg) {
    	
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("update IdImg set "
            		+ "value=:value "
            		+ "where id=0");
            query.setLong("value", idImg);
            query.executeUpdate();
        } catch (HibernateException e) {
           
        }
    }
    
    @Transactional
    public List<CategoryInterest> getAllCategory() {
    	
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("from CategoryInterest");
            return query.getResultList();
        } catch (HibernateException e) {
        	return null;
           
        }
    }
    
    @Transactional
    public List<Interests> getInterestsByCatId(long categoryId)
    {
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("from Interests where categoryInterest.categoryId=:categoryId");
            query.setParameter("categoryId", categoryId);
            List<Interests> listInterests = query.getResultList();
            return listInterests;
        } catch (HibernateException e) {
        	return null;
           
        }
    }
      
    @Transactional
    public List<Interests> getInterestsByCatName(String categoryName)
    {
    	try {
            Query query=sessionFactory.getCurrentSession().createQuery("from Interests where categoryInterest.categoryName=:categoryName");
            query.setParameter("categoryName", categoryName);
            List<Interests> listInterests = query.getResultList();
            return listInterests;
        } catch (HibernateException e) {
        	return null;
           
        }
    }
      
    @Transactional
    public void putInterestsByCatId(long categoryId,List<Interests> interests) throws HibernateException
    {
    	Query query=sessionFactory.getCurrentSession().createQuery("update Interests set "
    			+ "interestName=:interestName "
        		+ "where interestId=:interestId");
    	for(int i=0;i<interests.size();i++)
    	{
    		query.setParameter("interestName", interests.get(i).getInterestName());
    		query.setParameter("interestId", interests.get(i).getInterestId());
            query.executeUpdate();
    	}
    }
        
    @Transactional
    public void updateInterests(List<Interests> interests) throws HibernateException
    {
    	Query query=sessionFactory.getCurrentSession().createQuery("update Interests set "
    			+ "interestName=:interestName "
        		+ "where interestId=:interestId");
    	for(int i=0;i<interests.size();i++)
    	{
    		query.setParameter("interestName", interests.get(i).getInterestName());
    		query.setParameter("interestId", interests.get(i).getInterestId());
            query.executeUpdate();
    	}
    }
    
    @Transactional
    public List<Long> putInterests(List<Interests> interests, String categoryName) throws HibernateException
    {
    	CategoryInterest categoryInterest = getCategoryInterestByName(categoryName);
    	Interests interest;
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
    public void deleteInterests(List<Interests> interests,String categoryName) throws HibernateException
    {
    	CategoryInterest categoryInterest = getCategoryInterestByName(categoryName);
    	Interests interest;
    	for(int i=0;i<interests.size();i++)
    	{
    		interest = interests.get(i);
    		interest.setCategoryInterest(categoryInterest);
    		sessionFactory.getCurrentSession().delete(interest);
    	}
    }
    
    @Transactional
    public void deleteCategory(String categoryName) throws HibernateException
    {
    	Session session = sessionFactory.getCurrentSession();
    	Query query=session.createQuery("from CategoryInterest where categoryName=:categoryName");
    	query.setParameter("categoryName", categoryName);
    	CategoryInterest categoryInterest = (CategoryInterest)query.uniqueResult();
        session.delete(categoryInterest);
    }
    
    @Transactional
    private CategoryInterest getCategoryInterestByName(String categoryName)
    {
    	Query query=sessionFactory.getCurrentSession().createQuery("from CategoryInterest where categoryName=:categoryName");
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
    

}
