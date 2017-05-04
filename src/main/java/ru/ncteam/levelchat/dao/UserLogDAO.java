package ru.ncteam.levelchat.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ncteam.levelchat.entity.UserInfo;


public interface UserLogDAO {

	public String addUser(UserInfo userInfo);
	
	public String updateUserInfo(UserInfo userInfo);
	
	public String updateUserInfoPhoto(UserInfo userInfo);

	public String editUserInfo(UserInfo userInfo); //МЕТОД ОБНОВЛЕНИЯ ДАННЫХ

	//public List<UsersLog> listUser();

	//public void removeUser(Integer iduserlog);
	
	public boolean existUser(UserInfo userInfo);
	
	public boolean existUser(String login); 
	
	//public List<String> getMessages(String username);
	
	//public List<String> getMessages(String username,int mid);
	
	//public void addMessage(String username, String message, int mid);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public long getIdImg();
    
    public void setIdImg(long idImg);

	public UserInfo getUserInfo(String username);

}