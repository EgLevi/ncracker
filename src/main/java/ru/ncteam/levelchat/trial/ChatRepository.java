package ru.ncteam.levelchat.trial;

import java.util.List;

public interface ChatRepository {

	public List<String> getMessage(long messageId) throws NoSuchMessages;
	public void setMessage(List<String>  message);
	
	public Long getLastIndex();
	public Long getFirstIndex();
	
	public long getLastSaveIndex();

	public void setLastSaveIndex(long lastSaveIndex);

}
