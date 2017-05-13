package ru.ncteam.levelchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.ncteam.levelchat.dao.ChatDAO;
import ru.ncteam.levelchat.entity.Chat;
import ru.ncteam.levelchat.entity.UserInfo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class ChatService {

    @Autowired
    ChatDAO chatDAO;

    private static ConcurrentHashMap<Long, List<String>> map = new ConcurrentHashMap<Long,List<String>>();

    private long maxSizeMap;

    public long getMaxSizeMap()
    {
        return maxSizeMap;
    }

    public void setMaxSizeMap(long size)
    {
        maxSizeMap = size;
    }

    public ChatService()
    {
        maxSizeMap = -1;
    }

    public ChatService(long size)
    {
        //расширяемый
        if (size>0)
        {
            maxSizeMap = size;
        }
        else
        {
            maxSizeMap=size;
        }
    }

    private void getAllChatsGroupInformation()
    {
        List<Chat> chats = chatDAO.getAllWithUsers();
        List<String> listLogin = new CopyOnWriteArrayList<String>();
        for(Chat chat : chats)
        {
            for(UserInfo user : chat.getUsers())
            {
                listLogin.add(user.getLogin());
            }
            map.put(chat.getChatId(),listLogin);
            listLogin.clear();
        }
    }

    public void addUserInChat(Long chatId, String login)
    {
    }




}
