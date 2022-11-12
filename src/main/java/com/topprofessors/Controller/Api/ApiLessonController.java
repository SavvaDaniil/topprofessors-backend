package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.Lesson.LessonGetDTO;
import com.topprofessors.Facade.LessonFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;

@RestController
@RequestMapping("api/lesson")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiLessonController {

    @Autowired
    private UserMiddleware userMiddleware;
    
    @Autowired
    LessonFacade lessonFacade;

    @GetMapping(value = "/slider/get/{lessonId}/{hashFolder}/{numberOfFile}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getSlider(
    		@PathVariable(value="lessonId") int lessonId, 
    		@PathVariable(value="hashFolder") String hashFolder,
    		@PathVariable(value="numberOfFile") int numberOfFile) {
    	return lessonFacade.getSlide(lessonId, hashFolder, numberOfFile);
    }
    
    
    @PostMapping(value = "/user/get")
	public JsonAnswerStatus get(@RequestBody LessonGetDTO lessonGetDTO) {
		if(!lessonGetDTO.isValid())return new JsonAnswerStatus("error", "no_data");
		
		return lessonFacade.getForUser(
				userMiddleware.getCurrentUserId(), 
				lessonGetDTO.getLessonId(), 
				lessonGetDTO.getDisciplineId(), 
				lessonGetDTO.getCourseId()
		);
	}
}
