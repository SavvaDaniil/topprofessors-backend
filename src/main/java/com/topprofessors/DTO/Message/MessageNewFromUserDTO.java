package com.topprofessors.DTO.Message;

import com.topprofessors.Interface.ValidatedObject;

public class MessageNewFromUserDTO implements ValidatedObject {

	private int courseId;
	private int disciplineId;
	private int lessonId;
	private String messageText;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	@Override
	public boolean isValid() {
		return this.courseId != 0 && this.disciplineId != 0 && this.lessonId != 0 && this.messageText != null;
	}
	
}
