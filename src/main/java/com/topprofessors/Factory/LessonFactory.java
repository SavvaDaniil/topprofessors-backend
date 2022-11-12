package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Lesson;

public class LessonFactory {

	public Lesson createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Lesson lesson = new Lesson();
			lesson.setId(resultSet.getInt("id"));
			lesson.setName(resultSet.getString("name"));
			lesson.setNumberOfLesson(resultSet.getInt("number_of_lesson"));
			lesson.setVideoType(resultSet.getInt("video_type"));
			lesson.setVideoYoutubeLink(resultSet.getString("video_youtube_link"));
			lesson.setHash(resultSet.getString("hash"));
			lesson.setDisciplineId(resultSet.getInt("discipline_id"));
			lesson.setDateOfAdd(resultSet.getTimestamp("date_of_add"));
			lesson.setDateOfUpdate(resultSet.getTimestamp("date_of_update"));
			return lesson;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
