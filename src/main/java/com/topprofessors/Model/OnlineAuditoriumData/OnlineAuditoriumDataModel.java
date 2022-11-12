package com.topprofessors.Model.OnlineAuditoriumData;

import java.util.Date;

public class OnlineAuditoriumDataModel {

	private String youtubeVideoId;
	private int isOpen;
	private int isAvailableUploadUserFile;
	private Date lastDateOfChat;
	
	public String getYoutubeVideoId() {
		return youtubeVideoId;
	}
	public void setYoutubeVideoId(String youtubeVideoId) {
		this.youtubeVideoId = youtubeVideoId;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getIsAvailableUploadUserFile() {
		return isAvailableUploadUserFile;
	}
	public void setIsAvailableUploadUserFile(int isAvailableUploadUserFile) {
		this.isAvailableUploadUserFile = isAvailableUploadUserFile;
	}
	public Date getLastDateOfChat() {
		return lastDateOfChat;
	}
	public void setLastDateOfChat(Date lastDateOfChat) {
		this.lastDateOfChat = lastDateOfChat;
	}
	
	public OnlineAuditoriumDataModel(String youtubeVideoId, int isOpen, int isAvailableUploadUserFile,
			Date lastDateOfChat) {
		super();
		this.youtubeVideoId = youtubeVideoId;
		this.isOpen = isOpen;
		this.isAvailableUploadUserFile = isAvailableUploadUserFile;
		this.lastDateOfChat = lastDateOfChat;
	}
	
	
	
}
