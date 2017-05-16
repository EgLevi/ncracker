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

}
