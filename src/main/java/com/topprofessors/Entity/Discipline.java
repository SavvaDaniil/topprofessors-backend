package com.topprofessors.Entity;

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
//@Table(name ="discipline")
public class Discipline {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;
	
	//@Column(name="name", nullable=true, columnDefinition="varchar(512)")
    private String name;

	//@Column(name="description", nullable=true, columnDefinition="text")
    private String description;
	
	//@Column(name="number_of_lessons", nullable=false, columnDefinition="int(2) default 0")
	private int numberOfLessons;
	
	//@Column(name="force_block_access", nullable=false, columnDefinition="int(1) default 0")
	private int forceBlockAccess;
	
	//@Column(name="is_without_test", nullable=false, columnDefinition="int(1) default 0")
	private int isWithoutTest;

	//@OneToOne(cascade = CascadeType.REMOVE)
	//@JoinColumn(name = "testId", referencedColumnName = "id")
	//private Test test;
	private int testId;
	
	private int isWithoutHomework;
	private int homeworkId;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfLessons() {
		return numberOfLessons;
	}

	public void setNumberOfLessons(int numberOfLessons) {
		this.numberOfLessons = numberOfLessons;
	}

	public int getForceBlockAccess() {
		return forceBlockAccess;
	}

	public void setForceBlockAccess(int forceBlockAccess) {
		this.forceBlockAccess = forceBlockAccess;
	}

	public int getIsWithoutTest() {
		return isWithoutTest;
	}

	public void setIsWithoutTest(int isWithoutTest) {
		this.isWithoutTest = isWithoutTest;
	}

	/*
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	*/

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getIsWithoutHomework() {
		return isWithoutHomework;
	}

	public void setIsWithoutHomework(int isWithoutHomework) {
		this.isWithoutHomework = isWithoutHomework;
	}

	public int getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	
	
}
