package com.topprofessors.ViewModel.HomeworkUser;

public class HomeworkUserResultLiteViewModel {

	private int id;
	private boolean isHomeworkAccepted;
	private boolean isHomeworkDenied;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getIsHomeworkAccepted() {
		return isHomeworkAccepted;
	}
	public void setIsHomeworkAccepted(boolean isHomeworkAccepted) {
		this.isHomeworkAccepted = isHomeworkAccepted;
	}
	public boolean isHomeworkDenied() {
		return isHomeworkDenied;
	}
	public void setHomeworkDenied(boolean isHomeworkDenied) {
		this.isHomeworkDenied = isHomeworkDenied;
	}
	
	public HomeworkUserResultLiteViewModel(int id, boolean isHomeworkAccepted, boolean isHomeworkDenied) {
		super();
		this.id = id;
		this.isHomeworkAccepted = isHomeworkAccepted;
		this.isHomeworkDenied = isHomeworkDenied;
	}
	
	
	
}
