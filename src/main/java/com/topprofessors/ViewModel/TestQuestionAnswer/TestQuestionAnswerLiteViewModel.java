package com.topprofessors.ViewModel.TestQuestionAnswer;

public class TestQuestionAnswerLiteViewModel {

	private int id;
	private int numberOfAnswer;
	private int typeOfAnswer;
    private String textContent;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfAnswer() {
		return numberOfAnswer;
	}
	public void setNumberOfAnswer(int numberOfAnswer) {
		this.numberOfAnswer = numberOfAnswer;
	}
	public int getTypeOfAnswer() {
		return typeOfAnswer;
	}
	public void setTypeOfAnswer(int typeOfAnswer) {
		this.typeOfAnswer = typeOfAnswer;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public TestQuestionAnswerLiteViewModel(int id, int numberOfAnswer, int typeOfAnswer, String textContent) {
		super();
		this.id = id;
		this.numberOfAnswer = numberOfAnswer;
		this.typeOfAnswer = typeOfAnswer;
		this.textContent = textContent;
	}
    
    
	
}
