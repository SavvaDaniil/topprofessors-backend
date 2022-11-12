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
//@Table(name = "test_question_answer")
public class TestQuestionAnswer {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testId", referencedColumnName = "id")
	//private Test test;
	private int testId;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "testQuestionId", referencedColumnName = "id")
	//private TestQuestion testQuestion;
	private int testQuestionId;
	
	//@Column(name="number_of_answer", nullable=false, columnDefinition="int(4) default 0")
	private int numberOfAnswer;

	//@Column(name="type_of_answer", nullable=false, columnDefinition="int(1) default 0")
	private int typeOfAnswer;

	//@Column(name="textContent", nullable=true, columnDefinition="TEXT")
    private String textContent;

	//@Column(name="is_correct", nullable=false, columnDefinition="int(1) default 0")
	private int isCorrect;
	
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

	public int getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public int getNumberOfAnswer() {
		return numberOfAnswer;
	}

	public void setNumberOfAnswer(int numberOfAnswer) {
		this.numberOfAnswer = numberOfAnswer;
	}

	public int getTypeOfAnswer() {
		return typeOfAnswer;
	}

	public void setTypeOfAnswer(int typeOfAnswer) {
		this.typeOfAnswer = typeOfAnswer;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public int getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
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
