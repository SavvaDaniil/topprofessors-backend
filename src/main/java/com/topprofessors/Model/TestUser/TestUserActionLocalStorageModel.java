package com.topprofessors.Model.TestUser;

import java.io.Serializable;
import java.util.HashSet;

public class TestUserActionLocalStorageModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int testQuestionId;
	private int numberOfQuestion;
	private HashSet<Integer> testQuestionAnswerIdsSet;
	
	public int getTestQuestionId() {
		return testQuestionId;
	}
	public void setTestQuestionId(int testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}
	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
	public HashSet<Integer> getTestQuestionAnswerIdsSet() {
		return testQuestionAnswerIdsSet;
	}
	public void setTestQuestionAnswerIdsSet(HashSet<Integer> testQuestionAnswerIdsSet) {
		this.testQuestionAnswerIdsSet = testQuestionAnswerIdsSet;
	}
	
	
	public TestUserActionLocalStorageModel(int testQuestionId, int numberOfQuestion) {
		super();
		this.testQuestionId = testQuestionId;
		this.numberOfQuestion = numberOfQuestion;
	}
	public TestUserActionLocalStorageModel(int testQuestionId, int numberOfQuestion,
			HashSet<Integer> testQuestionAnswerIdsSet) {
		super();
		this.testQuestionId = testQuestionId;
		this.numberOfQuestion = numberOfQuestion;
		this.testQuestionAnswerIdsSet = testQuestionAnswerIdsSet;
	}
	
	


	
	
}
