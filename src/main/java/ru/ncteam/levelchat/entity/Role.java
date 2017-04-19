package ru.ncteam.levelchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @Column(name = "ROLE_ID")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ROLE")
    private String role;

	/*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_ROLES",
                joinColumns={@JoinColumn(name = "ROLE_ID")},
                inverseJoinColumns={@JoinColumn(name = "USER_ID")})
    private Set<UserInfo> users = new HashSet<UserInfo>();*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	
	/*public Set<UserInfo> getUsers() {
        return users;
    }
 
    public void setUsers(Set<UserInfo> users) {
        this.users = users;
    }*/
}
