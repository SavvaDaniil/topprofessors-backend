package com.topprofessors.ViewModel.Homework;

import com.topprofessors.ViewModel.HomeworkUser.HomeworkUserLiteViewModel;

public class HomeworkLiteForUserViewModel {

	private int id;
    private String name;
    private String description;
    
    private HomeworkUserLiteViewModel homeworkUserLiteViewModel;
    
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
	
	public HomeworkUserLiteViewModel getHomeworkUserLiteViewModel() {
		return homeworkUserLiteViewModel;
	}
	public void setHomeworkUserLiteViewModel(HomeworkUserLiteViewModel homeworkUserLiteViewModel) {
		this.homeworkUserLiteViewModel = homeworkUserLiteViewModel;
	}
	
	public HomeworkLiteForUserViewModel(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	
    
}
