package com.topprofessors.Entity;

import java.sql.Timestamp;
import java.util.Date;

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
//@Table(name = "message")
public class Message {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	////@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userIdFrom", referencedColumnName = "id")
	//private User userFrom;
	private int userIdFrom;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userIdTo", referencedColumnName = "id")
	//private User userTo;
	private int userIdTo;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "courseId", referencedColumnName = "id")
	//private Course course;
	private int courseId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "disciplineId", referencedColumnName = "id")
	//private Discipline discipline;
	private int disciplineId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "lessonId", referencedColumnName = "id")
	//private Lesson lesson;
	private int lessonId;

	////@Column(name="isParent", nullable=false, columnDefinition="int(1) default 0")
	private int isParent;
	
	////@OneToOne(cascade = CascadeType.ALL)
	////@JoinColumn(name = "parentMessageId", referencedColumnName = "id")
	//private Message parentMessage;
	private int parentMessageId;
	
	//private int messageId;
	
	////@Column(name="messageText", nullable=true, columnDefinition="text")
    private String messageText;

	////@Column(name="isDeleted", nullable=false, columnDefinition="int(1) default 0")
	private int isDeleted;

	////@Column(name="isClosedByUser", nullable=false, columnDefinition="int(1) default 0")
	private int isClosedByUser;

	////@Column(name="isClosedByAdminForce", nullable=false, columnDefinition="int(1) default 0")
	private int isClosedByAdminForce;

	////@Column(name="isCheckedByAdmin", nullable=false, columnDefinition="int(1) default 0")
	private int isCheckedByAdmin;

	////@Column(name="isCheckedByUser", nullable=false, columnDefinition="int(1) default 0")
	private int isCheckedByUser;
	
	/*
	////@Column(name="dateOfAdd", nullable=true, columnDefinition="TIMESTAMP")
	private java.sql.Timestamp dateOfAdd;

	////@Column(name="dateOfUpdate", nullable=true, columnDefinition="TIMESTAMP")
	private java.sql.Timestamp dateOfUpdate;
	*/
	////@Column(name="dateOfAdd", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfAdd;

	////@Column(name="dateOfUpdate", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	public int getUserIdFrom() {
		return userIdFrom;
	}

	public void setUserIdFrom(int userIdFrom) {
		this.userIdFrom = userIdFrom;
	}

	public int getUserIdTo() {
		return userIdTo;
	}

	public void setUserIdTo(int userIdTo) {
		this.userIdTo = userIdTo;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public int getParentMessageId() {
		return parentMessageId;
	}

	public void setParentMessageId(int parentMessageId) {
		this.parentMessageId = parentMessageId;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}


	public String getMessageText() {
		return messageText;
	}


	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getIsClosedByUser() {
		return isClosedByUser;
	}

	public void setIsClosedByUser(int isClosedByUser) {
		this.isClosedByUser = isClosedByUser;
	}

	public int getIsClosedByAdminForce() {
		return isClosedByAdminForce;
	}

	public void setIsClosedByAdminForce(int isClosedByAdminForce) {
		this.isClosedByAdminForce = isClosedByAdminForce;
	}

	public int getIsCheckedByAdmin() {
		return isCheckedByAdmin;
	}

	public void setIsCheckedByAdmin(int isCheckedByAdmin) {
		this.isCheckedByAdmin = isCheckedByAdmin;
	}

	public int getIsCheckedByUser() {
		return isCheckedByUser;
	}

	public void setIsCheckedByUser(int isCheckedByUser) {
		this.isCheckedByUser = isCheckedByUser;
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





	
	
	
	
	
	
	
	
}
