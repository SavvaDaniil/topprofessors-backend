package com.topprofessors.Facade;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.OnlineAuditoriumMessage;
import com.topprofessors.Entity.User;
import com.topprofessors.Model.OnlineAuditoriumData.OnlineAuditoriumDataModel;
import com.topprofessors.Service.OnlineAuditoriumDataService;
import com.topprofessors.Service.OnlineAuditoriumMessageService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.OnlineAuditoriumMessage.OnlineAuditoriumMessageLiteViewModel;
import com.topprofessors.ViewModel.OnlineAuditoriumMessage.OnlineAuditoriumMessageLitesViewModel;

@Component
public class OnlineAuditoriumMessageFacade {
	
	@Autowired
	OnlineAuditoriumMessageService onlineAuditoriumMessageService;

	@Autowired
	UserFacade userFacade;

	@Autowired
	UserService userService;

	@Lazy
	@Autowired
	OnlineAuditoriumDataFacade onlineAuditoriumDataFacade;
	
	public JsonAnswerStatus isAnyNewForUser(int userId, long lastDateOfChatFromUserLong) {

		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Date lastDateOfChatFromUser = new Date();
		Date lastDateOfChatNew = new Date(lastDateOfChatFromUserLong);
		if(lastDateOfChatNew != null) {
			lastDateOfChatFromUser = lastDateOfChatNew;
		}
		

		OnlineAuditoriumDataModel onlineAuditoriumDataModel = onlineAuditoriumDataFacade.getModel();
		if(onlineAuditoriumDataModel.getIsOpen() == 0) {
			return new JsonAnswerStatus("error", "online_auditorium_closed");
		}
		boolean isAnyNew = false;
		ArrayList<OnlineAuditoriumMessageLiteViewModel> onlineAuditoriumMessageLiteViewModels = null;
		if(lastDateOfChatFromUser.compareTo(onlineAuditoriumDataModel.getLastDateOfChat()) < 0) {
			isAnyNew = true;
			onlineAuditoriumMessageLiteViewModels = this.listAllLiteForUser(user);
		}
		
		OnlineAuditoriumMessageLitesViewModel onlineAuditoriumMessageLitesViewModel = new OnlineAuditoriumMessageLitesViewModel(
			isAnyNew, 
			onlineAuditoriumDataModel.getLastDateOfChat(),
			onlineAuditoriumMessageLiteViewModels
		);
		
		//OnlineAuditoriumMessageLitesViewModel onlineAuditoriumMessageLitesViewModel = this.getMessagesForUserViewModel(lastDateOfChatFromUser);
		
		//if(onlineAuditoriumMessageLitesViewModel == null)return new JsonAnswerStatus("error", "try_get_messages");
		
		return new JsonAnswerStatus("success", null, onlineAuditoriumMessageLitesViewModel);
	}
	
	
	public ArrayList<OnlineAuditoriumMessageLiteViewModel> listAllLiteForUser(User userOwnerOfRequest){
		if(userOwnerOfRequest == null)return null;
		
		ArrayList<OnlineAuditoriumMessageLiteViewModel> onlineAuditoriumMessageLiteViewModels = 
				new ArrayList<OnlineAuditoriumMessageLiteViewModel>();
		ArrayList<OnlineAuditoriumMessage> onlineAuditoriumMessages = onlineAuditoriumMessageService.listAllForUser();

		ArrayList<User> users = userService.listAll();
		Optional<User> userOptional = null;
		
		for(OnlineAuditoriumMessage onlineAuditoriumMessage : onlineAuditoriumMessages) {
			
			if(onlineAuditoriumMessage.getUserId() != 0) {
				userOptional = users.stream().filter(userFromDb -> userFromDb.getId() == onlineAuditoriumMessage.getUserId()).findFirst();
			}
			
			onlineAuditoriumMessageLiteViewModels.add(
				new OnlineAuditoriumMessageLiteViewModel(
						onlineAuditoriumMessage.getId(),
						(userOptional != null ? userOptional.isPresent() ? userFacade.toLite(userOptional.get()) : null : null),
						(userOptional != null ? userOptional.isPresent() ? userOwnerOfRequest.getId() == userOptional.get().getId() : null : null),
						onlineAuditoriumMessage.getMessageText(),
						onlineAuditoriumMessage.getDateOfAdd()
				)
			);
		}
		
		return onlineAuditoriumMessageLiteViewModels;
	}
	
	public JsonAnswerStatus add(int userId, String messageText) {
		
		if(messageText == null)return new JsonAnswerStatus("error", "no_message_text");
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		OnlineAuditoriumMessage onlineAuditoriumMessage = this.add(user, messageText);
		if(onlineAuditoriumMessage == null)return new JsonAnswerStatus("error", "try_add");
		
		//update lastDateOfChat
		onlineAuditoriumDataFacade.updateLastDateOfChat(onlineAuditoriumMessage.getDateOfAdd());
		
		return new JsonAnswerStatus("success");
	}
	
	public OnlineAuditoriumMessage add(User user, String messageText) {
		if(user == null || messageText == null)return null;
		
		Date dateOfAdd = new Date();
		
		OnlineAuditoriumMessage onlineAuditoriumMessage = new OnlineAuditoriumMessage();
		onlineAuditoriumMessage.setUserId(user.getId());
		onlineAuditoriumMessage.setMessageText(messageText);
		onlineAuditoriumMessage.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
		onlineAuditoriumMessage.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
		
		onlineAuditoriumMessage = onlineAuditoriumMessageService.add(onlineAuditoriumMessage);
		
		return onlineAuditoriumMessage;
	}
}
