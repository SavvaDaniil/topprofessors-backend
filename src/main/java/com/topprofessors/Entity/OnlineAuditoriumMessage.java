package com.topprofessors.Entity;

import java.sql.Timestamp;

//@Entity
//@Table(name = "online_auditorium_message")
public class OnlineAuditoriumMessage {

	private int id;
	
	private int userId;
    private String messageText;
	private int isDeleted;
	private Timestamp dateOfAdd;
	private Timestamp dateOfUpdate;
	
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
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Timestamp dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}
	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	
}
