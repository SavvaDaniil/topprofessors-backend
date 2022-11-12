package com.topprofessors.ViewModel.Message;

import java.util.Date;

public class MessageParentViewModel {

	private int id;
	private int userIdFrom;
	private int userIdTo;
	
	private int courseId;
	private int disciplineId;
	private int lessonId;

    private String messageText;

    private boolean isOutOfLimitUnreadMessagesFromUser;
	private boolean isClosedByUser;
	private boolean isClosedByAdminForce;
	private boolean isCheckedByUser;
	private boolean isCheckedByAdmin;
	
	private Date dateOfAdd;
	private Date dateOfUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserIdFrom() {
		return userIdFrom;
	}
	public void setUserIdFrom(int userIdFrom) {
		this.userIdFrom = userIdFrom;
	}
	public int getUserIdTo() {
		return userIdTo;
	}
	public void setUserIdTo(int userIdTo) {
		this.userIdTo = userIdTo;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public boolean getIsOutOfLimitUnreadMessagesFromUser() {
		return isOutOfLimitUnreadMessagesFromUser;
	}
	public void setIsOutOfLimitUnreadMessagesFromUser(boolean isOutOfLimitUnreadMessagesFromUser) {
		this.isOutOfLimitUnreadMessagesFromUser = isOutOfLimitUnreadMessagesFromUser;
	}
	public boolean getIsClosedByUser() {
		return isClosedByUser;
	}
	public void setIsClosedByUser(boolean isClosedByUser) {
		this.isClosedByUser = isClosedByUser;
	}
	public boolean getIsClosedByAdminForce() {
		return isClosedByAdminForce;
	}
	public void setIsClosedByAdminForce(boolean isClosedByAdminForce) {
		this.isClosedByAdminForce = isClosedByAdminForce;
	}
	public boolean getIsCheckedByAdmin() {
		return isCheckedByAdmin;
	}
	public void setIsCheckedByAdmin(boolean isCheckedByAdmin) {
		this.isCheckedByAdmin = isCheckedByAdmin;
	}
	public boolean getIsCheckedByUser() {
		return isCheckedByUser;
	}
	public void setIsCheckedByUser(boolean isCheckedByUser) {
		this.isCheckedByUser = isCheckedByUser;
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
	public MessageParentViewModel(int id, int userIdFrom, int userIdTo, int courseId, int disciplineId, int lessonId,
			String messageText, boolean isOutOfLimitUnreadMessagesFromUser, boolean isClosedByUser,
			boolean isClosedByAdminForce, boolean isCheckedByUser, boolean isCheckedByAdmin, Date dateOfAdd,
			Date dateOfUpdate) {
		super();
		this.id = id;
		this.userIdFrom = userIdFrom;
		this.userIdTo = userIdTo;
		this.courseId = courseId;
		this.disciplineId = disciplineId;
		this.lessonId = lessonId;
		this.messageText = messageText;
		this.isOutOfLimitUnreadMessagesFromUser = isOutOfLimitUnreadMessagesFromUser;
		this.isClosedByUser = isClosedByUser;
		this.isClosedByAdminForce = isClosedByAdminForce;
		this.isCheckedByUser = isCheckedByUser;
		this.isCheckedByAdmin = isCheckedByAdmin;
		this.dateOfAdd = dateOfAdd;
		this.dateOfUpdate = dateOfUpdate;
	}



	
	
}
