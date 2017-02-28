package ru.ncteam.levelchat.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "PHOTO_LIB")
public class PhotoLib {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "PHOTO")
    private Blob photo;

    @Column(name = "USER_ID")
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
