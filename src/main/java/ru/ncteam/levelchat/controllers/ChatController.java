package ru.ncteam.levelchat.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import ru.ncteam.levelchat.dao.MessageDAO;
import ru.ncteam.levelchat.entity.Message;
import ru.ncteam.levelchat.listener.ChatPublisher;

import java.util.List;
import java.util.Queue;

@Controller
public class ChatController {
    @Autowired
    private ChatPublisher publisher;

    @Autowired
    private Queue<DeferredResult<String>> deferredResults;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageDAO messageDAO;

    @RequestMapping(value = "/demoChat", method = RequestMethod.GET)
    public ModelAndView demo() {
        Message message = new Message();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        List<Message> messages = messageDAO.getAll();
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("demoChat");
        return modelAndView;
    }


    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public @ResponseBody
    DeferredResult<String> chatGet() {
        DeferredResult<String> result = new DeferredResult<>();
        deferredResults.add(result);
        return result;

    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    protected @ResponseBody
    Message doPost(@ModelAttribute Message message) {
        publisher.sendMessage(message);
        return message;
    }

    @Async
    @EventListener
    void handle(Message event) throws JsonProcessingException {
        System.out.println("Listener: " + event.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        Message message = new Message();
        message.setMessage(event.getMessage());
        String jsonInString = mapper.writeValueAsString(message);

        messageDAO.create(message);

        for (DeferredResult<String> result : deferredResults) {
            result.setResult(jsonInString);
            deferredResults.poll();
        }
    }
}