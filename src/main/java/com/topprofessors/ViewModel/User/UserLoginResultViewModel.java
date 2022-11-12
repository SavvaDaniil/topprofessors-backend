package com.topprofessors.ViewModel.User;

import com.topprofessors.ViewModel.JsonAnswerStatus;

public class UserLoginResultViewModel extends JsonAnswerStatus {

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	public UserLoginResultViewModel(String status, String errors) {
		super(status, errors);
	}

	public UserLoginResultViewModel(String status, String errors, String accessToken) {
		super(status, errors);
		this.accessToken = accessToken;
	}
}
