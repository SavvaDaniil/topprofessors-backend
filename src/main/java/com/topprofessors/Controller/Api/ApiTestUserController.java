package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.TestQuestionAnswer.TestQuestionAnswerFromUserDTO;
import com.topprofessors.DTO.TestUser.TestUserFinishDTO;
import com.topprofessors.DTO.TestUser.TestUserGetQuestionDTO;
import com.topprofessors.DTO.TestUser.TestUserGetResultDTO;
import com.topprofessors.Facade.TestUserFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/test_user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiTestUserController {


    @Autowired
    private UserMiddleware userMiddleware;
    
    @Autowired
    private TestUserFacade testUserFacade;

	@RequestMapping(value = "/user/update")
    public JsonAnswerStatus userAnswerUpdate(
    		@RequestBody TestQuestionAnswerFromUserDTO testQuestionAnswerFromUserDTO
		) {
		
    	return testUserFacade.userUpdate(
			userMiddleware.getCurrentUserId(), 
			testQuestionAnswerFromUserDTO.getTestId(), 
			testQuestionAnswerFromUserDTO.getTestQuestionId(), 
			testQuestionAnswerFromUserDTO.getTestQuestionAnswersSet()
		);
    }
	

	@RequestMapping(value = "/user/question/get")
	public JsonAnswerStatus userGetQuestion(
			@RequestBody TestUserGetQuestionDTO testUserGetQuestionDTO
		) {
		return testUserFacade.userGetQuestion(
			userMiddleware.getCurrentUserId(), 
			testUserGetQuestionDTO.getTestId(), 
			testUserGetQuestionDTO.getNumberOfQuestion()
		);
	}

	

	@RequestMapping(value = "/user/finish")
	public JsonAnswerStatus finish(
			@RequestBody TestUserFinishDTO testUserFinishDTO
		) {
		return testUserFacade.userInitResult(
			userMiddleware.getCurrentUserId(), 
			testUserFinishDTO.getTestId()
		);
	}

	

	@RequestMapping(value = "/user/get_result")
	public JsonAnswerStatus getResult(
			@RequestBody TestUserGetResultDTO testUserGetResultDTO
		) {
		return testUserFacade.getResultOfUser(
			userMiddleware.getCurrentUserId(), 
			testUserGetResultDTO.getTestId()
		);
	}
}
