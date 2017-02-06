package net.ncteam.levelchat.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.ncteam.levelchat.domain.UsersLog;
import net.ncteam.levelchat.domain.Role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserLogDAOImpl implements UserLogDAO, UserDetailsService {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory (SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public void addUser(UsersLog userLog) {
		sessionFactory.getCurrentSession().save(userLog);
	}
	
	public boolean existUser(String login, String password) {
		return sessionFactory.getCurrentSession().createQuery("from UsersLog where login='"+login+"' and password='"+password+"'").list().isEmpty();
	}

	@SuppressWarnings("unchecked")
	public List<UsersLog> listUser() {

		return sessionFactory.getCurrentSession().createQuery("from UsersLog")
			.list();
	}

	public void removeUser(Integer iduserlog) {
		UsersLog userLog = (UsersLog) sessionFactory.getCurrentSession().load(
				UsersLog.class, iduserlog);
		if (null != userLog) {
			sessionFactory.getCurrentSession().delete(userLog);
		}

	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
	       throws UsernameNotFoundException, DataAccessException
	{
		
		UsersLog userLog = (UsersLog)sessionFactory.getCurrentSession().createQuery(
				"from UsersLog u where u.login='"
				+username+"'").uniqueResult();
		
		
		Set<Role> roles= userLog.getRoles();
		Iterator<Role> it = roles.iterator();
		Collection<GrantedAuthority> collectionGA = new ArrayList<GrantedAuthority>();
		while (it.hasNext())
		{
			collectionGA.add(new GrantedAuthorityImpl(it.next().getRole()));
		}
		UserDetails user = new User(username, userLog.getPassword(), true, true, true, true, collectionGA);
		return user;
	}
}
