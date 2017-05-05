package ru.ncteam.levelchat.service;

import javafx.application.Application;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.ncteam.levelchat.entity.Message;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.io.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ChatService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ApplicationUtil util;

    @Bean
    Queue<DeferredResult<String>> getDeferredResults() {
        return new ConcurrentLinkedQueue<>();
    }

    @Transactional
    public List getUserInfoByInterests() {
        return null;
    }

    public Message getMessagesByIdChat(long id) {
        String query = util.getStringFromFile("hql/messageByIdChat.hql");
        return (Message) sessionFactory.getCurrentSession().createQuery(query);
    }


}
