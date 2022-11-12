package com.topprofessors.ViewModel.User;

public class UserLiteViewModel {

	private int id;
	private String username;
	private String secondname;
	private String firstname;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public UserLiteViewModel(int id, String username, String secondname, String firstname) {
		super();
		this.id = id;
		this.username = username;
		this.secondname = secondname;
		this.firstname = firstname;
	}
	
	
}
