package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHAT_GROUP")
public class ChatGroup {
    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long groupId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID", nullable = false)
    private Chat chat;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUserInfo(UserInfo user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
