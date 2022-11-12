package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.OnlineAuditoriumData;

public class OnlineAuditoriumDataFactory {

	public OnlineAuditoriumData createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			OnlineAuditoriumData onlineAuditoriumData = new OnlineAuditoriumData();
			onlineAuditoriumData.setId(resultSet.getInt("id"));
			onlineAuditoriumData.setName(resultSet.getString("name"));
			onlineAuditoriumData.setStr_value(resultSet.getString("str_value"));
			onlineAuditoriumData.setInt_value(resultSet.getInt("int_value"));
			onlineAuditoriumData.setTimestamp_value(resultSet.getTimestamp("timestamp_value"));

			return onlineAuditoriumData;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
