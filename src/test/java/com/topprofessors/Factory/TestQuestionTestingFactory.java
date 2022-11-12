package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.TestQuestion;

@SpringBootTest
public class TestQuestionTestingFactory {

	public TestQuestion createMock(int id, int testId) {
		TestQuestion testQuestion = new TestQuestion();
		testQuestion.setId(id);
		testQuestion.setTestId(testId);
		testQuestion.setTextContent("Тестовый вопрос id" + id);
		
		return testQuestion;
	}
}
