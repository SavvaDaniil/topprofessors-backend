package com.topprofessors.DTO.User;

import com.topprofessors.Interface.ValidatedObject;

public class UserLoginDTO implements ValidatedObject {

    private String username;
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean isValid() {
		return username != null && password != null;
	}
}
