package com.topprofessors.Controller.Api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiIndexController {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private UserMiddleware userMiddleware;

	@RequestMapping
	public JsonAnswerStatus index() {
		int userId = userMiddleware.getUserIdFromCurrentAccessToken(request);
		//System.out.println("ApiIndexController index userId: " + userId);
		return userId != 0 ? new JsonAnswerStatus("success") : new JsonAnswerStatus("error", "not_auth");
	}
}
