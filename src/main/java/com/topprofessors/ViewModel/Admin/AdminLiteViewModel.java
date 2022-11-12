package com.topprofessors.ViewModel.Admin;

public class AdminLiteViewModel {

	private int id;
	private String position;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public AdminLiteViewModel(int id, String position) {
		super();
		this.id = id;
		this.position = position;
	}
	
}
