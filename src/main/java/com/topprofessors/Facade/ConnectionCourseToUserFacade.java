package com.topprofessors.Facade;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.ConnectionCourseToUser;
import com.topprofessors.Service.ConnectionCourseToUserService;

@Component
public class ConnectionCourseToUserFacade {

	@Autowired
	ConnectionCourseToUserService connectionCourseToUserService;
	
	
	public boolean setDoneByUserIdAndCourseId(int userId, int courseId) {
		
		ConnectionCourseToUser connectionCourseToUser = connectionCourseToUserService.findByCourseIdAndUserId(courseId, userId);
		
		Date dateCurrent = new Date();
		connectionCourseToUser.setDateOfDone(new Timestamp(dateCurrent.getTime()));
		connectionCourseToUser.setDateOfUpdate(new Timestamp(dateCurrent.getTime()));
		connectionCourseToUser.setIsDone(1);
		
		return connectionCourseToUserService.update(connectionCourseToUser);
	}
}
