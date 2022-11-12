package com.topprofessors.Facade;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.Entity.User;
import com.topprofessors.Model.OnlineAuditoriumData.OnlineAuditoriumDataModel;
import com.topprofessors.Observer.OnlineAuditoriumUserFileObserver;
import com.topprofessors.Service.OnlineAuditoriumUserFileService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@Component
public class OnlineAuditoriumUserFileFacade {

	@Lazy
	@Autowired
	OnlineAuditoriumDataFacade onlineAuditoriumDataFacade;
	
	@Autowired
	OnlineAuditoriumUserFileService onlineAuditoriumUserFileService;

	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	OnlineAuditoriumUserFileObserver onlineAuditoriumUserFileObserver;
	
	//private final String HOST = "https://topprofessors.ru";
	private final String API_HOST = "https://api.topprofessors.ru";
	private final String UPLOAD_FOLDER_PATH = "uploads/auditorium_online/user_file";
	
	public JsonAnswerStatus newFileFromUser(int userId, MultipartFile file) {

		if(file.isEmpty())return new JsonAnswerStatus("error", "no_file");
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		OnlineAuditoriumDataModel onlineAuditoriumDataModel = onlineAuditoriumDataFacade.getModel();
		if(onlineAuditoriumDataModel.getIsAvailableUploadUserFile() == 0)return new JsonAnswerStatus("error", "upload_closed");

        try {

    		String filenameWithoutType = onlineAuditoriumUserFileService.generateFilenameWithoutType(user);
    		if(filenameWithoutType == null)return new JsonAnswerStatus("error", "fail_create_filename");
        	
            if(! new File(UPLOAD_FOLDER_PATH).exists())
            {
                new File(UPLOAD_FOLDER_PATH).mkdir();
            }

            System.out.println("realPathtoUploads: " + UPLOAD_FOLDER_PATH);
            
            File uploadFolderPathDest = new File(UPLOAD_FOLDER_PATH);
            
            // Convert to absolute path
            File destFile = new File(uploadFolderPathDest.getAbsolutePath(), filenameWithoutType + ".jpg");
            System.out.println("filePathDest.getAbsolutePath(): " + uploadFolderPathDest.getAbsolutePath());
            if (!uploadFolderPathDest.exists()) {
            	uploadFolderPathDest.mkdirs();
            }
            file.transferTo(destFile);

            String urlToFile = API_HOST + "/" + UPLOAD_FOLDER_PATH + "/" +  filenameWithoutType + ".jpg";
            if(!onlineAuditoriumUserFileObserver.sendFileToAdminFromUser(user, destFile, urlToFile))
            	return new JsonAnswerStatus("error", "fail_send_email_to_admin");

			return new JsonAnswerStatus("success");
            
        } catch(Exception ex) {
        	System.out.println("OnlineAuditoriumUserFileFacade newFileFromUser exception: " + ex);
        }
		
		return new JsonAnswerStatus("error", null);
	}
	
}
