package ru.ncteam.levelchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IDIMG")
public class IdImg {
	
	@Id
    @Column(name = "ID")
    private long id;
	
	@Column(name = "VALUE")
    private long value;

}
