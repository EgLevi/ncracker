package ru.ncteam.levelchat.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "PHOTO_LIB")
public class PhotoLib implements Comparable<PhotoLib>{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long photoId;

    @Column(name = "PHOTO_REF")
    private String photoRef;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserInfo userInfo;

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long id) {
        this.photoId = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    @Override
    public int compareTo(PhotoLib photoLib) {
        return (int)(this.photoId-photoLib.getPhotoId());
    }

    @Override
    public String toString() {
        return null;
    }
}
