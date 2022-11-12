package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.DTO.OnlineAuditoriumMessage.OnlineAuditoriumMessageIsAnyNewDTO;
import com.topprofessors.DTO.OnlineAuditoriumMessage.OnlineAuditoriumMessageNewFromUserDTO;
import com.topprofessors.DTO.OnlineAuditoriumUserFile.OnlineAuditoriumUserFileNewFromUserDTO;
import com.topprofessors.Facade.OnlineAuditoriumMessageFacade;
import com.topprofessors.Facade.OnlineAuditoriumUserFileFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/online_auditorium_message")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiOnlineAuditoriumMessageController {

    @Autowired
    private UserMiddleware userMiddleware;

    @Autowired
    OnlineAuditoriumMessageFacade onlineAuditoriumMessageFacade;

    @Lazy
    @Autowired
    OnlineAuditoriumUserFileFacade onlineAuditoriumUserFileFacade;

	@PostMapping(value = "/user/is_any_new")
	public JsonAnswerStatus listAllParentPreviewForUser(
			@RequestBody OnlineAuditoriumMessageIsAnyNewDTO onlineAuditoriumMessageIsAnyNewDTO) {
		return onlineAuditoriumMessageFacade.isAnyNewForUser(
				userMiddleware.getCurrentUserId(), 
				onlineAuditoriumMessageIsAnyNewDTO.getLastDateOfChat()
			);
	}
	
	//OnlineAuditoriumMessageNewFromUserDTO
	@PostMapping(value = "/user/add")
	public JsonAnswerStatus addFromUser(
			@RequestBody OnlineAuditoriumMessageNewFromUserDTO onlineAuditoriumMessageNewFromUserDTO) {
		return onlineAuditoriumMessageFacade.add(
				userMiddleware.getCurrentUserId(), 
				onlineAuditoriumMessageNewFromUserDTO.getMessageText()
			);
	}
	
	//OnlineAuditoriumUserFileNewFromUserDTO
	@PostMapping(value = "/user/new_file")
	public JsonAnswerStatus newFileFromUser(
			@RequestParam("uploadedFile") MultipartFile uploadedFile) {
		return onlineAuditoriumUserFileFacade.newFileFromUser(
				userMiddleware.getCurrentUserId(), 
				uploadedFile
			);
	}
	
	
}
