package com.topprofessors.ViewModel.HomeworkUser;

public class HomeworkUserLiteViewModel {

	private int id;
	private int homeworkId;
	private int userId;
    private String message;
	private int isViewed;
	private int isAccepted;
	private int isDenied;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(int isViewed) {
		this.isViewed = isViewed;
	}
	public int getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(int isAccepted) {
		this.isAccepted = isAccepted;
	}
	public int getIsDenied() {
		return isDenied;
	}
	public void setIsDenied(int isDenied) {
		this.isDenied = isDenied;
	}
	
	public HomeworkUserLiteViewModel(int id, int homeworkId, int userId, String message, int isViewed, int isAccepted,
			int isDenied) {
		super();
		this.id = id;
		this.homeworkId = homeworkId;
		this.userId = userId;
		this.message = message;
		this.isViewed = isViewed;
		this.isAccepted = isAccepted;
		this.isDenied = isDenied;
	}
	
	
}
