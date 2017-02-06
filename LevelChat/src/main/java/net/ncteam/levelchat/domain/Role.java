package net.ncteam.levelchat.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "role")
	private String role;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "users_log_roles",
                joinColumns={@JoinColumn(name = "role_id")},
                inverseJoinColumns={@JoinColumn(name = "users_log_id")})
    private Set<UsersLog> users = new HashSet<UsersLog>();


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

	
	public Set<UsersLog> getUsers() {
        return users;
    }
 
    public void setUsers(Set<UsersLog> users) {
        this.users = users;
    }
}
