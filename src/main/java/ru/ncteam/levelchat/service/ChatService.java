package ru.ncteam.levelchat.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ChatService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationUtil util;

    @Bean
    public HashMap<Long, ConcurrentLinkedQueue<DeferredResult<String>>> getResultMap(){
        return new HashMap<>();
    }

    @Transactional
    public List getUserInfoByInterests() {
        return null;
    }

}
