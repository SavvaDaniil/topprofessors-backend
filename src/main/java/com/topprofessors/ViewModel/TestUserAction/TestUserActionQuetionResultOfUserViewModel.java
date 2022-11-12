package com.topprofessors.ViewModel.TestUserAction;

import java.util.ArrayList;
import java.util.HashSet;

import com.topprofessors.ViewModel.TestQuestion.TestQuestionLiteViewModel;
import com.topprofessors.ViewModel.TestQuestionAnswer.TestQuestionAnswerLiteViewModel;

public class TestUserActionQuetionResultOfUserViewModel extends TestQuestionLiteViewModel {

	private boolean isCorrect;
	private boolean isPartlyCorrect;
	private float pointsGot;
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public boolean getIsPartlyCorrect() {
		return isPartlyCorrect;
	}
	public void setIsPartlyCorrect(boolean isPartlyCorrect) {
		this.isPartlyCorrect = isPartlyCorrect;
	}
	public float getPointsGot() {
		return pointsGot;
	}
	public void setPointsGot(float pointsGot) {
		this.pointsGot = pointsGot;
	}
	
	

	public TestUserActionQuetionResultOfUserViewModel(int id, int userId, int testId, int numberOfQuestion,
			String textContent, ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels,
			HashSet<Integer> testQuestionAnswerIdsFilledByUserSet, boolean isCorrect, boolean isPartlyCorrect,
			float pointsGot) {
		super(id, userId, testId, numberOfQuestion, textContent, testQuestionAnswerLiteViewModels,
				testQuestionAnswerIdsFilledByUserSet);
		this.isCorrect = isCorrect;
		this.isPartlyCorrect = isPartlyCorrect;
		this.pointsGot = pointsGot;
	}
	
	
	private TestUserActionQuetionResultOfUserViewModel(
			int id, 
			int userId, 
			int testId, 
			int numberOfQuestion,
			String textContent, 
			ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels,
			HashSet<Integer> testQuestionAnswerIdsFilledByUserSet
		) {
		super(id, userId, testId, numberOfQuestion, textContent, testQuestionAnswerLiteViewModels,
				testQuestionAnswerIdsFilledByUserSet);
		// TODO Auto-generated constructor stub
	}
	
	/*
	private int testQuestionId;
	private int userId;
	private int testId;
	private int numberOfQuestion;
	private String textContent;
	
	
	private boolean isCorrect;
	*/
	
	
}
