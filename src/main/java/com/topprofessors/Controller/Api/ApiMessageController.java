package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.Message.MessagGetChatDTO;
import com.topprofessors.DTO.Message.MessageChatNewFromUserDTO;
import com.topprofessors.DTO.Message.MessageNewFromUserDTO;
import com.topprofessors.Facade.MessageFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/message")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiMessageController {

    @Autowired
    private UserMiddleware userMiddleware;

	@Autowired
	MessageFacade messageFacade;

	@PostMapping(value = "/user/start_chat")
	public JsonAnswerStatus chatStartByUser(@RequestBody MessageNewFromUserDTO messageNewFromUserDTO) {
		return messageFacade.startChatFromUser(
			userMiddleware.getCurrentUserId(),
			messageNewFromUserDTO.getCourseId(),
			messageNewFromUserDTO.getDisciplineId(),
			messageNewFromUserDTO.getLessonId(),
			messageNewFromUserDTO.getMessageText()
		);
	}

	@PostMapping(value = "/user/list_all_parent_preview")
	public JsonAnswerStatus listAllParentPreviewForUser() {
		return messageFacade.listAllChatPreviewsForUser(userMiddleware.getCurrentUserId());
	}


	@PostMapping(value = "/user/get_chat")
	public JsonAnswerStatus getChatForUser(
			@RequestBody MessagGetChatDTO messagGetChatDTO) {
		return messageFacade.getChatForUser(userMiddleware.getCurrentUserId(), messagGetChatDTO.getParentMessageId());
	}

	
	@PostMapping(value = "/user/chat/add")
	public JsonAnswerStatus chatNewFromUser(
			@RequestBody MessageChatNewFromUserDTO messageChatNewFromUserDTO) {
		if(!messageChatNewFromUserDTO.isValid())return new JsonAnswerStatus("error", "no_data");
		return messageFacade.newMessageInChatFromUser(
				userMiddleware.getCurrentUserId(),
				messageChatNewFromUserDTO.getParentMessageId(),
				messageChatNewFromUserDTO.getMessageContent()
		);
	}
	
}
