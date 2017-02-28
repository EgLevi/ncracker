package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHAT_GROUP")
public class ChatGroup {
    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private long groupId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "CHAT_ID")
    private long chatId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
