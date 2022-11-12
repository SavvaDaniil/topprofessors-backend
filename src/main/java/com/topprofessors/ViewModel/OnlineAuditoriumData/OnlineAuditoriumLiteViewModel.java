package com.topprofessors.ViewModel.OnlineAuditoriumData;

public class OnlineAuditoriumLiteViewModel {

	private String youtubeVideoId;
	private boolean isOpen;
	
	public String getYoutubeVideoId() {
		return youtubeVideoId;
	}
	public void setYoutubeVideoId(String youtubeVideoId) {
		this.youtubeVideoId = youtubeVideoId;
	}
	public boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public OnlineAuditoriumLiteViewModel(String youtubeVideoId, boolean isOpen) {
		super();
		this.youtubeVideoId = youtubeVideoId;
		this.isOpen = isOpen;
	}

	
}
