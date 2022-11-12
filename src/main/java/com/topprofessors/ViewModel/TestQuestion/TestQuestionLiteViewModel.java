package com.topprofessors.ViewModel.TestQuestion;

import java.util.ArrayList;
import java.util.HashSet;

import com.topprofessors.ViewModel.TestQuestionAnswer.TestQuestionAnswerLiteViewModel;
import com.topprofessors.ViewModel.TestUserAction.TestUserActionLiteViewModel;

public class TestQuestionLiteViewModel {

	private int id;
	private int userId;
	private int testId;
	private int numberOfQuestion;
	private String textContent;
	
	private ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels;
	//private ArrayList<TestUserActionLiteViewModel> testUserActionLiteViewModels;
	private HashSet<Integer> testQuestionAnswerIdsFilledByUserSet;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}
	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	public ArrayList<TestQuestionAnswerLiteViewModel> getTestQuestionAnswerLiteViewModels() {
		return testQuestionAnswerLiteViewModels;
	}
	public void setTestQuestionAnswerLiteViewModels(
			ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels) {
		this.testQuestionAnswerLiteViewModels = testQuestionAnswerLiteViewModels;
	}

	public HashSet<Integer> getTestQuestionAnswerIdsFilledByUserSet() {
		return testQuestionAnswerIdsFilledByUserSet;
	}
	public void setTestQuestionAnswerIdsFilledByUserSet(HashSet<Integer> testQuestionAnswerIdsFilledByUserSet) {
		this.testQuestionAnswerIdsFilledByUserSet = testQuestionAnswerIdsFilledByUserSet;
	}
	
	public TestQuestionLiteViewModel(int id, int userId, int testId, int numberOfQuestion, String textContent,
			ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels,
			HashSet<Integer> testQuestionAnswerIdsFilledByUserSet) {
		super();
		this.id = id;
		this.userId = userId;
		this.testId = testId;
		this.numberOfQuestion = numberOfQuestion;
		this.textContent = textContent;
		this.testQuestionAnswerLiteViewModels = testQuestionAnswerLiteViewModels;
		this.testQuestionAnswerIdsFilledByUserSet = testQuestionAnswerIdsFilledByUserSet;
	}

	
	
}
