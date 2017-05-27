package ru.ncteam.levelchat.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.Levels;
import ru.ncteam.levelchat.utils.ApplicationUtil;

import java.util.List;
@Service
public class LevelsDAO extends AbstractDAO<Levels, Long> {

    @Autowired
    private ApplicationUtil util;

    @Override
    public List<Levels> getAll() {
        return null;
    }

    @Override
    public Levels update(Levels entity) {
        return null;
    }

    @Override
    @Transactional
    public Levels getEntityById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(util.getStringFromFile("hql/levelById.hql"));
        query.setParameter("id", id);
        return (Levels) query.getSingleResult();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(Levels entity) {
        return false;
    }
}
