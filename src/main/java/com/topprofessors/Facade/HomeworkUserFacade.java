package com.topprofessors.Facade;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.Component.Md5Component;
import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.Homework;
import com.topprofessors.Entity.HomeworkUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Observer.HomeworkUserObserver;
import com.topprofessors.Service.CourseService;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.HomeworkService;
import com.topprofessors.Service.HomeworkUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Course.JsonAnswerStatusCoursePreviewForUserViewModels;
import com.topprofessors.ViewModel.HomeworkUser.HomeworkUserLiteViewModel;

@Component
public class HomeworkUserFacade {

	@Autowired
	HomeworkUserService homeworkUserService;

	@Autowired
	HomeworkService homeworkService;

	@Lazy
	@Autowired
	UserFacade userFacade;

	@Lazy
	@Autowired
	UserService userService;

	@Lazy
	@Autowired
	DisciplineService disciplineService;

	@Lazy
	@Autowired
	CourseService courseService;
	
	@Lazy
	@Autowired
	HomeworkUserObserver homeworkUserObserver;

	@Value("${host}")
	private String HOST;

	@Value("${api_host}")
	private String API_HOST;
	
	private final String UPLOAD_FOLDER_PATH = "uploads/homework_user";
	
	public JsonAnswerStatus addFromUser(int userId, int courseId, int disciplineId, int homeworkId, MultipartFile file) {

		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Course course = courseService.findById(courseId);
		if(course == null)return new JsonAnswerStatus("error", "course_not_found");
		
		Discipline discipline = disciplineService.findById(disciplineId);
		if(discipline == null)return new JsonAnswerStatus("error", "discipline_not_found");
		
		Homework homework = homeworkService.findById(homeworkId);
		if(homework == null)return new JsonAnswerStatus("error", "homework_not_found");
		
		HomeworkUser homeworkUser = homeworkUserService.findByUserIdAndHomeworkId(userId, homeworkId);
		
		if(file.isEmpty()) {
			return new JsonAnswerStatus("error", "no_file");
		}
		
		if(homeworkUser == null) {
			homeworkUser = new HomeworkUser();
			homeworkUser.setHomeworkId(homeworkId);
			homeworkUser.setUserId(userId);
			
			Date dateCurrent = new Date();
			
			homeworkUser.setDateOfAdd(new Timestamp(dateCurrent.getTime()));
			homeworkUser.setDateOfUpdate(new Timestamp(dateCurrent.getTime()));
			homeworkUser = homeworkUserService.add(homeworkUser);
			if(homeworkUser == null)return new JsonAnswerStatus("error", "try_add");
		}
		

        try {

        	String fileTypeFromUser = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        	if(!fileTypeFromUser.equals("pdf") && !fileTypeFromUser.equals("jpg") && !fileTypeFromUser.equals("jpeg")
        			 && !fileTypeFromUser.equals("png") && !fileTypeFromUser.equals("docx") && !fileTypeFromUser.equals("doc")) {
        		return new JsonAnswerStatus("error", "denied_type");
        	}
        	
    		String filenameWithoutType = this.getFileNameHashWithoutType(homeworkId, userId);
    		if(filenameWithoutType == null)return new JsonAnswerStatus("error", "fail_create_filename");
        	
            if(! new File(UPLOAD_FOLDER_PATH).exists())
            {
                new File(UPLOAD_FOLDER_PATH).mkdir();
            }

            //System.out.println("realPathtoUploads: " + UPLOAD_FOLDER_PATH);
            
            String fileNameWithType = filenameWithoutType + "." + fileTypeFromUser;
            
            File uploadFolderPathDest = new File(UPLOAD_FOLDER_PATH);
            
            // Convert to absolute path
            File destFile = new File(uploadFolderPathDest.getAbsolutePath(), fileNameWithType);
            System.out.println("filePathDest.getAbsolutePath(): " + uploadFolderPathDest.getAbsolutePath());
            if (!uploadFolderPathDest.exists()) {
            	uploadFolderPathDest.mkdirs();
            }
            file.transferTo(destFile);


            Date dateCurrent = new Date();
            homeworkUser.setFiletype(fileTypeFromUser);
            homeworkUser.setIsAccepted(1);
			homeworkUser.setDateOfUpdate(new Timestamp(dateCurrent.getTime()));
			if(!homeworkUserService.update(homeworkUser))return new JsonAnswerStatus("error", "try_update_homework_user");


            
            String urlToFile = API_HOST + "/api/homework_user/get_file/" +  fileNameWithType;
            String linkForDoneByHash = this.genLinkForDoneByHash(homeworkUser.getId());
            
            if(!homeworkUserObserver.sendHomeworUserFileToAdminFromUser(
            		user,
            		course,
            		discipline,
            		homework,
            		homeworkUser,
            		destFile, 
            		urlToFile,
            		linkForDoneByHash
            		)
        		)
            	return new JsonAnswerStatus("error", "fail_send_email_to_admin");
            
			this.userFacade.checkCoursesForDone(user);
			
			return new JsonAnswerStatus("success");
            
        } catch(Exception ex) {
        	System.out.println("DisciplineFacade newFileFromUser exception: " + ex);
        }
		
		return new JsonAnswerStatus("error", null);
	}
	
	public HomeworkUserLiteViewModel getLiteByUserIdAndHomeworkId(int userId, int homeworkId) {
		
		HomeworkUser homeworkUser = homeworkUserService.findByUserIdAndHomeworkId(userId, homeworkId);
		if(homeworkUser == null)return null;
		
		return new HomeworkUserLiteViewModel(
				homeworkUser.getId(),
				homeworkUser.getHomeworkId(),
				homeworkUser.getUserId(),
				homeworkUser.getMessage(),
				homeworkUser.getIsViewed(),
				homeworkUser.getIsAccepted(),
				homeworkUser.getIsDenied()
		);
	}
	

	public byte[] getHomeworkUserFile(String fileName) {
		//String fileName = "poster.jpg";
		File filePathDest = new File(this.UPLOAD_FOLDER_PATH + "/");
        File destFile = new File(filePathDest.getAbsolutePath(), fileName);
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {
        	try {
	        	Path path = Paths.get(destFile.getAbsolutePath());
	            byte[] arr = Files.readAllBytes(path);
	            return arr;
        	} catch(Exception ex) {
            	System.out.println("getHomeworkUserFile Exception: " + ex.getMessage());
        	}
        }
        return null;
	}
	
	public JsonAnswerStatus setDoneByHash(int homerworkUserId, String hash) {
		
		HomeworkUser homeworkUser = homeworkUserService.findById(homerworkUserId);
		if(homeworkUser == null)return new JsonAnswerStatus("error", "homework_not_found");
		
		if(!this.genHashForDone(homerworkUserId).equals(hash))return new JsonAnswerStatus("error", "wrong_hash");
		
		if(homeworkUser.getIsAccepted() == 1)return new JsonAnswerStatus("success");
		
		Date dateCurrent = new Date();
		homeworkUser.setIsAccepted(1);
		homeworkUser.setDateOfUpdate(new Timestamp(dateCurrent.getTime()));
		if(!homeworkUserService.update(homeworkUser))return new JsonAnswerStatus("error", "try_save");

		if(homeworkUser.getUserId() != 0) {
			User user = userService.findById(homeworkUser.getUserId());
			if(user == null)return new JsonAnswerStatus("error", "user_not_found");
			userFacade.checkCoursesForDone(user);
		}
		
		return new JsonAnswerStatus("success");
	}
	
	
	
	public String getFileNameHashWithoutType(int homerworkId, int userId) {
		Md5Component md5Component = new Md5Component();
		return homerworkId + "_" + userId + "_" + md5Component.getHash(homerworkId + userId + "fshfOCA39rj39CJSAKef");
	}

	public String genLinkForDoneByHash(int homerworkUserId) {
		String hash = this.genHashForDone(homerworkUserId);
		return this.API_HOST + "/api/homework_user/set_done/" + homerworkUserId + "/" + hash;
	}
	
	public String genHashForDone(int homerworkUserId) {
		Md5Component md5Component = new Md5Component();
		return homerworkUserId + "_" + md5Component.getHash(homerworkUserId + "dsvgnshv8se");
	}
	
	
	
}
