package ru.ncteam.levelchat.trial;

import org.osgi.service.component.annotations.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype")
public class TrialImpl implements Trial{
	
	public int counter;
	
	public int getCounter()
	{
		return counter;
	}
	
	public void setCounter(int counter)
	{
		this.counter=counter;
	}

}
