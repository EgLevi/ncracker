package ru.ncteam.levelchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


@Configuration
@EnableWebMvc
@ComponentScan("ru.ncteam.levelchat")
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
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
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/photo/**").addResourceLocations("/photo/");
        registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(1000 * 60 * 5L);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    


    @Bean
    public MappingJackson2HttpMessageConverter jacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jacksonHttpMessageConverter.setPrettyPrint(true);
        return jacksonHttpMessageConverter;
    }

    @Bean
    public HashMap<Long, ConcurrentLinkedQueue<DeferredResult<String>>> getHashMap() {
        return new HashMap<Long, ConcurrentLinkedQueue<DeferredResult<String>>>();
    }

}
