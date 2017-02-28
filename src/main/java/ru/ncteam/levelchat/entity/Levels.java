package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "LEVELS")
public class Levels {
    @Id
    @GeneratedValue
    @Column(name = "LEVEL_ID")
    private long levelId;

    @Column(name = "NAME_LEVEL", length = 100)
    private String nameLevel;

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public String getNameLevel() {
        return nameLevel;
    }

    public void setNameLevel(String nameLevel) {
        this.nameLevel = nameLevel;
    }
}
