package com.topprofessors.DTO.Course;

import com.topprofessors.Interface.ValidatedObject;

public class CourseIdDTO implements ValidatedObject {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean isValid() {
		return this.id != 0;
	}
	
}
