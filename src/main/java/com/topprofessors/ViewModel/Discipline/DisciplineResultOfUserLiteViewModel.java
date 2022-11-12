package com.topprofessors.ViewModel.Discipline;

import com.topprofessors.ViewModel.HomeworkUser.HomeworkUserResultLiteViewModel;
import com.topprofessors.ViewModel.Test.TestResultOfUserLiteViewModel;

public class DisciplineResultOfUserLiteViewModel {

	private int id;
	private String name;
	private boolean isWithoutTest;
	private TestResultOfUserLiteViewModel testResultOfUserLiteViewModel;

	private boolean isWithoutHomework;
	private HomeworkUserResultLiteViewModel homeworkUserResultLiteViewModel;
	
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
	
	public boolean getIsWithoutTest() {
		return isWithoutTest;
	}
	public void setIsWithoutTest(boolean isWithoutTest) {
		this.isWithoutTest = isWithoutTest;
	}
	public TestResultOfUserLiteViewModel getTestResultOfUserLiteViewModel() {
		return testResultOfUserLiteViewModel;
	}
	public void setTestResultOfUserLiteViewModel(TestResultOfUserLiteViewModel testResultOfUserLiteViewModel) {
		this.testResultOfUserLiteViewModel = testResultOfUserLiteViewModel;
	}
	public boolean getIsWithoutHomework() {
		return isWithoutHomework;
	}
	public void setIsWithoutHomework(boolean isWithoutHomework) {
		this.isWithoutHomework = isWithoutHomework;
	}
	public HomeworkUserResultLiteViewModel getHomeworkUserResultLiteViewModel() {
		return homeworkUserResultLiteViewModel;
	}
	public void setHomeworkUserResultLiteViewModel(HomeworkUserResultLiteViewModel homeworkUserResultLiteViewModel) {
		this.homeworkUserResultLiteViewModel = homeworkUserResultLiteViewModel;
	}
	public DisciplineResultOfUserLiteViewModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	public DisciplineResultOfUserLiteViewModel(int id, String name, boolean isWithoutTest) {
		super();
		this.id = id;
		this.name = name;
		this.isWithoutTest = isWithoutTest;
	}
	
	public DisciplineResultOfUserLiteViewModel(int id, String name,
			TestResultOfUserLiteViewModel testResultOfUserLiteViewModel) {
		super();
		this.id = id;
		this.name = name;
		this.testResultOfUserLiteViewModel = testResultOfUserLiteViewModel;
	}

	
	
}
