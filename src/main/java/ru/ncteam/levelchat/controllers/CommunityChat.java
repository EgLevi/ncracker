package ru.ncteam.levelchat.controllers;

import java.util.logging.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ru.ncteam.levelchat.entity.Interests;
import ru.ncteam.levelchat.entity.UserInfo;
import ru.ncteam.levelchat.service.CommunityChatService;
import ru.ncteam.levelchat.service.UserLogService;
import ru.ncteam.levelchat.trial.ChatRepository;
import ru.ncteam.levelchat.trial.NoSuchMessages;
import ru.ncteam.levelchat.trial.Trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.async.DeferredResult;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class CommunityChat {
	@Autowired
	private CommunityChatService communityChatService;
	
	private final ChatRepository chatRepository;
	
	@Autowired
	public CommunityChat(ChatRepository chatRepository)
	{
		this.chatRepository=chatRepository;
	}
	
	
	private final Map<DeferredResult<List<String>>, Long> chatRequests =
			new ConcurrentHashMap<DeferredResult<List<String>>, Long>();
	
	
	
	
	@RequestMapping(value = "/chat/{chatId}/{messageId}",method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<List<String>> trialGet(@PathVariable long messageId) {
		final DeferredResult<List<String>> deferredResult = new DeferredResult<List<String>>();
		this.chatRequests.put(deferredResult,new Long(messageId));
		
		deferredResult.onCompletion(new Runnable() {
			public void run() {
				chatRequests.remove(deferredResult);
			}
		});
		
		List<String> messages;
		try
		{
			messages = chatRepository.getMessage(messageId);
		}
		catch(NoSuchMessages e)
		{
			messages = communityChatService.getMessages(messageId + 1, chatRepository.getLastSaveIndex());
			try {
				messages.addAll(chatRepository.getMessage(chatRepository.getFirstIndex()));
			} 
			catch (NoSuchMessages innnerE) {
			}
		}
		if(messages != null)
		{
			deferredResult.setResult(messages);
		}
		return deferredResult;
	}
	
	
	@RequestMapping(value = "/chat/{chatId}",method = RequestMethod.POST)
	@ResponseBody
	public void trialPost(@PathVariable long chatId, @RequestBody String message) {
		
		List<String> msg = new CopyOnWriteArrayList<String>();
		
		msg.add(message);
		
		chatRepository.setMessage(msg);
		
		
		Iterator<DeferredResult<List<String>>> iterator = this.chatRequests.keySet().iterator();
		DeferredResult<List<String>> dr;
		while(iterator.hasNext())
		{
			dr=iterator.next();
			dr.setResult(msg);
		}
		//сохранили в БД сообщения и записали последний индекс в чате
		chatRepository.setLastSaveIndex(communityChatService.saveMessages(msg, chatId, chatRepository.getLastSaveIndex()));
	}
}
