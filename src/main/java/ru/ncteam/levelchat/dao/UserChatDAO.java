package ru.ncteam.levelchat.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.utils.ApplicationUtil;
import ru.ncteam.levelchat.entity.UserChat;

import java.util.Iterator;
import java.util.List;

@Service
public class UserChatDAO extends AbstractDAO<UserChat, Long> {
    @Autowired
    private ApplicationUtil util;

    @Override
    public List<UserChat> getAll() {
        return null;
    }

    @Override
    public UserChat update(UserChat entity) {
        return null;
    }

    @Override
    public UserChat getEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @Transactional
    public boolean create(UserChat entity) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(entity);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Transactional
    public void setReadableForUser(String login, Long chatId) throws HibernateException {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/getUserChatByLoginChatId.hql"));
        query.setParameter("login", login);
        query.setParameter("chatId", chatId);
        UserChat userChat = (UserChat) query.getSingleResult();
        userChat.setUnreadable(false);
        sessionFactory.getCurrentSession().save(userChat);
    }


    @Transactional
    public void setReadable(Long chatId) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(util.getStringFromFile("hql/getUserChatsByChatId.hql"));
        query.setParameter("chatId", chatId);
        List<UserChat> userChats = query.getResultList();
        Iterator<UserChat> it = userChats.iterator();
        UserChat userChat;
        while (it.hasNext()) {
            userChat = it.next();
            userChat.setUnreadable(true);
            session.saveOrUpdate(userChat);
        }
    }

    public UserChat createAndReturn(UserChat entity) {
        try {
            return (UserChat) sessionFactory.getCurrentSession().save(entity);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new UserChat();

    }

}
