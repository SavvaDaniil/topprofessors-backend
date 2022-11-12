package com.topprofessors.Service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Lesson;
import com.topprofessors.Facade.LessonFacade;

import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class LessonServiceTest {

	@Autowired
	LessonFacade lessonFacade;

	@Autowired
	LessonService lessonService;
	
	
	@Test
	public void checkLessonsForHash() {
		ArrayList<Lesson> lessons = lessonService.listAll();
		
		for(Lesson lesson : lessons) {
			if(lesson.getHash() == null) {
				System.out.println("Test lesson no hash: " + lesson.getId());
				lesson = lessonFacade.chechHashForLesson(lesson);
				Assert.notNull(lesson.getHash());
			}
		}
	}
	
}
