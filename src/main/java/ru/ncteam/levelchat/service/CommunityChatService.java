package ru.ncteam.levelchat.service;

import java.util.List;

public interface CommunityChatService {
	
	public long saveMessages(List<String> messages, long chatId, long lastIndex);
	
	public List<String> getMessages(long fromIndex, long toIndex);

}
