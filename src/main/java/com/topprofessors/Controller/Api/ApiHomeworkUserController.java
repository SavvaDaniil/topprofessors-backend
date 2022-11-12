package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.Facade.HomeworkUserFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/homework_user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiHomeworkUserController {

    @Autowired
    private UserMiddleware userMiddleware;

    @Autowired
    private HomeworkUserFacade homeworkUserFacade;

    
    
	@PostMapping(value = "/user/add")
	public JsonAnswerStatus newFileFromUser(
			//@RequestBody DisciplineNewUserFileDTO disciplineNewUserFileDTO
			@RequestParam("courseId") int courseId,
			@RequestParam("disciplineId") int disciplineId,
			@RequestParam("homeworkId") int homeworkId,
			@RequestParam("uploadedFile") MultipartFile uploadedFile
		) {
		return homeworkUserFacade.addFromUser(
				userMiddleware.getCurrentUserId(),
				courseId,
				disciplineId,
				homeworkId,
				uploadedFile
			);
	}

	@GetMapping(value = "/set_done/{homerworkUserId}/{hash}")
	public JsonAnswerStatus setDone(
			@PathVariable(value="homerworkUserId") int homerworkUserId,
			@PathVariable(value="hash") String hash
			) {
		return homeworkUserFacade.setDoneByHash(homerworkUserId, hash);
	}

	@GetMapping(value = "/get_file/{filename}")
	public byte[] getPoster(@PathVariable(value="filename") String filename) {
		return homeworkUserFacade.getHomeworkUserFile(filename);
	}
}
