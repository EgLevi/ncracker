package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "INTEREST_LIST")
public class InterestList {
    @Id
    @GeneratedValue
    @Column(name = "LIST_ID")
    private long listId;

    @Column(name = "INTEREST_GROUP", unique = true)
    private long interestGroup;

    @Column(name = "INTEREST_ID")
    private long interestId;

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public long getInterestGroup() {
        return interestGroup;
    }

    public void setInterestGroup(long interestGroup) {
        this.interestGroup = interestGroup;
    }

    public long getInterestId() {
        return interestId;
    }

    public void setInterestId(long interestId) {
        this.interestId = interestId;
    }
}
