package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.Test.TestActionDTO;
import com.topprofessors.DTO.TestQuestionAnswer.TestQuestionAnswerFromUserDTO;
import com.topprofessors.Facade.TestFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiTestController {

    @Autowired
    private UserMiddleware userMiddleware;

    @Autowired
    private TestFacade testFacade;

	@PostMapping(value = "/user/{testId}/index")
    public JsonAnswerStatus getIndex(@PathVariable(value="testId") int testId) {
    	return testFacade.getIndexViewModel(userMiddleware.getCurrentUserId(), testId);
    }
	
	@RequestMapping(value = "/user/{testId}/start")
    public JsonAnswerStatus start(@PathVariable(value="testId") int testId) {
    	return testFacade.start(userMiddleware.getCurrentUserId(), testId, false);
    }

	@RequestMapping(value = "/user/{testId}/continue")
    public JsonAnswerStatus continueTest(@PathVariable(value="testId") int testId) {
    	return testFacade.start(userMiddleware.getCurrentUserId(), testId, true);
    }

}
