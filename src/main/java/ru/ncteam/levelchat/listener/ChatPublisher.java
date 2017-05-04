package ru.ncteam.levelchat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.ncteam.levelchat.entity.Message;

@Component
public class ChatPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public ChatPublisher(ApplicationEventPublisher publisher) {
    }

    public void sendMessage(Message message) {
        publisher.publishEvent(message);
    }

}
