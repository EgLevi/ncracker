package ru.ncteam.levelchat.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_DATA")
public class UserData {
    @Id
    @Column(name = "DATA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long dataId;

    @Column(name = "DATA")
    private Blob data;

    @ManyToOne
    @JoinColumn(name = "TYPE", nullable = false)
    private TypeData type;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserInfo userInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userData")
    public Set<Message> messages = new HashSet<Message>();

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public TypeData getType() {
        return type;
    }

    public void setType(TypeData type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
