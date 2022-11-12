package com.topprofessors.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name ="connection_course_to_user")
public class ConnectionCourseToUser {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "courseId", referencedColumnName = "id")
	//private Course course;
	private int courseId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userId", referencedColumnName = "id")
	//private User user;
	private int userId;
	
	//@Column(name="active", nullable=false, columnDefinition="int(1) default 0")
	private int active;
	
	//@Column(name="is_done", nullable=false, columnDefinition="int(1) default 0")
	private int isDone;

	//@Column(name="dateOfAdd", nullable=true, columnDefinition="date")
	private Timestamp dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="date")
	private Timestamp dateOfUpdate;

	//@Column(name="dateOfDone", nullable=true, columnDefinition="date")
	private Timestamp dateOfDone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	*/

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
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

	public Timestamp getDateOfDone() {
		return dateOfDone;
	}

	public void setDateOfDone(Timestamp dateOfDone) {
		this.dateOfDone = dateOfDone;
	}


	
	
}
