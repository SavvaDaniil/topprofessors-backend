package com.topprofessors.Entity;

import java.sql.Timestamp;

//@Entity
//@Table(name ="homework_user")
public class HomeworkUser {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

    private int homeworkId;
	
    private int userId;

    private String message;

	private int isViewed;
	private int isAccepted;
	private int isDenied;

	//@Column(name="dateOfAdd", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfUpdate;
	
	private String filetype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(int isViewed) {
		this.isViewed = isViewed;
	}

	public int getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(int isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getIsDenied() {
		return isDenied;
	}

	public void setIsDenied(int isDenied) {
		this.isDenied = isDenied;
	}

	public Timestamp getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(Timestamp dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}

	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
}
