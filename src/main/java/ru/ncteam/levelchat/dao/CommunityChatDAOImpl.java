package ru.ncteam.levelchat.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.entity.Message;

@Repository
public class CommunityChatDAOImpl implements CommunityChatDAO {

    @Autowired
    private SessionFactory sessionFactory;

    
    @Transactional
    public long saveMessages(List<String> messages, long chatId, long lastIndex)
    {
    	long currentIndex = lastIndex + 1;
    	Session session = sessionFactory.getCurrentSession();
    	Chat chat = (Chat)session.get(Chat.class.getName(), chatId);
    	Message message = new Message();
    	for(int i=0;i<messages.size();i++)
    	{
    		message.setTextMessage(messages.get(i));
    		message.setChat(chat);
    		message.setId(currentIndex);
    		currentIndex++;
    		sessionFactory.getCurrentSession().save(message);
    	}
    	return currentIndex - 1;
    }
    
    @Transactional
    public List<Message> getMessages(long fromIndex, long toIndex)
    {
    	Query query = sessionFactory.getCurrentSession().createQuery("from Message where id>=:from and id<=:to");
    	query.setParameter("from", fromIndex);
    	query.setParameter("to", toIndex);
    	return query.list();
    }
    
    

}
