package com.topprofessors.ViewModel.Test;

public class TestResultOfUserLiteViewModel {

	private int id;
	private String name;
	
	private int testUserId;
	private boolean isStarted;
	private boolean isFinished;
	private boolean isDone;
	
	private float neededPoints;
	private float points;
	
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


	
	public int getTestUserId() {
		return testUserId;
	}
	public void setTestUserId(int testUserId) {
		this.testUserId = testUserId;
	}
	public boolean getIsStarted() {
		return isStarted;
	}
	public void setIsStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public boolean getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	public boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public float getNeededPoints() {
		return neededPoints;
	}
	public void setNeededPoints(float neededPoints) {
		this.neededPoints = neededPoints;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
	public TestResultOfUserLiteViewModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.isStarted = false;
	}
	
	public TestResultOfUserLiteViewModel(int id, String name, int testUserId, boolean isStarted, boolean isFinished,
			boolean isDone, float neededPoints, float points) {
		super();
		this.id = id;
		this.name = name;
		this.testUserId = testUserId;
		this.isStarted = isStarted;
		this.isFinished = isFinished;
		this.isDone = isDone;
		this.neededPoints = neededPoints;
		this.points = points;
	}


	
	
}
