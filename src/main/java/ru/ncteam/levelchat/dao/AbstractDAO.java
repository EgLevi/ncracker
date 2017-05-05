package ru.ncteam.levelchat.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractDAO<E, K> {
    @Autowired
    SessionFactory sessionFactory;

    public abstract List<E> getAll();

    public abstract E update(E entity);

    public abstract E getEntityById(K id);

    public abstract boolean delete(K id);

    public abstract boolean create(E entity);
}
