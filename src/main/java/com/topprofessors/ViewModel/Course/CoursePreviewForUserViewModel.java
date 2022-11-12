package com.topprofessors.ViewModel.Course;

import com.topprofessors.Model.Course.CourseStatusTypeForUser;

public class CoursePreviewForUserViewModel {

	private int id;
	private String name;
	private String posterSrc;
	
	private CourseStatusTypeForUser status;

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

	public String getPosterSrc() {
		return posterSrc;
	}

	public void setPosterSrc(String posterSrc) {
		this.posterSrc = posterSrc;
	}

	public CourseStatusTypeForUser getStatus() {
		return status;
	}

	public void setStatus(CourseStatusTypeForUser status) {
		this.status = status;
	}

	public CoursePreviewForUserViewModel(int id, String name, String posterSrc, CourseStatusTypeForUser status) {
		super();
		this.id = id;
		this.name = name;
		this.posterSrc = posterSrc;
		this.status = status;
	}

	
	
	
}
