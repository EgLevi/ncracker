package net.ncteam.levelchat.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.ncteam.levelchat.dao.UserLogDAO;
import net.ncteam.levelchat.domain.UsersLog;
 
@Service
public class UserLogServiceImpl implements UserLogService {
 
    private UserLogDAO userLogDAO;
    
    public void setUserLogDAO (UserLogDAO userLogDAO)
    {
    	this.userLogDAO = userLogDAO;
    }
 
    @Transactional
    public void addUser(UsersLog contact) {
        userLogDAO.addUser(contact);
    }
 
    @Transactional
    public List<UsersLog> listUser() {
 
        return userLogDAO.listUser();
    }
 
    @Transactional
    public void removeUser(Integer iduserlog) {
    	userLogDAO.removeUser(iduserlog);
    }
    
    @Transactional
    public boolean existUser(String login, String password) {
    	return userLogDAO.existUser(login,password);
    }
}
