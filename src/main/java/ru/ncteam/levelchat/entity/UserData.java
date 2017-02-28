package ru.ncteam.levelchat.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "USER_DATA")
public class UserData {
    @Id
    @GeneratedValue
    @Column(name = "DATA_ID")
    private long dataId;

    @Column(name = "DATA")
    private Blob data;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "USER_ID")
    private long userId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
