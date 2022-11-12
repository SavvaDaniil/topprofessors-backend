package com.topprofessors.ViewModel.TestUser;

import java.util.Date;

public class TestUserLiteViewModel {

	private int testId;
	private int userId;
	private int isFinished;
	private int statusOfDone;
	private float points;

	private Date dateOfAdd;
	private Date dateOfUpdate;
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
	public int getStatusOfDone() {
		return statusOfDone;
	}
	public void setStatusOfDone(int statusOfDone) {
		this.statusOfDone = statusOfDone;
	}
	
	
	
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	public Date getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public Date getDateOfUpdate() {
		return dateOfUpdate;
	}
	public void setDateOfUpdate(Date dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}
	public TestUserLiteViewModel(int testId, int userId, int isFinished, int statusOfDone, float points, Date dateOfAdd,
			Date dateOfUpdate) {
		super();
		this.testId = testId;
		this.userId = userId;
		this.isFinished = isFinished;
		this.statusOfDone = statusOfDone;
		this.points = points;
		this.dateOfAdd = dateOfAdd;
		this.dateOfUpdate = dateOfUpdate;
	}

	
	
}
