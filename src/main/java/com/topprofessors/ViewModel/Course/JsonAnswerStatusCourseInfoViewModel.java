package com.topprofessors.ViewModel.Course;

import com.topprofessors.ViewModel.JsonAnswerStatus;

public class JsonAnswerStatusCourseInfoViewModel extends JsonAnswerStatus {

	private CourseInfoViewModel courseInfoViewModel;

	public CourseInfoViewModel getCourseInfoViewModel() {
		return courseInfoViewModel;
	}

	public void setCourseInfoViewModel(CourseInfoViewModel courseInfoViewModel) {
		this.courseInfoViewModel = courseInfoViewModel;
	}

	public JsonAnswerStatusCourseInfoViewModel(String status, String errors) {
		super(status, errors);
	}
	
	public JsonAnswerStatusCourseInfoViewModel(String status, String errors, CourseInfoViewModel courseInfoViewModel) {
		super(status, errors);
		this.courseInfoViewModel = courseInfoViewModel;
	}
}
