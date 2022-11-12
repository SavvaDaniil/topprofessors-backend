package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.TestUser;

@SpringBootTest
public class TestUserFactoryTesting {

	
	public TestUser createMock(int id, int userId, int testId, boolean isDone) {
		TestUser testUser = new TestUser();
		testUser.setId(id);
		testUser.setUserId(userId);
		testUser.setTestId(testId);
		testUser.setIsDone(isDone ? 1 : 0);
		return testUser;
	}
}
