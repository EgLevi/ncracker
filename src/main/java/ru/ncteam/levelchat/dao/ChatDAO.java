package ru.ncteam.levelchat.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.List;

@Service
public class ChatDAO extends AbstractDAO<Chat, Long> {

    @Autowired
    private ApplicationUtil util;

    @Override
    @Transactional
    public List<Chat> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Chat update(Chat entity) {
        return null;
    }

    @Override
    @Transactional
    public Chat getEntityById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/chatById.hql"));
        query.setParameter("chatId", id);
        return (Chat) query.getSingleResult();
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @Transactional
    public boolean create(Chat entity) {
        return false;
    }

    @Transactional
    public List<Chat> getAllChatsByLogin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/allUserChatsByLogin.hql"));
        query.setParameter("login", user.getUsername());
        return query.list();
    }
}
