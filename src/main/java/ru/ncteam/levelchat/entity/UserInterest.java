package ru.ncteam.levelchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INTEREST")
public class UserInterest {
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "INTEREST_ID")
    private long interestId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInterestId() {
        return interestId;
    }

    public void setInterestId(long interestId) {
        this.interestId = interestId;
    }
}
