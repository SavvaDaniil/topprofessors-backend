package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.TestUser;

public class TestUserFactory {

	public TestUser createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			TestUser testUser = new TestUser();
			testUser.setId(resultSet.getInt("id"));
			testUser.setTestId(resultSet.getInt("test_id"));
			testUser.setUserId(resultSet.getInt("user_id"));
			testUser.setIsFinished(resultSet.getInt("is_finished"));
			testUser.setIsDone(resultSet.getInt("is_done"));
			testUser.setSeenQuestions(resultSet.getInt("seen_questions"));
			testUser.setPoints(resultSet.getFloat("points"));
			testUser.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			testUser.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			return testUser;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
