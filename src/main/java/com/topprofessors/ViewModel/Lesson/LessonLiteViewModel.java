package com.topprofessors.ViewModel.Lesson;

public class LessonLiteViewModel {

	private int id;
	private String name;
	private int numberOfLesson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfLesson() {
		return numberOfLesson;
	}
	public void setNumberOfLesson(int numberOfLesson) {
		this.numberOfLesson = numberOfLesson;
	}
	
	public LessonLiteViewModel(int id, String name, int numberOfLesson) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfLesson = numberOfLesson;
	}

}
