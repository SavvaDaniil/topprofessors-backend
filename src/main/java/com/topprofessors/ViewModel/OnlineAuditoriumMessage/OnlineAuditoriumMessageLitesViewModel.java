package com.topprofessors.ViewModel.OnlineAuditoriumMessage;

import java.util.ArrayList;
import java.util.Date;

public class OnlineAuditoriumMessageLitesViewModel {

	private boolean isAnyNew;
	private Date lastDateOfChat;
	private ArrayList<OnlineAuditoriumMessageLiteViewModel> onlineAuditoriumMessageLiteViewModels;
	
	public boolean getIsAnyNew() {
		return isAnyNew;
	}
	public void setIsAnyNew(boolean isAnyNew) {
		this.isAnyNew = isAnyNew;
	}
	public Date getLastDateOfChat() {
		return lastDateOfChat;
	}
	public void setLastDateOfChat(Date lastDateOfChat) {
		this.lastDateOfChat = lastDateOfChat;
	}
	public ArrayList<OnlineAuditoriumMessageLiteViewModel> getOnlineAuditoriumMessageLiteViewModels() {
		return onlineAuditoriumMessageLiteViewModels;
	}
	public void setOnlineAuditoriumMessageLiteViewModels(
			ArrayList<OnlineAuditoriumMessageLiteViewModel> onlineAuditoriumMessageLiteViewModels) {
		this.onlineAuditoriumMessageLiteViewModels = onlineAuditoriumMessageLiteViewModels;
	}
	
	public OnlineAuditoriumMessageLitesViewModel(boolean isAnyNew, Date lastDateOfChat,
			ArrayList<OnlineAuditoriumMessageLiteViewModel> onlineAuditoriumMessageLiteViewModels) {
		super();
		this.isAnyNew = isAnyNew;
		this.lastDateOfChat = lastDateOfChat;
		this.onlineAuditoriumMessageLiteViewModels = onlineAuditoriumMessageLiteViewModels;
	}
	
	
}
