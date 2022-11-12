package com.topprofessors.ViewModel.TestUserAction;

public class TestUserActionLiteViewModel {

	private int id;

	private int numberOfQuesion;
	private int testQuestionId;
	private int testQuestionAnswerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfQuesion() {
		return numberOfQuesion;
	}
	public void setNumberOfQuesion(int numberOfQuesion) {
		this.numberOfQuesion = numberOfQuesion;
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
	
	public TestUserActionLiteViewModel(int id, int testQuestionAnswerId) {
		super();
		this.id = id;
		this.testQuestionAnswerId = testQuestionAnswerId;
	}
	
	public TestUserActionLiteViewModel(int id, int numberOfQuesion, int testQuestionId, int testQuestionAnswerId) {
		super();
		this.id = id;
		this.numberOfQuesion = numberOfQuesion;
		this.testQuestionId = testQuestionId;
		this.testQuestionAnswerId = testQuestionAnswerId;
	}
	
	
}
