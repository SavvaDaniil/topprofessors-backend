package com.topprofessors.Entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "test_user")
public class TestUser {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	////@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "testId", referencedColumnName = "id")
	//private Test test;
	private int testId;

	//@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	////@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "userId", referencedColumnName = "id")
	//private User user;
	private int userId;

	//@Column(name="is_finished", nullable=false, columnDefinition="int(1) default 0")
	private int isFinished;

	//@Column(name="is_done", nullable=false, columnDefinition="int(1) default 0")
	private int isDone;
	
	//@Column(name="seen_questions", nullable=false, columnDefinition="int(4) default 0")
	private int seenQuestions;
	
	//@Column(name="points", nullable=false, columnDefinition="float(5) default 0")
	private float points;
	
	//@Column(name="dateOfAdd", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}

	public int getSeenQuestions() {
		return seenQuestions;
	}

	public void setSeenQuestions(int seenQuestions) {
		this.seenQuestions = seenQuestions;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
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
