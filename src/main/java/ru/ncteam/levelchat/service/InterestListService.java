/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncteam.levelchat.service;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncteam.levelchat.entity.InterestList;

/**
 * Сервис, предназначенный для работы с алгоритмом поиска людей по интересам.
 * @author vara
 */
@Service("interestList")
@Transactional
public class InterestListService {
    protected static Logger logger = Logger.getLogger("service");
    
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
    public List<InterestList> getAll(){
        logger.debug("Retrieving all groups interest");
        
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("FROM InterestList");
        
        return query.list();
    }
    
    public InterestList get (Integer listId)
    {
        Session session = sessionFactory.getCurrentSession();
        
        InterestList interestList = (InterestList) session.get(InterestList.class, listId);
        
        return interestList;
    }
    
    public void add(InterestList interestList){
        logger.debug("Adding new group interest");
        
        Session session = sessionFactory.getCurrentSession();
        
        session.save(interestList);
    }
    
    public void delete(Integer idList) {
        logger.debug("Deleting existing person");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing person first
        InterestList interestList = (InterestList) session.get(InterestList.class, idList);

        // Delete
        session.delete(interestList);
    }
    
    public void edit (InterestList interestList){
        logger.debug("Editing existing interestGroup");
        
        Session session = sessionFactory.getCurrentSession();
        
        InterestList excitingGroup = (InterestList) session.get(InterestList.class, interestList.getListId());
        
        excitingGroup.setInterest(interestList.getInterests());
        excitingGroup.setInterestGroup(interestList.getInterestGroup());
        //excitingGroup.setListId; хз, надо или нет
        
        session.save(excitingGroup);
    }
    
}
