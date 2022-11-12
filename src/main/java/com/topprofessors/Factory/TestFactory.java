package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Test;

public class TestFactory {

	public Test createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Test test = new Test();
			test.setId(resultSet.getInt("id"));
			test.setName(resultSet.getString("name"));
			test.setStatus(resultSet.getInt("status"));
			test.setQuestionsByOrder0Random1(resultSet.getInt("questions_by_order_0_random_1"));
			test.setMaxSeenQuestions(resultSet.getInt("max_seen_questions"));
			test.setTypeOfTest(resultSet.getInt("type_of_test"));
			test.setNeededPoints(resultSet.getFloat("needed_points"));
			test.setDescription(resultSet.getString("description"));
			test.setDescriptionForClosedStatus(resultSet.getString("description_for_closed_status"));
			test.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			test.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			return test;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
