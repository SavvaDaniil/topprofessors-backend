package com.topprofessors.DTO.Test;

import com.topprofessors.Interface.ValidatedObject;

public class TestActionDTO implements ValidatedObject {

	private int id;
	private String actionValue;
	private int questionId;
	private int questionAnswerId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActionValue() {
		return actionValue;
	}
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionAnswerId() {
		return questionAnswerId;
	}
	public void setQuestionAnswerId(int questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return this.id != 0 && this.actionValue != null;
	}
	
}
