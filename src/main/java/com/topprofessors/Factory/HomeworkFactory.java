package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Homework;

public class HomeworkFactory {

	public Homework createFromResultSet(ResultSet resultSet) {
		
		if(resultSet == null)return null;
		try {
			Homework homework = new Homework();
			homework.setId(resultSet.getInt("id"));
			homework.setName(resultSet.getString("name"));
			homework.setDescription(resultSet.getString("description"));
			homework.setStatus(resultSet.getInt("status"));
			homework.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			homework.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			return homework;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
