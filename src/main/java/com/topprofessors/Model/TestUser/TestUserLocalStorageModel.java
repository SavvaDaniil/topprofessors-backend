package com.topprofessors.Model.TestUser;

import java.io.Serializable;
import java.util.ArrayList;

public class TestUserLocalStorageModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int testId;
	
	private ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels;

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

	public ArrayList<TestUserActionLocalStorageModel> getTestUserActionLocalStorageModels() {
		return testUserActionLocalStorageModels;
	}

	public void setTestUserActionLocalStorageModels(
			ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels) {
		this.testUserActionLocalStorageModels = testUserActionLocalStorageModels;
	}

	public TestUserLocalStorageModel(int id, int userId, int testId, ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels) {
		super();
		this.id = id;
		this.userId = userId;
		this.testId = testId;
		this.testUserActionLocalStorageModels = testUserActionLocalStorageModels;
	}
	
	
}
