package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.HomeworkUser;

public class HomeworkUserFactory {

	public HomeworkUser createFromResultSet(ResultSet resultSet) {
		
		if(resultSet == null)return null;
		try {
			HomeworkUser homeworkUser = new HomeworkUser();
			homeworkUser.setId(resultSet.getInt("id"));
			homeworkUser.setHomeworkId(resultSet.getInt("homework_id"));
			homeworkUser.setUserId(resultSet.getInt("user_id"));
			homeworkUser.setMessage(resultSet.getString("message"));
			
			homeworkUser.setIsViewed(resultSet.getInt("is_viewed"));
			homeworkUser.setIsAccepted(resultSet.getInt("is_accepted"));
			homeworkUser.setIsDenied(resultSet.getInt("is_denied"));
			
			homeworkUser.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			homeworkUser.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			homeworkUser.setFiletype(resultSet.getString("filetype"));
			
			return homeworkUser;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
