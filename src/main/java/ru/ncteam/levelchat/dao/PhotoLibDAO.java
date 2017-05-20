package ru.ncteam.levelchat.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.PhotoLib;
import ru.ncteam.levelchat.entity.UserData;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.List;


@Service
public class PhotoLibDAO extends AbstractDAO<PhotoLib, Long> {
    @Autowired
    private ApplicationUtil util;

    @Override
    public List<PhotoLib> getAll() {
        return null;
    }

    @Override
    public PhotoLib update(PhotoLib entity) {
        return null;
    }

    @Override
    @Transactional
    public PhotoLib getEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(PhotoLib entity) {
        sessionFactory.getCurrentSession();
        return false;
    }

    @Transactional
    public String create(String link, UserInfo userInfo) {
        Long index = indexOf(link,userInfo);
        if(index == null)
        {
            try
            {
                PhotoLib photoLib = new PhotoLib();
                photoLib.setUserInfo(userInfo);
                photoLib.setPhotoRef(link);
                Session s = sessionFactory.getCurrentSession();
                return "success";
            }
            catch(Exception e)
            {
                e=e;
            }
        }
        return "photo already exist";
    }

    @Transactional
    public Long indexOf(String link,UserInfo userInfo) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/getUserPhotoLib.hql"));
        query.setParameter("login", userInfo.getLogin());
        query.setParameter("link", link);
        PhotoLib photoLib = (PhotoLib)query.uniqueResult();
        if(photoLib != null)
        {
            return photoLib.getPhotoId();
        }
        return null;
    }
}
