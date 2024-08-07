package rw.gov.rra.helpdeskapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
	@JsonManagedReference
	private List<Request> requests = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public User() {
		super();
	}

	public User(String username, String password, Role role, List<Request> requests) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.requests = requests;
	}

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

	public Role getRole() {
		return role;
	}

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
}