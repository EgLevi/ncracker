package ru.ncteam.levelchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long messageId;

    @Column(name = "TWXT_MSG", length = 4000)
    private String textMessage;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "DATA_ID")
    private UserData userData;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Chat getChat() {
        return chat;
    }

    public Message setChat(Chat chat) {
        this.chat = chat;
        return this;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public Message setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public UserData getUserData() {
        return userData;
    }

    public Message setUserData(UserData userData) {
        this.userData = userData;
        return this;
    }
}
