package ru.ncteam.levelchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LEVELS")
public class Levels {
    @Id
    @Column(name = "LEVEL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long levelId;

    @Column(name = "NAME_LEVEL", length = 100)
    private String nameLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "level")
    @JsonIgnore
    public Set<Chat> chats = new HashSet<Chat>();

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

    public Set<Chat> getChats() {
        return chats;
    }

    public void setIChats(Set<Chat> chats) {
        this.chats = chats;
    }
}
