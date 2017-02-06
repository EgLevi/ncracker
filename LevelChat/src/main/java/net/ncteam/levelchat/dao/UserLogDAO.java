package net.ncteam.levelchat.dao;

import java.util.List;
import net.ncteam.levelchat.domain.UsersLog;

public interface UserLogDAO {

	public void addUser(UsersLog userLog);

	public List<UsersLog> listUser();

	public void removeUser(Integer iduserlog);
	
	public boolean existUser(String login, String password); 
}