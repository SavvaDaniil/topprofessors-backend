package com.topprofessors.Facade;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.OnlineAuditoriumData;
import com.topprofessors.Model.OnlineAuditoriumData.OnlineAuditoriumDataModel;
import com.topprofessors.Service.OnlineAuditoriumDataService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.OnlineAuditoriumData.OnlineAuditoriumLiteViewModel;

@Component
public class OnlineAuditoriumDataFacade {

	@Autowired
	OnlineAuditoriumDataService onlineAuditoriumDataService;
	
	public JsonAnswerStatus getLiteForUser() {
		OnlineAuditoriumLiteViewModel onlineAuditoriumLiteViewModel = this.getLite();
		if(onlineAuditoriumLiteViewModel == null)return new JsonAnswerStatus("error");
		
		return new JsonAnswerStatus("success", null, onlineAuditoriumLiteViewModel);
	}
	
	public OnlineAuditoriumLiteViewModel getLite() {
		
		OnlineAuditoriumDataModel onlineAuditoriumDataModel = this.getModel();
		if(onlineAuditoriumDataModel == null)return null;
		
		return new OnlineAuditoriumLiteViewModel(
			onlineAuditoriumDataModel.getYoutubeVideoId(), 
			(onlineAuditoriumDataModel.getIsOpen() == 1)
		);
	}
	

	public boolean updateLastDateOfChat(Date lastDateOfChat) {
		Timestamp lastDateOfChatTimestamp = new Timestamp(lastDateOfChat.getTime());
		return updateLastDateOfChat(lastDateOfChatTimestamp);
	}
	public boolean updateLastDateOfChat(Timestamp lastDateOfChat) {

		OnlineAuditoriumData onlineAuditoriumDataLastDateOfChat = onlineAuditoriumDataService.findValueByName("last_date_of_chat");
		if(onlineAuditoriumDataLastDateOfChat == null)return false;
		
		onlineAuditoriumDataLastDateOfChat.setTimestamp_value(lastDateOfChat);
		
		return onlineAuditoriumDataService.update(onlineAuditoriumDataLastDateOfChat);
	}
	
	
	public OnlineAuditoriumDataModel getModel() {
		OnlineAuditoriumData onlineAuditoriumDataYoutubeVideoId = onlineAuditoriumDataService.findValueByName("youtube_video_id");
		if(onlineAuditoriumDataYoutubeVideoId == null) {
			onlineAuditoriumDataYoutubeVideoId = new OnlineAuditoriumData();
			onlineAuditoriumDataYoutubeVideoId.setName("youtube_video_id");
			onlineAuditoriumDataYoutubeVideoId = onlineAuditoriumDataService.add(onlineAuditoriumDataYoutubeVideoId);
			if(onlineAuditoriumDataYoutubeVideoId == null)return null;
		}
		String youtubeVideoId = onlineAuditoriumDataYoutubeVideoId.getStr_value();
		
		OnlineAuditoriumData onlineAuditoriumDataIsOpen = onlineAuditoriumDataService.findValueByName("is_open");
		if(onlineAuditoriumDataIsOpen == null) {
			onlineAuditoriumDataIsOpen = new OnlineAuditoriumData();
			onlineAuditoriumDataIsOpen.setName("is_open");
			onlineAuditoriumDataIsOpen = onlineAuditoriumDataService.add(onlineAuditoriumDataIsOpen);
			if(onlineAuditoriumDataIsOpen == null)return null;
		}
		int isOpen = onlineAuditoriumDataIsOpen.getInt_value();
		

		
		OnlineAuditoriumData onlineAuditoriumDataIsAvailableUploadUserFile = onlineAuditoriumDataService.findValueByName("is_available_upload_user_file");
		if(onlineAuditoriumDataIsAvailableUploadUserFile == null) {
			onlineAuditoriumDataIsAvailableUploadUserFile = new OnlineAuditoriumData();
			onlineAuditoriumDataIsAvailableUploadUserFile.setName("is_available_upload_user_file");
			onlineAuditoriumDataIsAvailableUploadUserFile = onlineAuditoriumDataService.add(onlineAuditoriumDataIsAvailableUploadUserFile);
			if(onlineAuditoriumDataIsAvailableUploadUserFile == null)return null;
		}
		int is_available_upload_user_file = onlineAuditoriumDataIsAvailableUploadUserFile.getInt_value();
		

		OnlineAuditoriumData onlineAuditoriumDataLastDateOfChat = onlineAuditoriumDataService.findValueByName("last_date_of_chat");
		if(onlineAuditoriumDataLastDateOfChat == null) {
			onlineAuditoriumDataLastDateOfChat = new OnlineAuditoriumData();
			onlineAuditoriumDataLastDateOfChat.setName("last_date_of_chat");
			onlineAuditoriumDataLastDateOfChat.setTimestamp_value(new Timestamp((new Date()).getTime()));
			onlineAuditoriumDataLastDateOfChat = onlineAuditoriumDataService.add(onlineAuditoriumDataYoutubeVideoId);
			if(onlineAuditoriumDataLastDateOfChat == null)return null;
		}
		Date lastDateOfChat = (onlineAuditoriumDataLastDateOfChat.getTimestamp_value() == null ? new Date() 
				: new Date(onlineAuditoriumDataLastDateOfChat.getTimestamp_value().getTime()));
		
		
		return new OnlineAuditoriumDataModel(youtubeVideoId, isOpen, is_available_upload_user_file, lastDateOfChat);
	}
}
