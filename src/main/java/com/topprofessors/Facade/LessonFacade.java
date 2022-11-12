package com.topprofessors.Facade;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topprofessors.Component.RandomComponent;
import com.topprofessors.Entity.Lesson;
import com.topprofessors.Service.LessonService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Lesson.LessonLiteViewModel;
import com.topprofessors.ViewModel.Lesson.LessonViewModel;

@Component
public class LessonFacade {

	@Autowired
	LessonService lessonService;
	
	public ArrayList<LessonLiteViewModel> listAllLiteByDisciplineId(int disciplineId){
		List<Lesson> lessons = lessonService.listAllByDisciplineId(disciplineId);
		
		ArrayList<LessonLiteViewModel> lessonLiteViewModels = new ArrayList<LessonLiteViewModel>();
		
		if(lessons != null) {
			lessonLiteViewModels = (ArrayList<LessonLiteViewModel>) lessons.stream().map(lesson -> 
				new LessonLiteViewModel(lesson.getId(), lesson.getName(), lesson.getNumberOfLesson())
			).collect(Collectors.toList());
		}
		
		return lessonLiteViewModels;
	}
	
	public JsonAnswerStatus getForUser(int userId, int lessonId, int disciplineId, int courseId) {

		Lesson lesson = lessonService.findById(lessonId);
		if(lesson == null)return new JsonAnswerStatus("error", "lesson_not_found");
		
		lesson = this.chechHashForLesson(lesson);
		if(lesson == null)return new JsonAnswerStatus("error", "fail_check_hash");
		
		//нужна проверка доступа по курсу
		
		//нужна проверка наличия дисциплины в курсе
		
		
		LessonViewModel lessonViewModel = getViewModel(lesson);
		
		return new JsonAnswerStatus("success", null, lessonViewModel);
	}

	public LessonViewModel getViewModel(int lessonId) {
		Lesson lesson = lessonService.findById(lessonId);
		if(lesson == null)return null;
		return getViewModel(lesson);
	}
	
	private LessonViewModel getViewModel(Lesson lesson) {
		if(lesson == null)return null;
		
		return new LessonViewModel(
				lesson.getId(),
				lesson.getName(),
				lesson.getVideoType(),
				lesson.getVideoYoutubeLink(),
				null,
				getSliders(lesson.getId(), lesson.getHash())
		);
	}
	
	public Lesson chechHashForLesson(Lesson lesson) {
		if(lesson == null)return null;
		if(lesson.getHash() == null) {
			lesson.setHash(RandomComponent.RandomString(64));
			if(!lessonService.update(lesson))return null;
		}
		/*
		//check folder exist
        String uploadPath = "uploads/lesson/" + lesson.getId() + "_" + lesson.getHash();
        if(! new File(uploadPath).exists())
        {
            new File(uploadPath).mkdir();
        }
        */
		return lesson;
	}

	public byte[] getSlide(int lessonId, String hashFolder, int numberOfFile) {
		
		String fileName = numberOfFile + ".jpg";
		File filePathDest = new File("uploads/lesson/" + lessonId + "_" + hashFolder + "/slides");
        File destFile = new File(filePathDest.getAbsolutePath(), fileName);
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {
        	try {
	        	Path path = Paths.get(destFile.getAbsolutePath());
	            byte[] arr = Files.readAllBytes(path);
	            return arr;
        	} catch(Exception ex) {
            	System.out.println("getImgDocFileForUser Exception: " + ex.getMessage());
        	}
        } else {
        	System.out.println("File not found");
        }
        return null;
	}
	
	private ArrayList<String> getSliders(int lessonId, String hash){
		ArrayList<String> sliders = new ArrayList<String>();

        String realPathtoUploads = "./uploads/lesson/" + lessonId + "_" + hash;
        if(! new File(realPathtoUploads).exists())return null;

        File filePath;
        String fileSrc;
        for(int numberOfFile = 1; numberOfFile <= 100; numberOfFile++) {
        	filePath =  new File("uploads/lesson/" + lessonId + "_" + hash + "/slides/" + numberOfFile + ".jpg");
    		if(filePath.exists() && !filePath.isDirectory()) {
        		fileSrc = "/api/lesson/slider/get/"+lessonId+"/" + hash + "/" + numberOfFile;
        		sliders.add(fileSrc);
    		} else {
    			break;
    		}
        }
        return sliders;
	}
	
}
