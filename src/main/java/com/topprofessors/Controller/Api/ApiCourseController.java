package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.DTO.Course.CourseIdDTO;
import com.topprofessors.Facade.CourseFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Course.JsonAnswerStatusCourseInfoViewModel;
import com.topprofessors.ViewModel.Course.JsonAnswerStatusCoursePreviewForUserViewModels;

@RestController
@RequestMapping("api/course")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiCourseController {

    @Autowired
    private UserMiddleware userMiddleware;
    
	@Autowired
	CourseFacade courseFacade;


	@PostMapping(value = "/user/get")
	public JsonAnswerStatus listAllVisible(@RequestBody CourseIdDTO courseIdDTO) {
		if(!courseIdDTO.isValid())return new JsonAnswerStatus("error", "no_data");
		return courseFacade.getInfoForUser(userMiddleware.getCurrentUserId(), courseIdDTO.getId());
	}
	
	@GetMapping(value = "/poster/{courseId}")
	public byte[] getPoster(@PathVariable(value="courseId") int courseId) {
		return courseFacade.getPoster(courseId);
	}
	
	@PostMapping(value = "/user/list_all")
	public JsonAnswerStatusCoursePreviewForUserViewModels listAllVisible() {
		return courseFacade.listAllPreviewsForUser(userMiddleware.getCurrentUserId());
	}

	
	@PostMapping(value = "/user/list_all_result_lites")
	public JsonAnswerStatus listAllResultLitesOfUser() {
		return courseFacade.listAllResultLitesViewModel(userMiddleware.getCurrentUserId());
	}
	
	
}
