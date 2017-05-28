package ru.ncteam.levelchat.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.UserData;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.List;


@Service
public class UserDataDAO extends AbstractDAO<UserData, Long> {
    @Autowired
    private ApplicationUtil util;

    @Override
    public List<UserData> getAll() {
        return null;
    }

    @Override
    public UserData update(UserData entity) {
        return null;
    }

    @Override
    @Transactional
    public UserData getEntityById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/userDataById.hql"));
        query.setParameter("dataId", id);
        return (UserData) query.getSingleResult();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(UserData entity) {
        sessionFactory.getCurrentSession();
        return false;
    }

    @Transactional
    public Long create(String link, UserInfo userInfo) {
        Long index = indexOf(link,userInfo);
        if(index == null)
        {
            UserData userData = new UserData();
            userData.setUserInfo(userInfo);
            userData.setDataLink(link);
            Session s = sessionFactory.getCurrentSession();
            return (Long) s.save(userData);
        }
        return index;
    }

    @Transactional
    public Long indexOf(String link,UserInfo userInfo) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/getUserData.hql"));
        query.setParameter("login", userInfo.getLogin());
        query.setParameter("link", link);
        UserData userData = (UserData)query.uniqueResult();
        if(userData != null)
        {
            return userData.getDataId();
        }
        return null;
    }
}
