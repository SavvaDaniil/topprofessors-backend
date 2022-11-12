package com.topprofessors.Facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Homework;
import com.topprofessors.Entity.User;
import com.topprofessors.Service.HomeworkService;
import com.topprofessors.ViewModel.Homework.HomeworkLiteForUserViewModel;
import com.topprofessors.ViewModel.HomeworkUser.HomeworkUserLiteViewModel;

@Component
public class HomeworkFacade {

	@Autowired
	HomeworkService homeworkService;
	
	@Lazy
	@Autowired
	HomeworkUserFacade homeworkUserFacade;
	
	public HomeworkLiteForUserViewModel getLiteForUserById(User user, int homerworkId) {

		Homework homework = homeworkService.findActiveById(homerworkId);
		if(homework == null)return null;
		
		HomeworkLiteForUserViewModel homeworkLiteForUserViewModel = this.getLite(homework);
		
		if(user != null) {
			HomeworkUserLiteViewModel homeworkUserLiteViewModel = homeworkUserFacade.getLiteByUserIdAndHomeworkId(user.getId(), homerworkId);
			if(homeworkUserLiteViewModel != null) {
				homeworkLiteForUserViewModel.setHomeworkUserLiteViewModel(homeworkUserLiteViewModel);
			}
		}
		
		return homeworkLiteForUserViewModel;
	}

	public HomeworkLiteForUserViewModel getLite(Homework homework) {
		if(homework == null)return null;
		return new HomeworkLiteForUserViewModel(
			homework.getId(),
			homework.getName(),
			homework.getDescription()
		);
	}
}
