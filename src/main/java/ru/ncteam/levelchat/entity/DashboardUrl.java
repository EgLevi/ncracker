package ru.ncteam.levelchat.entity;

import javax.persistence.*;
@Entity
@Table(name = "DASHBOARD_URL")
class DashboardUrl {
    @Id
    @Column(name = "BOARD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LCSEQ")
    @SequenceGenerator(name = "LCSEQ", sequenceName = "LCSEQ", allocationSize = 1)
    private long boardId;

    @Column(name = "URL_INTEREST", length = 1000)
    private String urlInterest;

    @Column(name = "URL_IMG", length = 1000)
    private String statusChat;

    @ManyToOne
    @JoinColumn(name = "INTEREST_ID", nullable = false)
    private Interests interests;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public String getUrlInterest() {
        return urlInterest;
    }

    public void setUrlInterest(String urlInterest) {
        this.urlInterest = urlInterest;
    }

    public String getStatusChat() {
        return statusChat;
    }

    public void setStatusChat(String statusChat) {
        this.statusChat = statusChat;
    }

    public Interests getInterests() {
        return interests;
    }

    public void setInterests(Interests interests) {
        this.interests = interests;
    }
}
