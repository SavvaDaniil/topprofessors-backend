package com.topprofessors.ViewModel.Test;

import java.util.ArrayList;

import com.topprofessors.Entity.TestQuestion;
import com.topprofessors.ViewModel.TestQuestion.TestQuestionLiteViewModel;
import com.topprofessors.ViewModel.TestUserAction.TestUserActionLiteViewModel;

public class TestStartViewModel {

	private int id;
	private ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<TestQuestionLiteViewModel> getTestQuestionLiteViewModels() {
		return testQuestionLiteViewModels;
	}
	public void setTestQuestionLiteViewModels(ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels) {
		this.testQuestionLiteViewModels = testQuestionLiteViewModels;
	}
	public TestStartViewModel(int id, ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels) {
		super();
		this.id = id;
		this.testQuestionLiteViewModels = testQuestionLiteViewModels;
	}


	
	
}
