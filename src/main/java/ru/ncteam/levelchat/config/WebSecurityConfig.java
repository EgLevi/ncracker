package ru.ncteam.levelchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.ncteam.levelchat.authentication.AuthenticationSuccessHandlerImpl;
import ru.ncteam.levelchat.dao.UserLogDAO;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserLogDAO userLogDAOImpl;
	
	@Autowired
    private PasswordEncoder bcryptEncoder;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
                .authorizeRequests()
                .antMatchers("/","/logout*","/index*","/userpage*","/chats*","/postregistration","/postregistrationPhoto","/search*").hasAnyRole("USER","ADMIN")
                .antMatchers("/adminpage*").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=true")
                .successHandler(getAuthenticationSuccessHandlerImpl())
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .logoutUrl("/logout")
                .deleteCookies("CookieForRemember")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeCookieName("CookieForRemember")
                .tokenValiditySeconds(2419200)
                .rememberMeParameter("remeber_me_parameter")
                .and()
                .csrf().ignoringAntMatchers("/","/logout*","/index*","/userpage*","/registration*","/registration/**","/postregistration","/postregistrationPhoto","/adminpage*","/adminpage/**","/chats/**","/chats*");
    }
    
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider((AuthenticationProvider) userLogDAOImpl);
        auth.userDetailsService((UserDetailsService)userLogDAOImpl);
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService((UserDetailsService)userLogDAOImpl).passwordEncoder(bcryptEncoder);
    }
    
    @Bean
    public AuthenticationSuccessHandlerImpl getAuthenticationSuccessHandlerImpl() {
    	AuthenticationSuccessHandlerImpl successHandler = new AuthenticationSuccessHandlerImpl(); 
        return successHandler;
    }
    
}
