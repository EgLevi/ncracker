package ru.ncteam.levelchat.dao;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
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

import ru.ncteam.levelchat.entity.*;


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
    public String updateUserInfo(UserInfo userInfo) {

    	try {
    	    //создаем переменную buf для динамического формирования запроса
            StringBuffer buf = new StringBuffer();
            buf.append("update UserInfo set");
            boolean flag=false;
            if(userInfo.getEmail()!=null)
            {
                buf.append(" email=:email");
                flag=true;
            }
            if(userInfo.getCountry()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" country=:country");
                flag=true;
            }
            if(userInfo.getCity()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" city=:city");
                flag=true;
            }
            if(userInfo.getName()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" name=:name");
                flag=true;
            }
            if(userInfo.getSurname()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" surname=:surname");
                flag=true;
            }
            if(userInfo.getAge()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" age=:age");
                flag=true;
            }
            if(userInfo.getSex()!=null)
            {
                if(flag)
                {
                    buf.append(" ,");
                }
                buf.append(" sex=:sex");
            }
            buf.append(" where login=:login");
            String queryString = buf.toString();
            Query query=sessionFactory.getCurrentSession().createQuery(queryString);
            if(userInfo.getEmail()!=null)
            {
                query.setParameter("email", userInfo.getEmail());
            }
            if(userInfo.getCountry()!=null)
            {
                query.setParameter("country", userInfo.getCountry());
            }
            if(userInfo.getCity()!=null)
            {
                query.setParameter("city", userInfo.getCity());
            }
            if(userInfo.getName()!=null)
            {
                query.setParameter("name", userInfo.getName());
            }
            if(userInfo.getSurname()!=null)
            {
                query.setParameter("surname", userInfo.getSurname());
            }
            if(userInfo.getAge()!=null)
            {
                query.setParameter("age", userInfo.getAge());
            }
            if(userInfo.getSex()!=null)
            {
                query.setParameter("sex", userInfo.getSex());
            }
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

    @Transactional
    public Long getId(){
        Query query = sessionFactory.getCurrentSession().createSQLQuery("Select Max(interest_group) FROM interest_list");
        Long id = Long.parseLong(query.getSingleResult().toString()) + 1L;
        return id;
    }

    @Transactional
    @Override
    public void putDashBoard(Long userid) {
        DashboardUrl dashboardUrl = new DashboardUrl();
        Interests interests = new Interests();
        Query query = sessionFactory.getCurrentSession().createQuery("from UserInterest where userId =:userid");
        query.setParameter("userid",userid);
        List<UserInterest> uListInter = (List<UserInterest>) query.list();
        for (int i = 0; i < uListInter.size(); i++) {

            interests.setInterestId(uListInter.get(i).getInterestId());
            dashboardUrl.setBoardId(dashboardUrl.getBoardId()+1);
            dashboardUrl.setInterests(interests);
            dashboardUrl.setUrlInterest("href");
            sessionFactory.getCurrentSession().save(dashboardUrl);
        }
    }
    /**
     *  @param interestLists
     * @param groupid
     */
    @Override
    @Transactional
    public void putInterestList(List<Interests> interestLists, Long groupid) {
        InterestList interestList = new InterestList();
        Interests interests = new Interests();

        for (int i = 0; i<interestLists.size(); i++){

            interests.setInterestId(interestLists.get(i).getInterestId());

            interestList.setInterest(interests);

            interestList.setInterestGroup(groupid);
            sessionFactory.getCurrentSession().save(interestList);
        }
    }

    /**
     *
     * @param city
     * @param country
     * @param sex
     * @param otAge
     * @param doAge
     * @param group
     * @return listOfUsersForChat
     * @throws HibernateException
     */
    @Override
    @Transactional
    public  ArrayList getUsersForChat(String city, String country, String sex, int otAge, int doAge, Long group) throws HibernateException {

        String q = "Select t1.user_id FROM (SELECT uint.user_id, COUNT(uint.user_id) " +
                "   FROM User_interest uint, Interest_list ilist " +
                "   WHERE uint.interest_id = ilist.interest_id " +
                "   AND ilist.INTEREST_GROUP = :groupId " +
                "   GROUP BY uint.user_id ORDER BY COUNT(uint.user_id) DESC) t1";


        Query sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(q);

        sqlQuery.setParameter("groupId", 5);
//        sqlQuery.setParameter("city", "'"+city+"'");
//
//        sqlQuery.setParameter("country", "'"+country+"'");
//        sqlQuery.setParameter("sex", "'"+sex+"'");
//        sqlQuery.setInteger("otAge", otAge);
//        sqlQuery.setInteger("doAge", doAge);
        System.out.println("Айдишники собеседников");
        System.out.println(sqlQuery.getResultList().toString());

        String q1 = "Select Login From User_info where user_id = :userid";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(q1);
        ArrayList ar = new ArrayList();
        for (int i = 0; i<sqlQuery.getResultList().size(); i++){
            query.setParameter("userid",21);
            System.out.println(query.getResultList().toString());
            ar.add(query.getResultList().toString());
        }

        return ar;
    }



}
