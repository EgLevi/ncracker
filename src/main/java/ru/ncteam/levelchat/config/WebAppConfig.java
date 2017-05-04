package ru.ncteam.levelchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("ru.ncteam.levelchat")
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/*@Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize("500MB");
	    factory.setMaxRequestSize("500MB");
	    return factory.createMultipartConfig();
	}
	
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }*/
	
	/*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setCache(false);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

	
	
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(50000000);
        return multipartResolver;
    }*/
	
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setCache(false);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * Supports FileUploads.
     * @return 
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(50000000);
        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
    
    /*@Bean
    @Scope(value="session", proxyMode = ScopedProxyMode.INTERFACES)
    public TrialImpl getTrailImpl() {
        return new TrialImpl();
    }*/
    
    
    
    

}
