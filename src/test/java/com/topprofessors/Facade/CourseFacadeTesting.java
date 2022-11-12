package com.topprofessors.Facade;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Factory.CourseFactoryTest;
import com.topprofessors.Factory.DisciplineFactoryTest;
import com.topprofessors.Factory.TestFactoryTesting;
import com.topprofessors.Factory.TestUserFactoryTesting;
import com.topprofessors.Factory.UserFactoryTest;

@SpringBootTest
public class CourseFacadeTesting {

	@Autowired
	CourseFacade courseFacade;
	
	@Test
	public void checkCourseForDoneTestSuccess() {
		
		UserFactoryTest userFactoryTest = new UserFactoryTest();
		CourseFactoryTest courseFactoryTest = new CourseFactoryTest();
		DisciplineFactoryTest disciplineFactoryTest = new DisciplineFactoryTest();
		TestFactoryTesting testFacatoryTesting = new TestFactoryTesting();
		TestUserFactoryTesting testUserFactoryTesting = new TestUserFactoryTesting();
		
		User userMock = userFactoryTest.createMockWithoutUsername(1);
		
		Course courseMock = courseFactoryTest.createMock(1);
		
		com.topprofessors.Entity.Test testMock = testFacatoryTesting.createMock(1);
		
		ArrayList<Discipline> disciplineOfCourseMocks = new ArrayList<Discipline>();
		disciplineOfCourseMocks.add(disciplineFactoryTest.createMockWithoutTest(1));
		disciplineOfCourseMocks.add(disciplineFactoryTest.createMockWitTest(2, testMock.getId()));
		
		ArrayList<TestUser> testUserMocks = new ArrayList<TestUser>();
		testUserMocks.add(testUserFactoryTesting.createMock(1, userMock.getId(), testMock.getId(), true));
		
		Runnable taskCheckCourseDone = new Runnable() {
			public void run() {
				CourseFacade courseFacade = new CourseFacade();
				Assertions.assertEquals(true, courseFacade.checkCourseForDone(userMock, courseMock, disciplineOfCourseMocks, testUserMocks));
			}
		};
		
		Thread threadCheckCourseDone = new Thread(taskCheckCourseDone);
		threadCheckCourseDone.start();
	}
	
	@Test
	public void checkCourseForDoneTestFail() {
		
		UserFactoryTest userFactoryTest = new UserFactoryTest();
		CourseFactoryTest courseFactoryTest = new CourseFactoryTest();
		DisciplineFactoryTest disciplineFactoryTest = new DisciplineFactoryTest();
		TestFactoryTesting testFacatoryTesting = new TestFactoryTesting();
		TestUserFactoryTesting testUserFactoryTesting = new TestUserFactoryTesting();
		
		User userMock = userFactoryTest.createMockWithoutUsername(1);
		
		Course courseMock = courseFactoryTest.createMock(1);
		
		com.topprofessors.Entity.Test testMock = testFacatoryTesting.createMock(1);
		
		ArrayList<Discipline> disciplineOfCourseMocks = new ArrayList<Discipline>();
		disciplineOfCourseMocks.add(disciplineFactoryTest.createMockWithoutTest(1));
		disciplineOfCourseMocks.add(disciplineFactoryTest.createMockWitTest(2, testMock.getId()));
		
		ArrayList<TestUser> testUserMocks = new ArrayList<TestUser>();
		testUserMocks.add(testUserFactoryTesting.createMock(1, userMock.getId(), testMock.getId(), false));
		
		Assertions.assertEquals(false, courseFacade.checkCourseForDone(userMock, courseMock, disciplineOfCourseMocks, testUserMocks));
	}
	
}
