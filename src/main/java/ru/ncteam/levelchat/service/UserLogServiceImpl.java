package ru.ncteam.levelchat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.ncteam.levelchat.dao.UserLogDAO;
import ru.ncteam.levelchat.entity.UserInfo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserLogServiceImpl implements UserLogService {
	
	private static String UPLOADED_FOLDER = "C://Users//user//workspaceJee//LC//ncracker//src//main//webapp//resources//images//";

	@Autowired
    private UserLogDAO userLogDAO;
	
	/*@Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;*/

    public void setUserLogDAO(UserLogDAO userLogDAO) {
        this.userLogDAO = userLogDAO;
    }
    
    
    public boolean checkLogin(String login)
    {
    	return userLogDAO.existUser(login);
    }

    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo);
    }
    
    public String updateUserInfo(UserInfo userInfo) {
        return userLogDAO.updateUserInfo(userInfo);
    }
    
    public String updateUserInfoPhoto(UserInfo userInfo, MultipartFile photo_ava) {
    	long idImg = userLogDAO.getIdImg();
    	idImg++;
    	userInfo.setPhoto_ava(idImg);
    	Path path = Paths.get(UPLOADED_FOLDER + idImg+".jpg");
    	try {
			Files.write(path, photo_ava.getBytes());
			if(userLogDAO.updateUserInfoPhoto(userInfo).equals("success"))
	    	{
	    		userLogDAO.setIdImg(idImg);
	    		return (""+idImg+".jpg");
	    	}
	    	else
	    	{
	    		return "fail";
	    	}
		} catch (IOException e) {
			return "fail";
		}
    	
    }

    /*public List<UsersLog> listUser() {

        return userLogDAO.listUser();
    }

    public void removeUser(Integer iduserlog) {
        userLogDAO.removeUser(iduserlog);
    }*/


    /*public List<String> getMessages(String username) {
        return userLogDAO.getMessages(username);
    }

    public List<String> getMessages(String username, int mid) {
        return userLogDAO.getMessages(username, mid);
    }

    public void addMessage(String username, String messages, int mid) {
        File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write("in Service\n");
            fr.close();

        } catch (IOException e) {
        }
        userLogDAO.addMessage(username, messages, mid);
    }*/
    
    
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userLogDAO.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        //authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
