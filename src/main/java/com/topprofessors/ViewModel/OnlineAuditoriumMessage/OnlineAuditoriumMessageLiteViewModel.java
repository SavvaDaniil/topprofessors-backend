package com.topprofessors.ViewModel.OnlineAuditoriumMessage;

import java.util.Date;

import com.topprofessors.ViewModel.User.UserLiteViewModel;

public class OnlineAuditoriumMessageLiteViewModel {

	private int id;
	private UserLiteViewModel userLiteViewModel;
	private boolean isOwnerOfRequest;
	private String messageContent;
	private Date dateOfAdd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserLiteViewModel getUserLiteViewModel() {
		return userLiteViewModel;
	}
	public void setUserLiteViewModel(UserLiteViewModel userLiteViewModel) {
		this.userLiteViewModel = userLiteViewModel;
	}
	public boolean getIsOwnerOfRequest() {
		return isOwnerOfRequest;
	}
	public void setIsOwnerOfRequest(boolean isOwnerOfRequest) {
		this.isOwnerOfRequest = isOwnerOfRequest;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	
	public OnlineAuditoriumMessageLiteViewModel(int id, UserLiteViewModel userLiteViewModel, boolean isOwnerOfRequest,
			String messageContent, Date dateOfAdd) {
		super();
		this.id = id;
		this.userLiteViewModel = userLiteViewModel;
		this.isOwnerOfRequest = isOwnerOfRequest;
		this.messageContent = messageContent;
		this.dateOfAdd = dateOfAdd;
	}


	
	
}
