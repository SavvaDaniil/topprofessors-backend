package com.topprofessors.ViewModel.Test;

import com.topprofessors.ViewModel.TestUser.TestUserLiteViewModel;

public class TestIndexViewModel {

	private int id;
	private String name;
	private int status;
	private int maxSeenQuestions;
	private float neededPoints;
    private String description;
    private String descriptionForClosedStatus;
    
    private TestUserLiteViewModel testUserLiteViewModel;
    private boolean isAvailableForContinue;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMaxSeenQuestions() {
		return maxSeenQuestions;
	}

	public void setMaxSeenQuestions(int maxSeenQuestions) {
		this.maxSeenQuestions = maxSeenQuestions;
	}

	public float getNeededPoints() {
		return neededPoints;
	}

	public void setNeededPoints(float neededPoints) {
		this.neededPoints = neededPoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionForClosedStatus() {
		return descriptionForClosedStatus;
	}

	public void setDescriptionForClosedStatus(String descriptionForClosedStatus) {
		this.descriptionForClosedStatus = descriptionForClosedStatus;
	}

	public TestUserLiteViewModel getTestUserLiteViewModel() {
		return testUserLiteViewModel;
	}

	public void setTestUserLiteViewModel(TestUserLiteViewModel testUserLiteViewModel) {
		this.testUserLiteViewModel = testUserLiteViewModel;
	}
	public boolean getIsAvailableForContinue() {
		return isAvailableForContinue;
	}

	public void setIsAvailableForContinue(boolean isAvailableForContinue) {
		this.isAvailableForContinue = isAvailableForContinue;
	}

	public TestIndexViewModel(int id, String name, int status, int maxSeenQuestions, float neededPoints,
			String description, String descriptionForClosedStatus, TestUserLiteViewModel testUserLiteViewModel,
			boolean isAvailableForContinue) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.maxSeenQuestions = maxSeenQuestions;
		this.neededPoints = neededPoints;
		this.description = description;
		this.descriptionForClosedStatus = descriptionForClosedStatus;
		this.testUserLiteViewModel = testUserLiteViewModel;
		this.isAvailableForContinue = isAvailableForContinue;
	}


    
    
}
