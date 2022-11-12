package com.topprofessors.ViewModel.User;


public class UserForgetViewModel {

	private int forgetId;
	private String accessToken;

	public int getForgetId() {
		return forgetId;
	}
	public void setForgetId(int forgetId) {
		this.forgetId = forgetId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public UserForgetViewModel(int forgetId) {
		super();
		this.forgetId = forgetId;
	}
	public UserForgetViewModel(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	
}
