package ru.ncteam.levelchat.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:datasource.properties"})
@EnableTransactionManagement
public class HibernateConfig {
    /**
     * Привязка осуществляется к файлу datasource.properties в папке resources. Для того чтобы поменять значения на свои
     * достаточно отредактировать datasource.properties
     */
    @Autowired
    private Environment hibernateProps;

    //Дальше не трогай - убъёт

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            Class.forName(hibernateProps.getProperty("dataSource.DriverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setDriverClassName(hibernateProps.getProperty("dataSource.DriverClassName"));
        dataSource.setUrl(hibernateProps.getProperty("dataSource.Url"));
        dataSource.setUsername(hibernateProps.getProperty("dataSource.Username"));
        dataSource.setPassword(hibernateProps.getProperty("dataSource.Password"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"ru.ncteam.levelchat"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        return properties;
    }
}
