package com.topprofessors.ViewModel.Test;

public class TestLiteViewModel {

	private int id;
	private String name;
	private int status;
	private int questionsByOrder0Random1;
	private int maxSeenQuestions;
	private int typeOfTest;
	private float neededPoints;
    private String description;
    private String descriptionForClosedStatus;
	
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
	
	/*
	public TestLiteViewModel(int id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	*/
	
	public TestLiteViewModel(int id, String name, int status, int questionsByOrder0Random1, int maxSeenQuestions,
			int typeOfTest, float neededPoints, String description, String descriptionForClosedStatus) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.questionsByOrder0Random1 = questionsByOrder0Random1;
		this.maxSeenQuestions = maxSeenQuestions;
		this.typeOfTest = typeOfTest;
		this.neededPoints = neededPoints;
		this.description = description;
		this.descriptionForClosedStatus = descriptionForClosedStatus;
	}
	
	
}
