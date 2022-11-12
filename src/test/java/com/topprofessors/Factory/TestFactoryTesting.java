package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Test;

@SpringBootTest
public class TestFactoryTesting {

	
	public Test createMock(int id) {
		Test test = new Test();
		test.setId(id);
		test.setName("Тестовый тест " + id);
		return test;
	}
	
}
