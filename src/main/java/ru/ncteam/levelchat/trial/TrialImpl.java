package ru.ncteam.levelchat.trial;

import org.springframework.stereotype.Service;


public class TrialImpl implements Trial{
	
	public int counter;
	
	public TrialImpl()
	{
		counter=0;
	}
	
	public void incCounter()
	{
		counter++;
	}
	
	public int getCounter()
	{
		return counter;
	}

}
