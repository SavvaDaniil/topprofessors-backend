package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.Discipline;

@SpringBootTest
public class DisciplineFactoryTest {

	public Discipline createMockWithoutTest(int id) {
		return createMock(id, 1, 0);
	}
	
	public Discipline createMockWitTest(int id, int testId) {
		return createMock(id, 0, testId);
	}
	
	private Discipline createMock(int id, int isWithoutTest, int testId) {
		Discipline discipline = new Discipline();
		discipline.setId(id);
		discipline.setIsWithoutTest(isWithoutTest);
		discipline.setTestId(testId);
		return discipline;
	}
}
