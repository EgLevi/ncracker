package ru.ncteam.levelchat.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.Message;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class MessageDAO extends AbstractDAO<Message, Long> {
    @Autowired
    private ApplicationUtil util;

    @Override
    @Transactional
    public List<Message> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/allMessages.hql"));
        return query.list();
    }

    @Override
    @Transactional
    public Message update(Message entity) {
        return null;
    }

    @Override
    @Transactional
    public Message getEntityById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @Transactional
    public boolean create(Message entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    public List<Message> allMessagesByChatId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/messageByIdChat.hql"));
        query.setParameter("chatId", id);
        return query.list();
    }

    @Transactional
    public List<Message> allMessagesByChatIdWithInfo(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/messageByIdChat.hql"));
        query.setParameter("chatId", id);
        List<Message> messages = query.list();
        Iterator<Message> it = messages.iterator();
        Message message;
        while(it.hasNext())
        {
            message=it.next();
            message.setUserInfo(message.getUserInfo());
            message.setUserData(message.getUserData());
        }
        return messages;
    }
}
