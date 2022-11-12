package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Message;

public class MessageFactory {

	public Message createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Message message = new Message();
			message.setId(resultSet.getInt("id"));
			message.setUserIdFrom(resultSet.getInt("user_id_from"));
			message.setUserIdTo(resultSet.getInt("user_id_to"));
			message.setCourseId(resultSet.getInt("course_id"));
			message.setDisciplineId(resultSet.getInt("discipline_id"));
			message.setLessonId(resultSet.getInt("lesson_id"));
			message.setIsParent(resultSet.getInt("is_parent"));
			message.setParentMessageId(resultSet.getInt("parent_message_id"));
			message.setMessageText(resultSet.getString("message_text"));
			message.setIsDeleted(resultSet.getInt("is_deleted"));
			message.setIsCheckedByUser(resultSet.getInt("is_closed_by_user"));
			message.setIsClosedByAdminForce(resultSet.getInt("is_closed_by_admin_force"));
			message.setIsCheckedByAdmin(resultSet.getInt("is_checked_by_admin"));
			message.setIsCheckedByUser(resultSet.getInt("is_checked_by_user"));
			
			message.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			message.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			
			return message;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
