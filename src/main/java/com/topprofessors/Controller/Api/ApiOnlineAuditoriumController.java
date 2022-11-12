package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.Facade.OnlineAuditoriumDataFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/online_auditorium")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiOnlineAuditoriumController {

    @Autowired
    private UserMiddleware userMiddleware;
    
	@Autowired
	OnlineAuditoriumDataFacade onlineAuditoriumDataFacade;


	@PostMapping(value = "/user/get_lite")
	public JsonAnswerStatus listAllParentPreviewForUser() {
		return onlineAuditoriumDataFacade.getLiteForUser();
	}

    
	
}
