package com.topprofessors.Facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.Test;
import com.topprofessors.Entity.TestQuestion;
import com.topprofessors.Entity.TestQuestionAnswer;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.TestUserAction;
import com.topprofessors.Entity.User;
import com.topprofessors.Model.TestUser.TestUserActionLocalStorageModel;
import com.topprofessors.Model.TestUser.TestUserLocalStorageModel;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.TestQuestionAnswerService;
import com.topprofessors.Service.TestQuestionService;
import com.topprofessors.Service.TestService;
import com.topprofessors.Service.TestUserActionService;
import com.topprofessors.Service.TestUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Test.TestIndexViewModel;
import com.topprofessors.ViewModel.Test.TestLiteViewModel;
import com.topprofessors.ViewModel.Test.TestStartViewModel;
import com.topprofessors.ViewModel.TestQuestion.TestQuestionLiteViewModel;
import com.topprofessors.ViewModel.TestQuestionAnswer.TestQuestionAnswerLiteViewModel;
import com.topprofessors.ViewModel.TestUserAction.TestUserActionLiteViewModel;
import com.topprofessors.ViewModel.TestUserAction.TestUserActionLocalStorageViewModel;

@Component
public class TestFacade {

	@Autowired
	TestService testService;

	@Lazy
	@Autowired
	TestUserService testUserService;

	@Lazy
	@Autowired
	TestUserActionService testUserActionService;
	
	@Lazy
	@Autowired
	TestUserFacade testUserFacade;
	
	@Lazy
	@Autowired
	TestQuestionService testQuestionService;
	
	@Lazy
	@Autowired
	TestQuestionAnswerService testQuestionAnswerService;
	

	@Lazy
	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	DisciplineService disciplineService;
	
	//private final HashSet<String> availableActions = new HashSet<String>(Arrays.asList("start", ""));
	
	public JsonAnswerStatus action(int userId, int testId, String actionValue, int questionId, int questionAnswerId) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		return null;
	}
	
	public JsonAnswerStatus userAnswerUpdate(int userId, int testId, int questionId, int questionAnswerId) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");

		TestQuestion testQuestion = testQuestionService.findById(questionId);
		if(testQuestion == null)return new JsonAnswerStatus("error", "question_not_found");

		TestQuestionAnswer testQuestionAnswer = testQuestionAnswerService.findById(questionAnswerId);
		if(testQuestionAnswer == null)return new JsonAnswerStatus("error", "answer_not_found");
		
		
		
		return null;
	}
	
	public JsonAnswerStatus start(int userId, int testId, boolean isContinue) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");

		Date dateOfAdd = new Date();
		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser != null) {
			testUser.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
			testUser.setIsFinished(1);
			if(!testUserService.update(testUser))return new JsonAnswerStatus("error", "try_save_testUser");
		} else if(testUser == null) {
			testUser = new TestUser();
			testUser.setTestId(testId);
			testUser.setUserId(userId);
			testUser.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
			testUser.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
			testUser.setIsFinished(1);
			testUser = testUserService.add(testUser);
			if(testUser == null)return new JsonAnswerStatus("error", "try_add_testUser");
		}
		
		//теперь все действия по ответам сохраняются в файле, а не в базе данных
		//if(!testUserActionService.deleteAllByTestId(userId, testId))return new JsonAnswerStatus("error", "try_clear_testUserActions");

		ArrayList<TestQuestion> testQuestions = testQuestionService.listAllQuestionByTestId(testId);
		if(testQuestions == null)return new JsonAnswerStatus("error", "testQuestions_not_found");

		//все возможные ответы к тесту
		ArrayList<TestQuestionAnswer> testQuestionAnswersOfTest = (ArrayList<TestQuestionAnswer>)testQuestionAnswerService.listAllQuestionByTestId(testId);
		testQuestionAnswersOfTest.stream()
				.sorted(Comparator.comparingInt(answer -> answer.getNumberOfAnswer()));
		
		TestUserLocalStorageModel testUserLocalStorageModel = null;
		
		if(isContinue) {
			testUserLocalStorageModel = testUserService.deserializeFromFile(testUser.getId());
		} else {
			
			//генерируем arrayList с вопросами в рэндомном порядке
			Collections.shuffle(testQuestions);
			ArrayList<TestQuestion> testQuestionsInRandomSort = testQuestions;
			if(test.getMaxSeenQuestions() > testQuestionsInRandomSort.size())return new JsonAnswerStatus("error", "not_enough_questions");
			
			ArrayList<TestQuestion> testQuestionsInRandomSortByMaxSeenQuestions = (ArrayList<TestQuestion>)testQuestionsInRandomSort.stream()
					.limit(test.getMaxSeenQuestions())
					.collect(Collectors.toList());
			
			
			
			//генерируем test_user_action на которые пользователь будет отвечать
			ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels = new ArrayList<TestUserActionLocalStorageModel>();
			int newNumberOfQuestion = 0;
			for(TestQuestion testQuestionFromRandomSort : testQuestionsInRandomSortByMaxSeenQuestions) {
				newNumberOfQuestion++;
				//System.out.println("testQuestionInRandomSort number: " + testQuestionFromRandomSort.getNumberOfQuestion());
				testUserActionLocalStorageModels.add(new TestUserActionLocalStorageModel(
						testQuestionFromRandomSort.getId(),
						newNumberOfQuestion
					)
				);
			}
			
			//сохраняем в сериализованный файл
			testUserLocalStorageModel = 
					new TestUserLocalStorageModel(testUser.getId(), userId, testId, testUserActionLocalStorageModels);
			if(!testUserService.serializeToFile(testUserLocalStorageModel))return new JsonAnswerStatus("error", "try_generate_file_test_user");
			
			
		}

		//ArrayList<TestUserActionLiteViewModel> testUserActionLiteViewModels;
		//ArrayList<TestUserActionLocalStorageViewModel> testUserActionLocalStorageViewModels;
		
		ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels = 
				testUserFacade.listTestQuestionLiteViewModelsByTestUserLocalStorageForUser(
					userId, 
					testId, 
					testQuestions, 
					testQuestionAnswersOfTest, 
					testUserLocalStorageModel
				);
		
		
		return new JsonAnswerStatus("success", null, new TestStartViewModel(testId, testQuestionLiteViewModels));
	}
	
	
	...
	
	
	
	public TestLiteViewModel getLite(Test test) {
		if(test == null)return null;
		return new TestLiteViewModel(
			test.getId(),
			test.getName(),
			test.getStatus(),
			test.getQuestionsByOrder0Random1(),
			test.getMaxSeenQuestions(),
			test.getTypeOfTest(),
			test.getNeededPoints(),
			test.getDescription(),
			test.getDescriptionForClosedStatus()
		);
	}
}
