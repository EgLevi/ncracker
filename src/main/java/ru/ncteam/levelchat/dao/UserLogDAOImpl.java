package ru.ncteam.levelchat.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import ru.ncteam.levelchat.entity.MessageKey;
import ru.ncteam.levelchat.entity.Role;
import ru.ncteam.levelchat.entity.UserInfo;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserLogDAOImpl implements UserDetailsService, UserLogDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    //@Autowired
    //private Session session;
    
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public BCryptPasswordEncoder getBcryptEncoder() {
		return bcryptEncoder;
	}


	public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
		this.bcryptEncoder = bcryptEncoder;
	}
	
	@Transactional
	public List<Interests> getListInterests(CategoryInterest name)
	{
		List<Interests> l = new ArrayList<Interests>();
		List<Interests> mylist = new ArrayList<Interests>();
		try{// u where u.CATEGORY_ID="+id
			l = sessionFactory.getCurrentSession().createQuery("from Interests").list();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
            return l;
		}
		for (Interests curr: l)
		{			
			if(curr.getCategoryInterest().getCategoryName().equals(name.getCategoryName()))
			{
				System.out.print(curr.getInterestName() + " ");
				mylist.add(curr);
			}
		}
		return mylist;
	}
	
	@Transactional
	public CategoryInterest getCategorie(String name)
	{
		//List<CategoryInterest> cat = new ArrayList<CategoryInterest>();
		List<CategoryInterest> exec = new ArrayList<CategoryInterest>();
		try
		{
			exec = sessionFactory.getCurrentSession().createQuery("from CategoryInterest").list();			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
            return exec.get(0);
		}
		for (CategoryInterest curr: exec)
		{
			System.out.println(curr.getCategoryName());
			if(curr.getCategoryName().equals(name))
			{
				return curr;
			}
		}
		return exec.get(0);
	}
	
	@Transactional
	public String addInterest(Interests inter)
	{
		try
		{			
			sessionFactory.getCurrentSession().save(inter);			
		}
		catch(Exception e) {
            return e.getMessage();			
		}
		return ("success Insert interes");
	}
	
	@Transactional
	public List<CategoryInterest> getCategory()
	{
		//List<CategoryInterest> myList = new ArrayList<CategoryInterest>();	
		//CategoryInterest inter = new CategoryInterest();
		List<CategoryInterest> exec = new ArrayList<CategoryInterest>();
		try
		{					
			//Criteria criteria = session.createCriteria(CategoryInterest.class);
			//myList = criteria.add(Restrictions.isNotNull("categoryId")).list();
			
			//inter = (CategoryInterest)criteria.add(Restrictions.eq("categoryId","1")).uniqueResult();
			//inter = sessionFactory.getCurrentSession().get(CategoryInterest.class, 1);
			Query query = sessionFactory.getCurrentSession().createQuery("from CategoryInterest");
			exec = query.list();
			
			System.out.println(exec.get(0).getCategoryName());
		}
		catch(Exception e) {
            System.out.println(e.getMessage());
            return exec;
		}
		System.out.println("succes");
		return exec;
	}
	
	@Transactional
	public String addCategory(CategoryInterest catInteres)
	{
		try
		{			
			sessionFactory.getCurrentSession().save(catInteres);			
		}
		catch(Exception e) {
            return e.getMessage();			
		}
		return "success";
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
            		+ " age=:age,"
            		+ " sex=:sex"
            		+ " where login=:login");
            query.setParameter("email", userInfo.getEmail());
            query.setParameter("country", userInfo.getCountry());
            query.setParameter("city", userInfo.getCity());
            query.setParameter("name", userInfo.getName());
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
            
            query.setBinary("photo_ava",userInfo.getPhoto_ava().getBytes(0, (int)userInfo.getPhoto_ava().length()));
            query.setString("login", userInfo.getLogin());
            query.executeUpdate();
            return "success";
        } catch (HibernateException e) {
            return e.getMessage();
        } catch (SQLException e) {
        	return e.getMessage();
		}
    }
	

    @Transactional
    public boolean existUser(UserInfo userInfo) {
        return sessionFactory.getCurrentSession().createQuery("from UserInfo u where u.login='"
                + userInfo.getLogin() + "'").list().isEmpty();
    }

    /*@SuppressWarnings("unchecked")
    public List<UsersLog> listUser() {

        return sessionFactory.getCurrentSession().createQuery("select * from UsersLog")
                .list();
    }*/

    //��� ������� ���� ����� �� ������������
    /*public void removeUser(Integer iduserlog) {
        UsersLog userLog = (UsersLog) sessionFactory.getCurrentSession().load(
                UsersLog.class, iduserlog);
        if (null != userLog) {
            sessionFactory.getCurrentSession().delete(userLog);
        }

    }*/

    @Transactional
    public List<String> getMessages(String username)
            throws DataAccessException {

        Integer idIserLog = (Integer) sessionFactory.getCurrentSession().createQuery(
                "select u.iduserlog from UsersLog u where u.login='"
                        + username + "'").list().get(0);
        Integer idChat = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.idchat from UserInfo u where u.iduserlog='"
                        + idIserLog + "'").list().get(0);
        Integer idMess = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.id_mess from Chat u where u.idchat='"
                        + idChat + "'").list().get(0);
        List<String> messages = sessionFactory.getCurrentSession().createQuery(
                "u.message from Messages u where u.pk_idmess.id_mess='"
                        + idMess + "'").list();
        return messages;
    }

    @Transactional
    public List<String> getMessages(String username, int mid)
            throws DataAccessException {

        Integer idIserLog = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.iduserlog from UsersLog u where u.login='"
                        + username + "'").list().get(0);
        Integer idChat = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.idchat from UserInfo u where u.iduserlog='"
                        + idIserLog + "'").list().get(0);
        Integer idMess = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.id_mess from Chat u where u.idchat='"
                        + idChat + "'").list().get(0);
        List<String> messages = sessionFactory.getCurrentSession().createQuery(
                "u.message from Messages u where u.pk_idmess.id_mess='"
                        + idMess + "' and u.pk_idmess.id>='" + mid + "'").list();
        return messages;
    }

    @Transactional
    public void addMessage(String username, String message, int mid)
            throws DataAccessException {
        File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        Integer idIserLog = (Integer) sessionFactory.getCurrentSession().createQuery(
                "select u.iduserlog from UsersLog u where u.login='"
                        + username + "'").list().get(0);
        Integer idChat = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.idchat from UserInfo u where u.iduserlog='"
                        + idIserLog + "'").list().get(0);
        Integer idMess = (Integer) sessionFactory.getCurrentSession().createQuery(
                "u.id_mess from Chat u where u.idchat='"
                        + idChat + "'").list().get(0);
        MessageKey mk = new MessageKey();
        mk.setId_mess(idMess);
        mk.setId(mid + 1);
        /*Messages mes = new Messages();
        mes.setMessageKey(mk);
		mes.setMessage(message);
		mes.setRecepient("a");
		sessionFactory.getCurrentSession().save(mes);*/
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
            e = e;
        }

        Iterator<Role> it = roles.iterator();
        Collection<GrantedAuthority> collectionGA = new ArrayList<GrantedAuthority>();
        while (it.hasNext()) {
            collectionGA.add(new SimpleGrantedAuthority(it.next().getRole()));
        }

        UserDetails user = new User(username, userInfo.getPassword(), true, true, true, true, collectionGA);
        return user;
    }


}
