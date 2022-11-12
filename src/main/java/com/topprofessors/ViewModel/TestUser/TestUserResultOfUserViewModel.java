package com.topprofessors.ViewModel.TestUser;

import java.util.ArrayList;

import com.topprofessors.ViewModel.TestUserAction.TestUserActionQuetionResultOfUserViewModel;

public class TestUserResultOfUserViewModel {
	private TestUserLiteViewModel testUserLiteViewModel;
	private ArrayList<TestUserActionQuetionResultOfUserViewModel> testUserActionQuetionResultOfUserViewModels;
	
	public TestUserLiteViewModel getTestUserLiteViewModel() {
		return testUserLiteViewModel;
	}
	public void setTestUserLiteViewModel(TestUserLiteViewModel testUserLiteViewModel) {
		this.testUserLiteViewModel = testUserLiteViewModel;
	}
	public ArrayList<TestUserActionQuetionResultOfUserViewModel> getTestUserActionQuetionResultOfUserViewModels() {
		return testUserActionQuetionResultOfUserViewModels;
	}
	public void setTestUserActionQuetionResultOfUserViewModels(
			ArrayList<TestUserActionQuetionResultOfUserViewModel> testUserActionQuetionResultOfUserViewModels) {
		this.testUserActionQuetionResultOfUserViewModels = testUserActionQuetionResultOfUserViewModels;
	}
	
	public TestUserResultOfUserViewModel(TestUserLiteViewModel testUserLiteViewModel,
			ArrayList<TestUserActionQuetionResultOfUserViewModel> testUserActionQuetionResultOfUserViewModels) {
		super();
		this.testUserLiteViewModel = testUserLiteViewModel;
		this.testUserActionQuetionResultOfUserViewModels = testUserActionQuetionResultOfUserViewModels;
	}
	
	
}
