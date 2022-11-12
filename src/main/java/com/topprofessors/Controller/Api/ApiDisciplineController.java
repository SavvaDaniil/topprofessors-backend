package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.DTO.Discipline.DisciplineGetInfoDTO;
import com.topprofessors.DTO.Discipline.DisciplineNewUserFileDTO;
import com.topprofessors.Facade.DisciplineFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/discipline")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiDisciplineController {

    @Autowired
    private UserMiddleware userMiddleware;

	@Autowired
	DisciplineFacade disciplineFacade;
	
	@PostMapping(value = "/user/get")
	public JsonAnswerStatus get(@RequestBody DisciplineGetInfoDTO disciplineGetInfoDTO) {
		if(!disciplineGetInfoDTO.isValid())return new JsonAnswerStatus("error", "no_data");
		return disciplineFacade.getInfoForUser(
				userMiddleware.getCurrentUserId(), 
				disciplineGetInfoDTO.getCourseId(), 
				disciplineGetInfoDTO.getDisciplineId(),
				disciplineGetInfoDTO.getNumberOfLesson()
		);
	}

	@PostMapping(value = "/user/new_file")
	public JsonAnswerStatus newFileFromUser(
			//@RequestBody DisciplineNewUserFileDTO disciplineNewUserFileDTO
			@RequestParam("courseId") int courseId,
			@RequestParam("disciplineId") int disciplineId,
			@RequestParam("lessonId") int lessonId,
			@RequestParam("uploadedFile") MultipartFile uploadedFile
		) {
		return disciplineFacade.newFileFromUser(
				userMiddleware.getCurrentUserId(),
				courseId,
				disciplineId,
				lessonId,
				uploadedFile
			);
	}
	
}
