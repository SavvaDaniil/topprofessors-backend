package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Course;

@SpringBootTest
public class CourseFactoryTest {

	
	public Course createMock(int id) {
		Course course = new Course();
		course.setId(id);
		course.setName("Тестовый курс id" + id);
		return course;
	}
}
