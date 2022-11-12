package com.topprofessors.ViewModel.TestUserAction;

import java.util.HashSet;

public class TestUserActionLocalStorageViewModel {

	private int testQuestionId;
	private int numberOfQuesion;
	private HashSet<Integer> testQuestionAnswerIdsSet;
	
	public int getTestQuestionId() {
		return testQuestionId;
	}
	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}
	public int getNumberOfQuesion() {
		return numberOfQuesion;
	}
	public void setNumberOfQuesion(int numberOfQuesion) {
		this.numberOfQuesion = numberOfQuesion;
	}
	public HashSet<Integer> getTestQuestionAnswerIdsSet() {
		return testQuestionAnswerIdsSet;
	}
	public void setTestQuestionAnswerIdsSet(HashSet<Integer> testQuestionAnswerIdsSet) {
		this.testQuestionAnswerIdsSet = testQuestionAnswerIdsSet;
	}
	
	public TestUserActionLocalStorageViewModel(int testQuestionId, int numberOfQuesion) {
		super();
		this.testQuestionId = testQuestionId;
		this.numberOfQuesion = numberOfQuesion;
	}
	
	public TestUserActionLocalStorageViewModel(int testQuestionId, int numberOfQuesion,
			HashSet<Integer> testQuestionAnswerIdsSet) {
		super();
		this.testQuestionId = testQuestionId;
		this.numberOfQuesion = numberOfQuesion;
		this.testQuestionAnswerIdsSet = testQuestionAnswerIdsSet;
	}
	
}
