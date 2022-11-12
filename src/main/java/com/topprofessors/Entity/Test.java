package com.topprofessors.Entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "test")
public class Test {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;
	
	//@Column(name="name", nullable=true, columnDefinition="varchar(1024)")
    private String name;

	//@Column(name="status", nullable=false, columnDefinition="int(1) default 0")
	private int status;

	//@Column(name="questions_by_order_0_random_1", nullable=false, columnDefinition="int(1) default 0")
	private int questionsByOrder0Random1;

	//@Column(name="max_seen_questions", nullable=false, columnDefinition="int(3) default 0")
	private int maxSeenQuestions;

	//@Column(name="typeOfTest", nullable=false, columnDefinition="int(1) default 0")
	private int typeOfTest;
	
	//@Column(name="needed_points", nullable=false, columnDefinition="float(5) default 0")
	private float neededPoints;
	
	//@Column(name="description", nullable=true, columnDefinition="text")
    private String description;
	
	//@Column(name="descriptionForClosedStatus", nullable=true, columnDefinition="text")
    private String descriptionForClosedStatus;

	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuestionsByOrder0Random1() {
		return questionsByOrder0Random1;
	}

	public void setQuestionsByOrder0Random1(int questionsByOrder0Random1) {
		this.questionsByOrder0Random1 = questionsByOrder0Random1;
	}

	public int getMaxSeenQuestions() {
		return maxSeenQuestions;
	}

	public void setMaxSeenQuestions(int maxSeenQuestions) {
		this.maxSeenQuestions = maxSeenQuestions;
	}

	public int getTypeOfTest() {
		return typeOfTest;
	}

	public void setTypeOfTest(int typeOfTest) {
		this.typeOfTest = typeOfTest;
	}

	public float getNeededPoints() {
		return neededPoints;
	}

	public void setNeededPoints(float neededPoints) {
		this.neededPoints = neededPoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionForClosedStatus() {
		return descriptionForClosedStatus;
	}

	public void setDescriptionForClosedStatus(String descriptionForClosedStatus) {
		this.descriptionForClosedStatus = descriptionForClosedStatus;
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
