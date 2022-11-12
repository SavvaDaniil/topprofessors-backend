package com.topprofessors.DTO.Discipline;

import org.springframework.web.multipart.MultipartFile;

public class DisciplineNewUserFileDTO {

	private int courseId;
	private int disciplineId;
	private int lessonId;
	private MultipartFile uploadedFile;
	
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
	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
}
