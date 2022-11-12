package com.topprofessors.ViewModel.Discipline;

public class DisciplineStatForUserViewModel {

	private int id;
	private String name;
	private int testId;
	private float procentsOfDone;
	private boolean isDone;
	
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
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public float getProcentsOfDone() {
		return procentsOfDone;
	}
	public void setProcentsOfDone(float procentsOfDone) {
		this.procentsOfDone = procentsOfDone;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public DisciplineStatForUserViewModel(int id, String name, int testId, float procentsOfDone, boolean isDone) {
		super();
		this.id = id;
		this.name = name;
		this.testId = testId;
		this.procentsOfDone = procentsOfDone;
		this.isDone = isDone;
	}
	
}
