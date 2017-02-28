package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "INTEREST_LIST")
public class InterestList {
    @Id
    @Column(name = "LIST_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long listId;

    @Column(name = "INTEREST_GROUP", unique = true)
    private long interestGroup;

    @ManyToOne
    @JoinColumn(name = "INTEREST_ID", nullable = false)
    private Interests interest;

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

    public Interests getInterests() {
        return interest;
    }

    public void setInterest(Interests interest) {
        this.interest = interest;
    }
}
