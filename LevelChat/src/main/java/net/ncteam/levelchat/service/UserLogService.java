package net.ncteam.levelchat.service;

import java.util.List;
import net.ncteam.levelchat.domain.UsersLog;

public interface UserLogService {

	public void addUser(UsersLog contact);

	public List<UsersLog> listUser();

	public void removeUser(Integer iduserlog);
	
	public boolean existUser(String login, String password);
}