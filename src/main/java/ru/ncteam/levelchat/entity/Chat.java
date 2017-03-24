package ru.ncteam.levelchat.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CHAT")
public class Chat {
    @Id
    @Column(name = "CHAT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long chatId;

    @Column(name = "NAME_CHAT", length = 100)
    private String nameChat;

    @Column(name = "STATUS_CHAT", length = 20)
    private char statusChat;

    @Column(name = "LIST_ID")
    private long listId;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID", nullable = false)
    private Levels level;

    @Column(name = "PERSONAL_CHAT")
    private boolean isPersonalChat;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    public Set<ChatGroup> chatGroups = new HashSet<ChatGroup>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    public Set<Messages> messages = new HashSet<Messages>();

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

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public boolean isPersonalChat() {
        return isPersonalChat;
    }

    public void setPersonalChat(boolean personalChat) {
        isPersonalChat = personalChat;
    }

    public Set<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(Set<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public Set<Messages> getMessages() {
        return messages;
    }

    public void setMessages(Set<Messages> messages) {
        this.messages = messages;
    }
}
