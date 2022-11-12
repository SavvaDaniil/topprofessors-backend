package com.topprofessors.ViewModel.Course;

import java.util.ArrayList;

import com.topprofessors.ViewModel.JsonAnswerStatus;

public class JsonAnswerStatusCoursePreviewForUserViewModels extends JsonAnswerStatus {

	private ArrayList<CoursePreviewForUserViewModel> coursePreviewForUserViewModels;
	
	public ArrayList<CoursePreviewForUserViewModel> getCoursePreviewForUserViewModels() {
		return coursePreviewForUserViewModels;
	}
	public void setCoursePreviewForUserViewModels(ArrayList<CoursePreviewForUserViewModel> coursePreviewForUserViewModels) {
		this.coursePreviewForUserViewModels = coursePreviewForUserViewModels;
	}


	public JsonAnswerStatusCoursePreviewForUserViewModels(String status, String errors) {
		super(status, errors);
	}
	public JsonAnswerStatusCoursePreviewForUserViewModels(String status, String errors, ArrayList<CoursePreviewForUserViewModel> coursePreviewForUserViewModels) {
		super(status, errors);
		this.coursePreviewForUserViewModels = coursePreviewForUserViewModels;
	}
}
