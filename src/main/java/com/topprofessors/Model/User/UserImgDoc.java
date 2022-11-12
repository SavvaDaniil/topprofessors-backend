package com.topprofessors.Model.User;

public enum UserImgDoc {
	DIPLOM("diplom"),
	PASSPORT("passport"),
	STATEMENT("statement"),
	CHANGESECONDNAME("changesecondname");
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	private UserImgDoc(String title) {
		this.title = title;
	}
	
}
