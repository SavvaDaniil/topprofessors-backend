package com.topprofessors.Entity;

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
//@Table(name = "test_user_action")
public class TestUserAction {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testUserId", referencedColumnName = "id")
	//private TestUser testUser;
	private int testUserId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testId", referencedColumnName = "id")
	//private Test test;
	private int testId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userId", referencedColumnName = "id")
	//private User user;
	private int userId;
	
	//@Column(name="number_of_quesion", nullable=false, columnDefinition="int(4) default 0")
	private int numberOfQuesion;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testQuestionId", referencedColumnName = "id")
	//private TestQuestion testQuestion;
	private int testQuestionId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testQuestionAnswerId", referencedColumnName = "id")
	//private TestQuestionAnswer testQuestionAnswer;
	private int testQuestionAnswerId;
	
	//@Column(name="dateOfAdd", nullable=true, columnDefinition="DATETIME")
	private java.sql.Date dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="DATETIME")
	private java.sql.Date dateOfUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getTestUserId() {
		return testUserId;
	}

	public void setTestUserId(int testUserId) {
		this.testUserId = testUserId;
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

	public int getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public int getTestQuestionAnswerId() {
		return testQuestionAnswerId;
	}

	public void setTestQuestionAnswerId(int testQuestionAnswerId) {
		this.testQuestionAnswerId = testQuestionAnswerId;
	}

	public int getNumberOfQuesion() {
		return numberOfQuesion;
	}

	public void setNumberOfQuesion(int numberOfQuesion) {
		this.numberOfQuesion = numberOfQuesion;
	}

	public java.sql.Date getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(java.sql.Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}

	public java.sql.Date getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(java.sql.Date dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}


	
	
}
