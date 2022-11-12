package com.topprofessors.Facade;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Entity.TestQuestion;
import com.topprofessors.Entity.TestQuestionAnswer;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Factory.TestQuestionAnswerTestingFactory;
import com.topprofessors.Factory.TestQuestionTestingFactory;
import com.topprofessors.Factory.TestUserActionLocalStorageModelTestingFactory;
import com.topprofessors.Model.TestUser.TestUserActionLocalStorageModel;

@SpringBootTest
public class TestUserFacadeTesting {

	@Autowired
	TestUserFacade testUserFacade;
	
	@Test
	public void testUpdateResult() {
		TestUser testuser = new TestUser();
		testuser.setId(0);
		
		final float points = 1.6f;
		
		testuser = testUserFacade.updateResult(testuser, points, false);
		
		Assertions.assertNotEquals(null, testuser);
		Assertions.assertEquals(0, testuser.getIsDone());
		Assertions.assertEquals(points, testuser.getPoints());
		
		testuser = testUserFacade.updateResult(testuser, points, true);
		Assertions.assertEquals(1, testuser.getIsDone());
	}
	

	@Test
	public void countResultPointsTest() {
		
		
		TestQuestionTestingFactory testQuestionTestingFactory = new TestQuestionTestingFactory();
		TestQuestionAnswerTestingFactory testQuestionAnswerTestingFactory = new TestQuestionAnswerTestingFactory();
		TestUserActionLocalStorageModelTestingFactory TestUserActionLocalStorageModelTestingFactory = 
				new TestUserActionLocalStorageModelTestingFactory();
		
		int testId = 1;
		
		TestQuestion testQuestion1 = testQuestionTestingFactory.createMock(1, testId);
		TestQuestionAnswer testQuestionAnswer1Correct = testQuestionAnswerTestingFactory.createMockCorrect(1, testId, testQuestion1.getId(), 1, 1.0f);
		TestQuestionAnswer testQuestionAnswer2Wrong = testQuestionAnswerTestingFactory.createMockWrong(2, testId, testQuestion1.getId(), 2);

		TestQuestion testQuestion2 = testQuestionTestingFactory.createMock(2, testId);
		TestQuestionAnswer testQuestionAnswer3Wrong = testQuestionAnswerTestingFactory.createMockWrong(3, testId, testQuestion2.getId(), 3);
		TestQuestionAnswer testQuestionAnswer4Correct = testQuestionAnswerTestingFactory.createMockCorrect(4, testId, testQuestion2.getId(), 4, 1.0f);

		TestQuestion testQuestion3 = testQuestionTestingFactory.createMock(3, testId);
		TestQuestionAnswer testQuestionAnswer5Correct = testQuestionAnswerTestingFactory.createMockCorrect(5, testId, testQuestion3.getId(), 5, 0.5f);
		TestQuestionAnswer testQuestionAnswer6Correct = testQuestionAnswerTestingFactory.createMockCorrect(6, testId, testQuestion3.getId(), 6, 0.5f);
		TestQuestionAnswer testQuestionAnswer7Wrong = testQuestionAnswerTestingFactory.createMockWrong(7, testId, testQuestion3.getId(), 7);

		ArrayList<TestQuestion> listAllTestQuestionsOfTest = new ArrayList<TestQuestion>();
		listAllTestQuestionsOfTest.add(testQuestion1);
		listAllTestQuestionsOfTest.add(testQuestion2);
		listAllTestQuestionsOfTest.add(testQuestion3);
		
		ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest = new ArrayList<TestQuestionAnswer>();
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer1Correct);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer2Wrong);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer3Wrong);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer4Correct);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer5Correct);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer6Correct);
		listAllTestQuestionAnswersOfTest.add(testQuestionAnswer7Wrong);
		
		//общие переменные
		HashSet<Integer> testQuestionAnswerIdsSetForTestQuestion1;
		HashSet<Integer> testQuestionAnswerIdsSetForTestQuestion2;
		HashSet<Integer> testQuestionAnswerIdsSetForTestQuestion3;
		TestUserActionLocalStorageModel testUserActionLocalStorageModelForQuestion1;
		TestUserActionLocalStorageModel testUserActionLocalStorageModelForQuestion2;
		TestUserActionLocalStorageModel testUserActionLocalStorageModelForQuestion3;
		ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels;
		
		//пробуем, что все правильно и должен получиться максимальный результат
		testQuestionAnswerIdsSetForTestQuestion1 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion1.add(1);
		testQuestionAnswerIdsSetForTestQuestion2 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion2.add(4);
		testQuestionAnswerIdsSetForTestQuestion3 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion3.add(5);
		testQuestionAnswerIdsSetForTestQuestion3.add(6);
		
		testUserActionLocalStorageModelForQuestion1 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion1.getId(), 
			1, 
			testQuestionAnswerIdsSetForTestQuestion1
		);
		testUserActionLocalStorageModelForQuestion2 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion2.getId(), 
			2, 
			testQuestionAnswerIdsSetForTestQuestion2
		);
		testUserActionLocalStorageModelForQuestion3 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion3.getId(), 
			3, 
			testQuestionAnswerIdsSetForTestQuestion3
		);
		
		testUserActionLocalStorageModels = new ArrayList<TestUserActionLocalStorageModel>();
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion1);
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion2);
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion3);
		
		Assertions.assertEquals(3.0f, testUserFacade.countResultPoints(
				listAllTestQuestionsOfTest, 
				listAllTestQuestionAnswersOfTest, 
				testUserActionLocalStorageModels)
		);
		
		
		//пробуем частичный ответ
		testQuestionAnswerIdsSetForTestQuestion1 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion1.add(1);
		testQuestionAnswerIdsSetForTestQuestion2 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion2.add(3);
		testQuestionAnswerIdsSetForTestQuestion2.add(4);
		testQuestionAnswerIdsSetForTestQuestion3 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion3.add(5);

		testUserActionLocalStorageModelForQuestion1 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion1.getId(), 
			1, 
			testQuestionAnswerIdsSetForTestQuestion1
		);
		testUserActionLocalStorageModelForQuestion3 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion3.getId(), 
			3, 
			testQuestionAnswerIdsSetForTestQuestion3
		);
		testUserActionLocalStorageModels = new ArrayList<TestUserActionLocalStorageModel>();
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion1);
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion3);
		
		Assertions.assertEquals(1.5f, testUserFacade.countResultPoints(
				listAllTestQuestionsOfTest, 
				listAllTestQuestionAnswersOfTest, 
				testUserActionLocalStorageModels)
		);
		
		//пробуем полностью проваленое прохождение теста
		testQuestionAnswerIdsSetForTestQuestion1 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion1.add(2);
		testQuestionAnswerIdsSetForTestQuestion2 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion2.add(3);
		testQuestionAnswerIdsSetForTestQuestion3 = new HashSet<Integer>();
		testQuestionAnswerIdsSetForTestQuestion3.add(5);
		testQuestionAnswerIdsSetForTestQuestion3.add(6);
		testQuestionAnswerIdsSetForTestQuestion3.add(7);
		
		testUserActionLocalStorageModelForQuestion1 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion1.getId(), 
			1, 
			testQuestionAnswerIdsSetForTestQuestion1
		);
		testUserActionLocalStorageModelForQuestion2 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion2.getId(), 
			2, 
			testQuestionAnswerIdsSetForTestQuestion2
		);
		testUserActionLocalStorageModelForQuestion3 = TestUserActionLocalStorageModelTestingFactory.createMock(
			testQuestion3.getId(), 
			3, 
			testQuestionAnswerIdsSetForTestQuestion3
		);
		
		testUserActionLocalStorageModels = new ArrayList<TestUserActionLocalStorageModel>();
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion1);
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion2);
		testUserActionLocalStorageModels.add(testUserActionLocalStorageModelForQuestion3);
		
		Assertions.assertEquals(0f, testUserFacade.countResultPoints(
				listAllTestQuestionsOfTest, 
				listAllTestQuestionAnswersOfTest, 
				testUserActionLocalStorageModels)
		);
	}
}
