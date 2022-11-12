package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.User;

@SpringBootTest
public class UserFactoryTest {

	public User createMockWithoutUsername(int id) {
		return createMock(id, null);
	}
	public User createMockWithUsername(int id, String username) {
		return createMock(id, username);
	}
	
	private User createMock(int id, String username) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		return user;
	}
}
