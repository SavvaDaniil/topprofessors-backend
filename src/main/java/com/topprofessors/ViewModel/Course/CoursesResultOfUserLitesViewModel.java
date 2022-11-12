package com.topprofessors.ViewModel.Course;

import java.util.ArrayList;

public class CoursesResultOfUserLitesViewModel {

	ArrayList<CourseResultOfUserLiteViewModel> courseResultOfUserLiteViewModels;

	public ArrayList<CourseResultOfUserLiteViewModel> getCourseResultOfUserLiteViewModels() {
		return courseResultOfUserLiteViewModels;
	}

	public void setCourseResultOfUserLiteViewModels(
			ArrayList<CourseResultOfUserLiteViewModel> courseResultOfUserLiteViewModels) {
		this.courseResultOfUserLiteViewModels = courseResultOfUserLiteViewModels;
	}

	public CoursesResultOfUserLitesViewModel(
			ArrayList<CourseResultOfUserLiteViewModel> courseResultOfUserLiteViewModels) {
		super();
		this.courseResultOfUserLiteViewModels = courseResultOfUserLiteViewModels;
	}
	
}
