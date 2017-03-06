package net.ncteam.levelchat.domain;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LCSEQ")
	@SequenceGenerator(name="LCSEQ", sequenceName="LCSEQ", allocationSize=1)
    private long messageId;

    @Column(name = "TWXT_MSG", length = 4000)
    private long textMessage;

    @ManyToOne
   	@JoinColumn(name = "CHAT_ID", nullable = false)
    private Chat chat;

    @ManyToOne
   	@JoinColumn(name = "USER_ID", nullable = false)
    private UserInfo userInfo;

    @ManyToOne
   	@JoinColumn(name = "DATA_ID", nullable = false)
    private UserData userData;

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

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
