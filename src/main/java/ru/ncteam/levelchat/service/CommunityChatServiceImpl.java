package ru.ncteam.levelchat.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ncteam.levelchat.dao.CommunityChatDAO;
import ru.ncteam.levelchat.entity.Message;

@Service
public class CommunityChatServiceImpl implements CommunityChatService {

	
	@Autowired
	CommunityChatDAO chatDAO;
	
	public long saveMessages(List<String> messages, long chatId, long lastIndex)
	{
		return chatDAO.saveMessages(messages, chatId, lastIndex);
	}
	
	public List<String> getMessages(long fromIndex, long toIndex)
	{
		List<Message> messages = chatDAO.getMessages(fromIndex, toIndex);
		Iterator<Message> it = messages.iterator();
		Message message;
		List<String> messageText = new ArrayList<String>();
		while(it.hasNext())
		{
			message = it.next();
			messageText.add(message.getTextMessage());
		}
		return messageText;
	}
}
