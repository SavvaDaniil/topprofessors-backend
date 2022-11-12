package com.topprofessors.DTO.TestQuestionAnswer;

import java.util.HashSet;

public class TestQuestionAnswerFromUserDTO {

	private int testId;
	private int testQuestionId;
	private HashSet<Integer> testQuestionAnswersSet;
	
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
	public HashSet<Integer> getTestQuestionAnswersSet() {
		return testQuestionAnswersSet;
	}
	public void setTestQuestionAnswersSet(HashSet<Integer> testQuestionAnswersSet) {
		this.testQuestionAnswersSet = testQuestionAnswersSet;
	}
	
	
	
}
