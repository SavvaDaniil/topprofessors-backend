package com.topprofessors.ViewModel.Discipline;

import java.util.ArrayList;

import com.topprofessors.ViewModel.Homework.HomeworkLiteForUserViewModel;
import com.topprofessors.ViewModel.Lesson.LessonLiteViewModel;
import com.topprofessors.ViewModel.Lesson.LessonViewModel;
import com.topprofessors.ViewModel.Test.TestLiteViewModel;
import com.topprofessors.ViewModel.TestUser.TestUserLiteViewModel;

public class DisciplineInfoViewModel {

	private int id;
	private String name;
	private String description;
	private int isWithoutTest;
	private ArrayList<LessonLiteViewModel> lessonLiteViewModels;
	private LessonViewModel lessonViewModel;
	private int countOfUnreadedMessagesFromUser;
	
	private TestLiteViewModel testLiteViewModel;
	private TestUserLiteViewModel testUserLiteViewModel;

	private int isWithoutHomework;
	private HomeworkLiteForUserViewModel homeworkLiteForUserViewModel;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsWithoutTest() {
		return isWithoutTest;
	}
	public void setIsWithoutTest(int isWithoutTest) {
		this.isWithoutTest = isWithoutTest;
	}
	public ArrayList<LessonLiteViewModel> getLessonLiteViewModels() {
		return lessonLiteViewModels;
	}
	public void setLessonLiteViewModels(ArrayList<LessonLiteViewModel> lessonLiteViewModels) {
		this.lessonLiteViewModels = lessonLiteViewModels;
	}
	public LessonViewModel getLessonViewModel() {
		return lessonViewModel;
	}
	public void setLessonViewModel(LessonViewModel lessonViewModel) {
		this.lessonViewModel = lessonViewModel;
	}
	public int getCountOfUnreadedMessagesFromUser() {
		return countOfUnreadedMessagesFromUser;
	}
	public void setCountOfUnreadedMessagesFromUser(int countOfUnreadedMessagesFromUser) {
		this.countOfUnreadedMessagesFromUser = countOfUnreadedMessagesFromUser;
	}
	public TestLiteViewModel getTestLiteViewModel() {
		return testLiteViewModel;
	}
	public void setTestLiteViewModel(TestLiteViewModel testLiteViewModel) {
		this.testLiteViewModel = testLiteViewModel;
	}
	public TestUserLiteViewModel getTestUserLiteViewModel() {
		return testUserLiteViewModel;
	}
	public void setTestUserLiteViewModel(TestUserLiteViewModel testUserLiteViewModel) {
		this.testUserLiteViewModel = testUserLiteViewModel;
	}
	public HomeworkLiteForUserViewModel getHomeworkLiteForUserViewModel() {
		return homeworkLiteForUserViewModel;
	}
	public void setHomeworkLiteForUserViewModel(HomeworkLiteForUserViewModel homeworkLiteForUserViewModel) {
		this.homeworkLiteForUserViewModel = homeworkLiteForUserViewModel;
	}
	
	public int getIsWithoutHomework() {
		return isWithoutHomework;
	}
	public void setIsWithoutHomework(int isWithoutHomework) {
		this.isWithoutHomework = isWithoutHomework;
	}
	public DisciplineInfoViewModel(int id, String name, String description, int isWithoutTest,
			ArrayList<LessonLiteViewModel> lessonLiteViewModels, LessonViewModel lessonViewModel,
			int countOfUnreadedMessagesFromUser, int isWithoutHomework) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isWithoutTest = isWithoutTest;
		this.lessonLiteViewModels = lessonLiteViewModels;
		this.lessonViewModel = lessonViewModel;
		this.countOfUnreadedMessagesFromUser = countOfUnreadedMessagesFromUser;
		this.isWithoutHomework = isWithoutHomework;
	}
	
}
