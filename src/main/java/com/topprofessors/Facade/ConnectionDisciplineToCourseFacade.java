package com.topprofessors.Facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topprofessors.Service.ConnectionDisciplineToCourseService;

@Component
public class ConnectionDisciplineToCourseFacade {

	@Autowired
	ConnectionDisciplineToCourseService connectionDisciplineToCourseService;
	
	
	
}
