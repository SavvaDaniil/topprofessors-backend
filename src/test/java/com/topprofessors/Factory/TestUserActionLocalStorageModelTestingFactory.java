package com.topprofessors.Factory;

import java.util.HashSet;

import org.springframework.boot.test.context.SpringBootTest;

import com.topprofessors.Model.TestUser.TestUserActionLocalStorageModel;

@SpringBootTest
public class TestUserActionLocalStorageModelTestingFactory {

	
	public TestUserActionLocalStorageModel createMock(int testQuestionId, int numberOfQuestion,
			HashSet<Integer> testQuestionAnswerIdsSet) {
		return new TestUserActionLocalStorageModel(testQuestionId, numberOfQuestion, testQuestionAnswerIdsSet);
	}
}
