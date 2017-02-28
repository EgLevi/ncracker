package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHAT")
public class Chat {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID")
    private long chatId;

    @Column(name = "NAME_CHAT", length = 100)
    private String nameChat;

    @Column(name = "STATUS_CHAT", length = 20)
    private char statusChat;

    @Column(name = "LIST_ID")
    private long listId;

    @Column(name = "LEVEL_ID")
    private long levelId;

    @Column(name = "PERSONAL_CHAT")
    private boolean isPersonalChat;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getNameChat() {
        return nameChat;
    }

    public void setNameChat(String nameChat) {
        this.nameChat = nameChat;
    }

    public char getStatusChat() {
        return statusChat;
    }

    public void setStatusChat(char statusChat) {
        this.statusChat = statusChat;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public boolean isPersonalChat() {
        return isPersonalChat;
    }

    public void setPersonalChat(boolean personalChat) {
        isPersonalChat = personalChat;
    }
}
