package rw.gov.rra.helpdeskapplication.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    
    @OneToMany(mappedBy = "department")
    private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Department(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.users = users;
	}

	public Department() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}
}
