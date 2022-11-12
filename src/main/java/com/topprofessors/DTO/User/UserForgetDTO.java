package com.topprofessors.DTO.User;

public class UserForgetDTO {

	private int step;
	private String username;
	private int forgetId;
	private String code;
	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getForgetId() {
		return forgetId;
	}
	public void setForgetId(int forgetId) {
		this.forgetId = forgetId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
