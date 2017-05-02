package ru.ncteam.levelchat.dao;

import java.util.List;

import ru.ncteam.levelchat.entity.Message;

public interface CommunityChatDAO {
	public long saveMessages(List<String> messages, long chatId, long lastIndex);
	
	public List<Message> getMessages(long fromIndex, long toIndex);

}
