package com.topprofessors.Factory;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.TestQuestionAnswer;

@SpringBootTest
public class TestQuestionAnswerTestingFactory {

	
	public TestQuestionAnswer createMockCorrect(int id, int testId, int testQuestionId, int numberOfAnswer, float points) {
		return createMock(id, testId, testQuestionId, true, numberOfAnswer, points);
	}
	public TestQuestionAnswer createMockWrong(int id, int testId, int testQuestionId, int numberOfAnswer) {
		return createMock(id, testId, testQuestionId, false, numberOfAnswer, 0);
	}
	
	private TestQuestionAnswer createMock(int id, int testId, int testQuestionId, boolean isCorrect, int numberOfAnswer, float points) {
		TestQuestionAnswer testQuestionAnswer = new TestQuestionAnswer();
		testQuestionAnswer.setId(id);
		testQuestionAnswer.setTestId(testId);
		testQuestionAnswer.setTestQuestionId(testQuestionId);
		testQuestionAnswer.setIsCorrect(isCorrect ? 1 : 0);
		testQuestionAnswer.setNumberOfAnswer(numberOfAnswer);
		testQuestionAnswer.setPoints(isCorrect ? points : 0);
		
		return testQuestionAnswer;
	}
}
