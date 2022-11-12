package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Admin;

@Component
public class AdminFactory {

	public Admin createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Admin admin = new Admin();
			admin.setId(resultSet.getInt("id"));
			admin.setUsername(resultSet.getString("username"));
			admin.setPassword(resultSet.getString("password"));
			admin.setAuthKey(resultSet.getString("auth_key"));
			admin.setAccessToken(resultSet.getString("access_token"));
			admin.setActive(resultSet.getInt("active"));
			admin.setLevel(resultSet.getInt("level"));
			admin.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			
			admin.setFirstname(resultSet.getString("firstname"));
			admin.setPosition(resultSet.getString("position"));
			admin.setMain_0_rop_1(resultSet.getInt("main_0_rop_1"));
			admin.setRegion_id(resultSet.getInt("region_id"));
			
			admin.setPanel_admins(resultSet.getInt("panel_admins"));
			admin.setPanel_users(resultSet.getInt("panel_users"));
			admin.setPanel_messages(resultSet.getInt("panel_messages"));
			admin.setPanel_content(resultSet.getInt("panel_content"));
			admin.setPanel_old_users(resultSet.getInt("panel_old_users"));
			admin.setPanel_content_dangers(resultSet.getInt("panel_content_dangers"));
			admin.setAccess_of_media_manager(resultSet.getInt("access_of_media_manager"));
			admin.setAccess_to_delete_user(resultSet.getInt("access_to_delete_user"));
			admin.setPanel_analysis_discipline(resultSet.getInt("panel_analysis_discipline"));
			admin.setPanel_analytics(resultSet.getInt("panel_analytics"));
			admin.setPanel_online_audition(resultSet.getInt("panel_online_audition"));
			admin.setPanel_notice(resultSet.getInt("panel_notice"));
			
			
			return admin;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
