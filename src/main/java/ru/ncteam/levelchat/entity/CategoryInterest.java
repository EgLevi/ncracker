package ru.ncteam.levelchat.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_INTEREST")
public class CategoryInterest {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private long categoryId;

    @Column(name = "CAT_NAME", length = 60)
    private String categoryName;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
