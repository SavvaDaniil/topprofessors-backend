package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Discipline;

public class DisciplineFactory {

	public Discipline createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Discipline discipline = new Discipline();
			discipline.setId(resultSet.getInt("id"));
			discipline.setName(resultSet.getString("name"));
			discipline.setDescription(resultSet.getString("description"));
			discipline.setNumberOfLessons(resultSet.getInt("number_of_lessons"));
			discipline.setForceBlockAccess(resultSet.getInt("force_block_access"));
			discipline.setIsWithoutTest(resultSet.getInt("is_without_test"));
			discipline.setTestId(resultSet.getInt("test_id"));
			discipline.setIsWithoutHomework(resultSet.getInt("is_without_homework"));
			discipline.setHomeworkId(resultSet.getInt("homework_id"));
			return discipline;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
