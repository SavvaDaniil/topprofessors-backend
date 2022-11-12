package com.topprofessors.DTO.Lesson;

import com.topprofessors.Interface.ValidatedObject;

public class LessonGetDTO implements ValidatedObject {

	private int courseId;
	private int disciplineId;
	private int lessonId;
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
	
	@Override
	public boolean isValid() {
		return this.courseId != 0 && this.disciplineId != 0 && this.lessonId != 0;
	}
	
	
}
