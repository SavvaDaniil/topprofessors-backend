package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.TestQuestionAnswer.TestQuestionAnswerFromUserDTO;
import com.topprofessors.DTO.TestUser.TestUserFinishDTO;
import com.topprofessors.DTO.TestUser.TestUserGetQuestionDTO;
import com.topprofessors.Facade.TestUserFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/test_user_action")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiTestUserActionController {
	
}
