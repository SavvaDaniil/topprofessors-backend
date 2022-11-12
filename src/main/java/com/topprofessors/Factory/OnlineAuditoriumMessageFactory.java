package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.OnlineAuditoriumMessage;

public class OnlineAuditoriumMessageFactory {

	public OnlineAuditoriumMessage createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			OnlineAuditoriumMessage onlineAuditoriumMessage = new OnlineAuditoriumMessage();
			onlineAuditoriumMessage.setId(resultSet.getInt("id"));
			onlineAuditoriumMessage.setUserId(resultSet.getInt("user_id"));
			onlineAuditoriumMessage.setMessageText(resultSet.getString("message_text"));
			onlineAuditoriumMessage.setIsDeleted(resultSet.getInt("is_deleted"));
			
			onlineAuditoriumMessage.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			onlineAuditoriumMessage.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			return onlineAuditoriumMessage;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
