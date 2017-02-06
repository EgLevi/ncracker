package net.ncteam.levelchat.authentication;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.security.core.GrantedAuthority;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{
	
	public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		try
		{
			OutputStream logFile = new FileOutputStream("c:/MyLog.txt");
			logFile.write("we have been there".getBytes());
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("adminpage");
        }
        else
        {
        	response.sendRedirect("userpage");
        }
    }

}
