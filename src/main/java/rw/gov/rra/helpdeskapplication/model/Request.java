package rw.gov.rra.helpdeskapplication.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private String department;
	private String description;
	private Date requestDate;
	private String status;
	private String comment;
	private LocalDateTime approvedDate;

	@ManyToOne
	@JoinColumn(name = "requestor_id")
	private User requestor;

	@ManyToOne
	@JoinColumn(name = "assigned_to_id")
	private User assignedTo;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department depart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getRequestor() {
		return requestor;
	}

	public void setRequestor(User requestor) {
		this.requestor = requestor;
	}

//	public void setRequestor(int requestor) {
//		this.requestor = requestor;
//	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}
	//this change from here
	public void approve() {
		this.status = "Approved";
		this.approvedDate = LocalDateTime.now();
	}

	public void setPending() {
		this.status = "Pending";
	}
	public void addComment(String comment) {
		this.comment = comment;
	}
	//until here
//String department,
	public Request( String description, Date requestDate, String status, User requestor,
					User assignedTo, Department depart, String comment, LocalDateTime approvedDate) {
		super();
		//this.department = department;
		this.description = description;
		this.requestDate = requestDate;
		this.status = status;
		this.requestor = requestor;
		this.assignedTo = assignedTo;
		this.depart = depart;
		this.comment = comment;
		this.approvedDate = approvedDate;

	}

	public Request() {
		super();
	}



}
