package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.TestQuestion;

public class TestQuestionFactory {

	public TestQuestion createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			TestQuestion testQuestion = new TestQuestion();
			testQuestion.setId(resultSet.getInt("id"));
			testQuestion.setTestId(resultSet.getInt("test_id"));
			testQuestion.setNumberOfQuestion(resultSet.getInt("number_of_question"));
			testQuestion.setTextContent(resultSet.getString("text_content"));
			testQuestion.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			testQuestion.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			return testQuestion;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
