package rw.gov.rra.helpdeskapplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	@OneToMany(mappedBy = "requestor")
    private List<Request> requests = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole(){return role;}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User(String username, String password, Role role, List<Request> requests) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.requests = requests;
		this.department = department;
	}

	public User() {
		super();
	}


}