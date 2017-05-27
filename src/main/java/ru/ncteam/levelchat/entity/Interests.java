package ru.ncteam.levelchat.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INTERESTS")
public class Interests {
    @Id
    @Column(name = "INTEREST_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long interestId;

    @Column(name = "INTEREST_NAME", length = 130, unique = true)
    private String interestName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @JsonIgnore
    private CategoryInterest categoryInterest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "interest")
    @JsonIgnore
    private Set<InterestList> interestLists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "interests")
    @JsonIgnore
    public Set<DashboardUrl> dashboardUrls = new HashSet<>();

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

    public CategoryInterest getCategoryInterest() {
        return categoryInterest;
    }

    public void setCategoryInterest(CategoryInterest categoryInterest) {
        this.categoryInterest = categoryInterest;
    }

    public Set<InterestList> getInterestLists() {
        return interestLists;
    }

    public void setInterestLists(Set<InterestList> interestLists) {
        this.interestLists = interestLists;
    }
}
