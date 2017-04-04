package ru.ncteam.levelchat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {
    @RequestMapping(value = "/demoChat",method = RequestMethod.GET)
    public String doGet(){
        return "demoChat";
    }
}
