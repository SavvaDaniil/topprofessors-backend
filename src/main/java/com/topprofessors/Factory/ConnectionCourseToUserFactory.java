package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.ConnectionCourseToUser;

public class ConnectionCourseToUserFactory {

	public ConnectionCourseToUser createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			ConnectionCourseToUser connectionCourseToUser = new ConnectionCourseToUser();
			connectionCourseToUser.setId(resultSet.getInt("id"));
			connectionCourseToUser.setCourseId(resultSet.getInt("course_id"));
			connectionCourseToUser.setUserId(resultSet.getInt("user_id"));
			connectionCourseToUser.setActive(resultSet.getInt("active"));
			connectionCourseToUser.setIsDone(resultSet.getInt("is_done"));
			connectionCourseToUser.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			connectionCourseToUser.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			connectionCourseToUser.setDateOfDone(resultSet.getTimestamp("date_of_done"));
			return connectionCourseToUser;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
