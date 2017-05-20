package ru.ncteam.levelchat.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.*;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.List;
@Service
public class UserInfoDAO extends AbstractDAO<UserInfo, Long> {
    @Autowired
    private ApplicationUtil util;

    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public UserInfo update(UserInfo entity) {
        return null;
    }

    @Override
    public UserInfo getEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(UserInfo entity) {
        return false;
    }

    @Transactional
    public UserInfo getUserInfoByLogin(String login) throws HibernateException {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/UserInfoByLogin.hql"));
        query.setParameter("login", login);
        return (UserInfo) query.getSingleResult();
    }

    @Transactional
    public List<Chat> getUserChats(String login) throws HibernateException
    {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/UserInfoByLogin.hql"));
        query.setParameter("login", login);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        Set<Chat> chats = userInfo.getChat();
        List<Chat> result = new CopyOnWriteArrayList<Chat>();
        result.addAll(chats);
        return result;
    }

    @Transactional
    public List<Message> getUserMessages(String login) throws HibernateException
    {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/UserInfoByLogin.hql"));
        query.setParameter("login", login);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        Set<Chat> chats = userInfo.getChat();
        List<Message> messages = new CopyOnWriteArrayList<Message>();
        List<Message> buf = new CopyOnWriteArrayList<Message>();
        Iterator<Chat> it = chats.iterator();
        Iterator<Message> innerIt;
        Chat chat;
        Message message;
        while(it.hasNext())
        {
            chat = it.next();
            buf.addAll(chat.getMessages());
            innerIt = buf.iterator();
            while(innerIt.hasNext())
            {
                message = innerIt.next();
                message.setChat(chat);
                message.setUserData(message.getUserData());
                message.setUserInfo(userInfo);
            }
            messages.addAll(buf);
            buf.clear();
        }
        return messages;
    }


    @Transactional
    public List<UserData> getUserData(String login) throws HibernateException
    {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/UserInfoByLogin.hql"));
        query.setParameter("login", login);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        Set<UserData> data = userInfo.getUserData();
        List<UserData> result = new CopyOnWriteArrayList<UserData>();
        result.addAll(data);
        return result;
    }

    @Transactional
    public List<PhotoLib> getUserPhotos(String login) throws HibernateException
    {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/UserInfoByLogin.hql"));
        query.setParameter("login", login);
        UserInfo userInfo = (UserInfo) query.getSingleResult();
        Set<PhotoLib> photos = userInfo.getPhotoLibs();
        List<PhotoLib> result = new CopyOnWriteArrayList<PhotoLib>();
        result.addAll(photos);
        return result;
    }
}
