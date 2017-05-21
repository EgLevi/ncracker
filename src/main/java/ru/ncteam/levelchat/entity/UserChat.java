package ru.ncteam.levelchat.entity;


import javax.persistence.*;

@Entity
@Table(name = "USER_CHAT")
public class UserChat {

    @Id
    @Column(name = "USER_CHAT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long userChatId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;

    @Column(name = "UNREADABLE")
    private boolean unreadable;

    public boolean isUnreadable() {
        return unreadable;
    }

    public void setUnreadable(boolean unreadable) {
        this.unreadable = unreadable;
    }

    public long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(long userChatId) {
        this.userChatId = userChatId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
