package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.ConnectionDisciplineToCourse;

public class ConnectionDisciplineToCourseFactory {

	public ConnectionDisciplineToCourse createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			ConnectionDisciplineToCourse connectionDisciplineToCourse = new ConnectionDisciplineToCourse();
			connectionDisciplineToCourse.setId(resultSet.getInt("id"));
			connectionDisciplineToCourse.setDisciplineId(resultSet.getInt("discipline_id"));
			connectionDisciplineToCourse.setCourseId(resultSet.getInt("course_id"));
			connectionDisciplineToCourse.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			connectionDisciplineToCourse.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			return connectionDisciplineToCourse;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
