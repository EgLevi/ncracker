package ru.ncteam.levelchat.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.ncteam.levelchat.entity.Message;

import java.io.*;
import java.util.List;

@Service
public class ChatService {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List getUserInfoByInterests() {
        return null;
    }

    public Message getMessagesByIdChat(long id) {
        String query = "";
        try {
            File file = applicationContext.getResource("queries/messageByIdChat.hql").getFile();
            query = getQueryFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message messages = (Message) sessionFactory.getCurrentSession().createQuery(query);

        return messages;
    }

    private String getQueryFromFile(File file) {

        BufferedReader reader;
        StringBuffer query = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = reader.readLine()) != null) query.append(s);
            return query.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
