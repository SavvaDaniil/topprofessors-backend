package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.TestUserAction;

public class TestUserActionFactory {

	public TestUserAction createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			TestUserAction testUserAction = new TestUserAction();
			testUserAction.setId(resultSet.getInt("id"));
			testUserAction.setTestUserId(resultSet.getInt("test_user_id"));
			testUserAction.setTestId(resultSet.getInt("test_id"));
			testUserAction.setUserId(resultSet.getInt("user_id"));
			testUserAction.setNumberOfQuesion(resultSet.getInt("number_of_quesion"));
			testUserAction.setTestQuestionId(resultSet.getInt("test_question_id"));
			testUserAction.setTestQuestionAnswerId(resultSet.getInt("test_question_answer_id"));
			testUserAction.setDateOfAdd(resultSet.getDate("date_of_add"));
			testUserAction.setDateOfUpdate(resultSet.getDate("date_of_update"));
			
			return testUserAction;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
