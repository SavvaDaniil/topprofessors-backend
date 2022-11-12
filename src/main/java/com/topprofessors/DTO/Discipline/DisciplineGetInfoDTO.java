package com.topprofessors.DTO.Discipline;

import com.topprofessors.Interface.ValidatedObject;

public class DisciplineGetInfoDTO implements ValidatedObject {

	private int disciplineId;
	private int courseId;
	private int numberOfLesson;

	public int getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getNumberOfLesson() {
		return numberOfLesson;
	}
	public void setNumberOfLesson(int numberOfLesson) {
		this.numberOfLesson = numberOfLesson;
	}
	
	@Override
	public boolean isValid() {
		return this.disciplineId != 0 && this.courseId != 0 && this.numberOfLesson != 0;
	}
	
}
