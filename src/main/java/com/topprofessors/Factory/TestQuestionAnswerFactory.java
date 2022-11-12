package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.TestQuestionAnswer;

public class TestQuestionAnswerFactory {

	public TestQuestionAnswer createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			TestQuestionAnswer testQuestionAnswer = new TestQuestionAnswer();
			testQuestionAnswer.setId(resultSet.getInt("id"));
			testQuestionAnswer.setTestId(resultSet.getInt("test_id"));
			testQuestionAnswer.setTestQuestionId(resultSet.getInt("test_question_id"));
			testQuestionAnswer.setNumberOfAnswer(resultSet.getInt("number_of_answer"));
			testQuestionAnswer.setTypeOfAnswer(resultSet.getInt("type_of_answer"));
			testQuestionAnswer.setTextContent(resultSet.getString("text_content"));
			testQuestionAnswer.setIsCorrect(resultSet.getInt("is_correct"));
			testQuestionAnswer.setPoints(resultSet.getFloat("points"));
			testQuestionAnswer.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			testQuestionAnswer.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			return testQuestionAnswer;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
