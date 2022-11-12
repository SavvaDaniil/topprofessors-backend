package com.topprofessors.ViewModel.User;

public class UserImgDocViewModel {

	private int index;
	private String src;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public UserImgDocViewModel(int index, String src) {
		super();
		this.index = index;
		this.src = src;
	}
	
}
