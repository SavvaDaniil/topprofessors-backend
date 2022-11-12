package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Course;

public class CourseFactory {

	public Course createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Course course = new Course();
			course.setId(resultSet.getInt("id"));
			course.setName(resultSet.getString("name"));
			course.setDescription(resultSet.getString("description"));
			course.setActive(resultSet.getInt("active"));
			course.setIsVisible(resultSet.getInt("is_visible"));
			course.setIs_ready(resultSet.getInt("is_ready"));
			course.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			course.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			return course;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
