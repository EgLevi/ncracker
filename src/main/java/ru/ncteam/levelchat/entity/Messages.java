package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private long messageId;

    @Column(name = "TWXT_MSG", length = 4000)
    private long textMessage;

    @Column(name = "CHAT_ID")
    private long chatId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "DATA_ID")
    private long dataId;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(long textMessage) {
        this.textMessage = textMessage;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }
}
