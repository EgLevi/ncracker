package ru.ncteam.levelchat.trial;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class InMemoryChatRepository implements ChatRepository {
	
	private final long maxSize=50;
	
	private long lastSaveIndex;
	
	public long getLastSaveIndex() {
		return lastSaveIndex;
	}

	public void setLastSaveIndex(long lastSaveIndex) {
		this.lastSaveIndex = lastSaveIndex;
	}

	private ConcurrentSkipListMap<Long, String> messages = new ConcurrentSkipListMap<Long, String>();

	public List<String> getMessage(long messageCounter) throws NoSuchMessages {
		if(messages.size() == 0)
		{
			return null;
		}
		if(messages.lastKey().longValue() <= messageCounter)
		{
			return null;
		}
		if(messages.firstKey().longValue() < messageCounter)
		{
			throw new NoSuchMessages();
		}
		Collection<String> result = messages.subMap(messageCounter+1, messages.lastKey()).values();
		return (List<String>)result;
	}

	public void setMessage(List<String> messages) {
		if (messages == null)
		{
			return;
		}
		if(messages.size() == 0)
		{
			return;
		}
		long lastKey;
		if(this.messages.size() != 0)
		{
			lastKey = this.messages.lastKey()+1;
		}
		else
		{
			lastKey = 0;
		}
		this.messages.putAll(this.listToMap(messages,lastKey));
		if(this.messages.size() > maxSize)
		{
			for(long dif = this.messages.size()- maxSize;dif > 0;dif--)
			{
				this.messages.remove(this.messages.firstKey());
			}
		}
	}
	
	private Map<Long,String> listToMap(List<String> listStr, Long firstKey)
	{
		if(listStr == null)
		{
			return null;
		}
		if(listStr.size() == 0)
		{
			return null;
		}
		Long fK=firstKey;
		Map<Long,String> map = new Hashtable<Long,String>();
		Iterator<String> it = listStr.iterator();
		String str;
		while(it.hasNext())
		{
			str = it.next();
			map.put(fK, str);
			fK++;
		}
		return map;
	}
	
	public Long getLastIndex()
	{
		return messages.lastKey();
	}

	public Long getFirstIndex()
	{
		return messages.firstKey();
	}
}
