package com.topprofessors.Observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.User;
import com.topprofessors.Factory.CourseFactoryTest;
import com.topprofessors.Factory.UserFactoryTest;

@SpringBootTest
public class UserObserverTest {

	@Value("${userTest.username}")
	String usernameTest;

	@Value("${smtp.username}")
	String smtpUsername;
	
	@Value("${smtp.password}")
	String smtpPassword;
	
	@Autowired
	UserObserver userObserver;
	
	@Test
	public void sendMailToUser() {
		System.out.println("UserObserverTest sendMailToUser usernameTest: " + usernameTest);
		Assertions.assertNotEquals(null, usernameTest);
		
		System.out.println("UserObserverTest sendMailToUser smtpUsername: " + smtpUsername);
		Assertions.assertNotEquals(null, smtpUsername);
		
		System.out.println("UserObserverTest sendMailToUser adminMailPassword: " + smtpPassword);
		Assertions.assertNotEquals(null, smtpPassword);
		
		
		UserFactoryTest userFactoryTest = new UserFactoryTest();
		CourseFactoryTest courseFactoryTest = new CourseFactoryTest();
		
		User userMock = userFactoryTest.createMockWithUsername(1, "savva.d@mail.ru");

		Course courseMock = courseFactoryTest.createMock(1);
		
		String textMessage = "<html><head><meta charset=\"utf-8\"></head>"
				+ "<body><center><p>Тестовое письмо!</p></center>\r\n"
				+ "<br />\r\n"
				+ "<p>\r\n"
				+ "Тут текст тестовго письма\r\n"
				+ "	<br /><br />\r\n"
				+ "</p>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		
		
		//userObserver.sendMailToUserThatCourseIsDone(userMock, courseMock);
		
		//Assertions.assertEquals(true, userObserver.sendMailToUser(userMock, "Тестовый заголовк письма", textMessage));
	}
	
}
