package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "INTERESTS")
public class Interests {
    @Id
    @GeneratedValue
    @Column(name = "INTEREST_ID")
    private long interestId;

    @Column(name = "INTEREST_NAME", length = 130, unique = true)
    private String interestName;

    @Column(name = "CATEGORY_ID")
    private long categoryId;

    public long getInterestId() {
        return interestId;
    }

    public void setInterestId(long interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
