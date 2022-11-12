package com.topprofessors.Facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Test;
import com.topprofessors.Entity.TestQuestion;
import com.topprofessors.Entity.TestQuestionAnswer;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Model.TestUser.TestUserActionLocalStorageModel;
import com.topprofessors.Model.TestUser.TestUserLocalStorageModel;
import com.topprofessors.Service.TestQuestionAnswerService;
import com.topprofessors.Service.TestQuestionService;
import com.topprofessors.Service.TestService;
import com.topprofessors.Service.TestUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.TestQuestion.TestQuestionLiteViewModel;
import com.topprofessors.ViewModel.TestQuestionAnswer.TestQuestionAnswerLiteViewModel;
import com.topprofessors.ViewModel.TestUser.TestUserLiteViewModel;
import com.topprofessors.ViewModel.TestUser.TestUserResultOfUserViewModel;
import com.topprofessors.ViewModel.TestUserAction.TestUserActionQuetionResultOfUserViewModel;

@Component
public class TestUserFacade {

	@Autowired
	TestUserService testUserService;

	@Lazy
	@Autowired
	UserFacade userFacade;
	
	@Lazy
	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	TestFacade testFacade;
	
	@Lazy
	@Autowired
	TestService testService;

	@Lazy
	@Autowired
	TestQuestionService testQuestionService;
	
	@Lazy
	@Autowired
	TestQuestionAnswerService testQuestionAnswerService;
	

	public TestUserLiteViewModel getLiteByTestIdAndUserId(int testId, int userId) {
		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser == null)return null;
		
		return getLiteViewModel(testUser);
	}

	public TestUserLiteViewModel getLiteViewModel(TestUser testUser) {
		if(testUser == null)return null;
		//if(testUser.getTest() == null)return null;
		//if(testUser.getUser() == null)return null;
		
		return new TestUserLiteViewModel(
				testUser.getTestId(), 
				testUser.getUserId(), 
				testUser.getIsFinished(),
				testUser.getIsDone(), 
				(testUser.getIsFinished() == 2 ? testUser.getPoints() : 0),
				testUser.getDateOfAdd(), 
				testUser.getDateOfUpdate()
			);
	}

	
	public JsonAnswerStatus userUpdate(int userId, int testId, int testQuestionId, HashSet<Integer> testQuestionAnswersSet) {
		
		//if(testQuestionAnswersSet == null) {
			//return new JsonAnswerStatus("success");
		//}
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");

		TestQuestion testQuestion = testQuestionService.findById(testQuestionId);
		if(testQuestion == null)return new JsonAnswerStatus("error", "question_not_found");

		//TestQuestionAnswer testQuestionAnswer = testQuestionAnswerService.findById(questionAnswerId);
		//if(testQuestionAnswer == null)return new JsonAnswerStatus("error", "answer_not_found");
		
		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser == null)return new JsonAnswerStatus("error", "test_user_not_found");
		
		if(testQuestionAnswersSet != null) {
			//проверяем сет из отвеченных вопросов
			ArrayList<TestQuestionAnswer> testQuestionAnswers = testQuestionAnswerService.listAllQuestionByTestId(testId);
			boolean isTestQuestionAnswerFindInDb = false;
			for(int testQuestionAnswerIdFromUser : testQuestionAnswersSet) {
				isTestQuestionAnswerFindInDb = false;
				for(TestQuestionAnswer testQuestionAnswer : testQuestionAnswers) {
					if(testQuestionAnswer.getId() == testQuestionAnswerIdFromUser) {
						//System.out.println("testQuestionAnswer.getId() " + testQuestionAnswer.getId() + " найден в базе ");
						isTestQuestionAnswerFindInDb = true;
						break;
					}
				}
				if(!isTestQuestionAnswerFindInDb)return new JsonAnswerStatus("error", "test_question_answer_not_found");
			}
		}
		
		//получаем объект из бинарного файла со списком вопросов, которые были сгенерированны
		TestUserLocalStorageModel testUserLocalStorageModel = testUserService.deserializeFromFile(testUser.getId());
		if(testUserLocalStorageModel == null)return new JsonAnswerStatus("error", "file_not_found");
		
		
		for(int i = 0; i < testUserLocalStorageModel.getTestUserActionLocalStorageModels().size(); i++) {
			if(testUserLocalStorageModel.getTestUserActionLocalStorageModels().get(i).getTestQuestionId() != testQuestionId)continue;
			
			testUserLocalStorageModel.getTestUserActionLocalStorageModels().get(i).setTestQuestionAnswerIdsSet(testQuestionAnswersSet);
			System.out.println("Загрузка множества ответов пользователя в файл");
			break;
		}
		
		if(!testUserService.serializeToFile(testUserLocalStorageModel))return new JsonAnswerStatus("error", "try_save_file");
		

		TestUserLocalStorageModel testUserLocalStorageModelTest = testUserService.deserializeFromFile(testUser.getId());
		testUserLocalStorageModelTest.getTestUserActionLocalStorageModels()
		.stream()
		.forEach((testUserQuestionModel) -> {
			if(testUserQuestionModel.getTestQuestionAnswerIdsSet() != null) {
				testUserQuestionModel.getTestQuestionAnswerIdsSet()
					.stream()
					.forEach(testQuestionAnswerId -> {
						System.out.println("Найден ответ пользователя testQuestionAnswerId: " + testQuestionAnswerId);
					});
			}
		});
		
		return new JsonAnswerStatus("success");
	}
	
	
	public JsonAnswerStatus userGetQuestion(int userId, int testId, int numberOfQuestion) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");
		
		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser == null)return new JsonAnswerStatus("error", "test_user_not_found");
		if(testUser.getUserId() != userId)return new JsonAnswerStatus("error", "wrong_user");

		ArrayList<TestQuestion> listAllTestQuestionsOfTest = testQuestionService.listAllQuestionByTestId(testId);
		if(listAllTestQuestionsOfTest == null)return new JsonAnswerStatus("error", "testQuestions_not_found");
		
		ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest = testQuestionAnswerService.listAllQuestionByTestId(testId);
		listAllTestQuestionAnswersOfTest.stream()
				.sorted(Comparator.comparingInt(answer -> answer.getNumberOfAnswer()));

		TestUserLocalStorageModel testUserLocalStorageModelTest = testUserService.deserializeFromFile(testUser.getId());
		if(testUserLocalStorageModelTest == null)return new JsonAnswerStatus("error", "file_not_found");
		
		ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels = this.listTestQuestionLiteViewModelsByTestUserLocalStorageForUser(
			userId, 
			testId, 
			listAllTestQuestionsOfTest, 
			listAllTestQuestionAnswersOfTest, 
			testUserLocalStorageModelTest
		);
		if(testQuestionLiteViewModels == null)return new JsonAnswerStatus("error", "no_questions");
		if(testQuestionLiteViewModels.size() < numberOfQuestion)return new JsonAnswerStatus("error", "out_of_number_of_questions");
		
		TestQuestionLiteViewModel testQuestionLiteViewModel = testQuestionLiteViewModels.get(numberOfQuestion - 1);
		return new JsonAnswerStatus("success", null, testQuestionLiteViewModel);
	}
	
	
	
	

	public ArrayList<TestQuestionLiteViewModel> listTestQuestionLiteViewModelsByTestUserLocalStorageForUser(
			int userId,
			int testId,
			ArrayList<TestQuestion> listAllTestQuestionsOfTest,
			ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest,
			TestUserLocalStorageModel testUserLocalStorageModel){

		if(listAllTestQuestionsOfTest == null || listAllTestQuestionAnswersOfTest == null || testUserLocalStorageModel == null)return null;
		ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels = testUserLocalStorageModel.getTestUserActionLocalStorageModels();
		if(testUserActionLocalStorageModels == null)return null;

		Optional<TestQuestion> testQuestionByIdInLocalStorageOptional;
		TestQuestion testQuestionFromDbByIdInLocalStorage;
		ArrayList<TestQuestionLiteViewModel> testQuestionLiteViewModels = new ArrayList<TestQuestionLiteViewModel>();
		ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels;
		
		for(TestUserActionLocalStorageModel testUserActionLocalStorageModel : testUserActionLocalStorageModels) {
			testQuestionByIdInLocalStorageOptional = listAllTestQuestionsOfTest
					.stream()
					.filter(testQuestion -> testQuestion.getId() == testUserActionLocalStorageModel.getTestQuestionId())
					.findFirst();
			if(testQuestionByIdInLocalStorageOptional == null)continue;
			testQuestionFromDbByIdInLocalStorage = testQuestionByIdInLocalStorageOptional.get();
			
			//заполняем возможные ответы для вопроса теста
			testQuestionAnswerLiteViewModels = new ArrayList<TestQuestionAnswerLiteViewModel>();
			for(TestQuestionAnswer testQuestionAnswer : listAllTestQuestionAnswersOfTest) {
				if(testQuestionAnswer.getTestQuestionId() != testQuestionFromDbByIdInLocalStorage.getId())continue;
				
				testQuestionAnswerLiteViewModels.add(
					new TestQuestionAnswerLiteViewModel(
						testQuestionAnswer.getId(),
						testQuestionAnswer.getNumberOfAnswer(),
						testQuestionAnswer.getTypeOfAnswer(),
						testQuestionAnswer.getTextContent()
					)
				);
			}

			testQuestionLiteViewModels.add(
				new TestQuestionLiteViewModel(
						testQuestionFromDbByIdInLocalStorage.getId(),
					userId,
					testId,
					testUserActionLocalStorageModel.getNumberOfQuestion(),
					testQuestionFromDbByIdInLocalStorage.getTextContent(),
					testQuestionAnswerLiteViewModels,
					testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet()
				)
			);
		}
		
		return testQuestionLiteViewModels;
	}
	
	
	

	public JsonAnswerStatus userInitResult(int userId, int testId) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");
		
		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser == null)return new JsonAnswerStatus("error", "test_user_not_found");

		
		//нужно будет вынести в отдельную функцию
		ArrayList<TestQuestion> listAllTestQuestionsOfTest = testQuestionService.listAllQuestionByTestId(testId);
		ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest = testQuestionAnswerService.listAllQuestionByTestId(testId);
		if(listAllTestQuestionAnswersOfTest == null) {
			
		}
		
		listAllTestQuestionAnswersOfTest.stream()
				.sorted(Comparator.comparingInt(answer -> answer.getNumberOfAnswer()));
		TestUserLocalStorageModel testUserLocalStorageModelTest = testUserService.deserializeFromFile(testUser.getId());
		if(testUserLocalStorageModelTest == null)return new JsonAnswerStatus("error", "file_not_found");

		float pointsResult = this.countResultPoints(
			listAllTestQuestionsOfTest,
			listAllTestQuestionAnswersOfTest, 
			testUserLocalStorageModelTest.getTestUserActionLocalStorageModels()
		);
		
		if(test.getNeededPoints() <= pointsResult) {
			testUser = updateResult(testUser, pointsResult, true);
			if(!testUserService.update(testUser))return new JsonAnswerStatus("error", "try_update_test_user");
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//здесь нужен процесс проверки на сдачу остальных тестов курсов пользователя!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			userFacade.checkCoursesForDone(user);
			
		} else {
			testUser = updateResult(testUser, pointsResult, false);
			if(!testUserService.update(testUser))return new JsonAnswerStatus("error", "try_update_test_user");
		}
		
		return new JsonAnswerStatus("success");
	}
	
	
	public float countResultPoints(
			ArrayList<TestQuestion> listAllTestQuestionsOfTest,
			ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest, 
			ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels) {
		
		float pointsResult = 0f;
		float pointsForCorrectAnswerToQuestion = 0f;
		ArrayList<TestQuestionAnswer> listOfAllPossibleAnswersForQuestion;
		
		for(TestQuestion testQuestion : listAllTestQuestionsOfTest) {
			listOfAllPossibleAnswersForQuestion = (ArrayList<TestQuestionAnswer>)listAllTestQuestionAnswersOfTest.stream()
					.filter(testQuestionAnswer -> testQuestionAnswer.getTestQuestionId() == testQuestion.getId())
					.collect(Collectors.toList());
			if(listOfAllPossibleAnswersForQuestion.size() == 0)continue;
			System.out.println("Проверяем вопрос " + testQuestion.getId());

			pointsForCorrectAnswerToQuestion = 0;
			for(TestQuestionAnswer testQuestionAnswer : listOfAllPossibleAnswersForQuestion) {
				System.out.println("Проверяем ответ " + testQuestionAnswer.getId());
				
				for(TestUserActionLocalStorageModel testUserActionLocalStorageModel : testUserActionLocalStorageModels) {
					if(testUserActionLocalStorageModel.getTestQuestionId() != testQuestion.getId())continue;
					
					if(testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet().contains(testQuestionAnswer.getId())) {
						if(testQuestionAnswer.getIsCorrect() == 1) {
							System.out.println("Найден правильный ответ с id ответа " + testQuestionAnswer.getId());
							pointsForCorrectAnswerToQuestion += testQuestionAnswer.getPoints();
						} else {
							System.out.println("Обнуление результата за вопрос, неправильный ответ с id ответа " + testQuestionAnswer.getId());
							pointsForCorrectAnswerToQuestion = 0;
							break;
						}
					}
				}
			}
			System.out.println("За вопрос  " + testQuestion.getId() + " начислено " + pointsForCorrectAnswerToQuestion + " баллов");
			pointsResult += pointsForCorrectAnswerToQuestion;
		}
		
		
		/*
		for(TestUserActionLocalStorageModel testUserActionLocalStorageModel : testUserActionLocalStorageModels) {

			if(testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet() == null)continue;
			if(testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet().size() == 0)continue;
			
			listOfAllTestQuestionAnswersForTest = new ArrayList<TestQuestionAnswer>();
			listOfAllTestQuestionAnswersForTest = (ArrayList<TestQuestionAnswer>)listAllTestQuestionAnswersOfTest
					.stream()
					.filter(testQuestionAnswer -> testQuestionAnswer.getTestQuestionId() == testUserActionLocalStorageModel.getTestQuestionId())
					.collect(Collectors.toList());
			if(listOfAllTestQuestionAnswersForTest.size() == 0)continue;
			
			for(TestQuestionAnswer testQuestionAnswer : listOfAllTestQuestionAnswersForTest) {
				if(testQuestionAnswer.getPoints() == 0)continue;
				
				if(testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet().contains(testQuestionAnswer.getId())) {
					if(testQuestionAnswer.getIsCorrect() == 1) {
						pointsResult += testQuestionAnswer.getPoints();
					} else {
						break;
					}
				}
			}
		}
		*/
		
		System.out.println("Полученный баллы за тест: " + pointsResult + "\n-----------------------------------");
		return pointsResult;
	}
	
	public TestUser updateResult(TestUser testUser, float pointsResult, boolean isDone) {
		Date dateNow = new Date();
		testUser.setIsFinished(2);
		testUser.setIsDone(isDone ? 1 : 0);
		testUser.setPoints(pointsResult);
		testUser.setDateOfUpdate(new Timestamp(dateNow.getTime()));
		
		return testUser;
	}
	
	

	public JsonAnswerStatus getResultOfUser(int userId, int testId) {
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		TestUser testUser = testUserService.findByTestIdAndUserId(testId, userId);
		if(testUser == null)return new JsonAnswerStatus("error", "test_user_not_found");

		Test test = testService.findById(testId);
		if(test == null)return new JsonAnswerStatus("error", "test_not_found");
		
		return new JsonAnswerStatus(
			"success", 
			null, 
			testFacade.getLite(test),
			this.getResultOfUser(testUser)
		);
	}
	
	public TestUserResultOfUserViewModel getResultOfUser(TestUser testUser) {
		if(testUser == null)return null;

		ArrayList<TestQuestion> listAllTestQuestionsOfTest = 
				testQuestionService.listAllQuestionByTestId(testUser.getTestId());
		
		ArrayList<TestQuestion> listAllTestQuestionsOfTestForUser = new ArrayList<TestQuestion>();
		
		
		ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest = 
				testQuestionAnswerService.listAllQuestionByTestId(testUser.getTestId());
		TestUserLocalStorageModel testUserLocalStorageModelTest = testUserService.deserializeFromFile(testUser.getId());
		
		return new TestUserResultOfUserViewModel(
			this.getLiteViewModel(testUser),
			this.listResultsOfUserFromLocalStorage(
				testUser, 
				listAllTestQuestionsOfTest, 
				listAllTestQuestionAnswersOfTest, 
				(testUserLocalStorageModelTest != null ? testUserLocalStorageModelTest.getTestUserActionLocalStorageModels() : null)
			)
		);
	}

	public ArrayList<TestUserActionQuetionResultOfUserViewModel> listResultsOfUserFromLocalStorage(
			TestUser testUser,
			ArrayList<TestQuestion> listAllTestQuestionsOfTest,
			ArrayList<TestQuestionAnswer> listAllTestQuestionAnswersOfTest, 
			ArrayList<TestUserActionLocalStorageModel> testUserActionLocalStorageModels) {

		if(testUser == null 
				|| listAllTestQuestionsOfTest == null 
				|| listAllTestQuestionAnswersOfTest == null
				|| testUserActionLocalStorageModels == null)return null;
		
		ArrayList<TestUserActionQuetionResultOfUserViewModel> testUserActionQuetionResultOfUserViewModels
		 = new  ArrayList<TestUserActionQuetionResultOfUserViewModel>();
		TestUserActionQuetionResultOfUserViewModel testUserActionQuetionResultOfUserViewModel;
		float pointsForCorrectAnswerToQuestion = 0f;
		boolean isCorrectAnswer = false;
		boolean isPartlyCorrectAnswer = false;
		ArrayList<TestQuestionAnswerLiteViewModel> testQuestionAnswerLiteViewModels;
		TestQuestionAnswerLiteViewModel testQuestionAnswerLiteViewModel;
		HashSet<Integer> testQuestionAnswerIdsFilledByUserSet;

		ArrayList<TestQuestionAnswer> listOfAllPossibleAnswersForQuestion;
		boolean isQuestionWasInTestForUser = false;
		int numberOfQuestionInTest = 0;
		for(TestQuestion testQuestion : listAllTestQuestionsOfTest) {
			//нужна проверка, а был ли этот вопрос у пользователя
			isQuestionWasInTestForUser = false;
			numberOfQuestionInTest = 0;
			for(TestUserActionLocalStorageModel testUserActionLocalStorageModel : testUserActionLocalStorageModels) {
				if(testUserActionLocalStorageModel.getTestQuestionId() == testQuestion.getId()) {
					isQuestionWasInTestForUser = true;
					numberOfQuestionInTest = testUserActionLocalStorageModel.getNumberOfQuestion();
					break;
				}
			}
			if(!isQuestionWasInTestForUser)continue;
			
			listOfAllPossibleAnswersForQuestion = (ArrayList<TestQuestionAnswer>)listAllTestQuestionAnswersOfTest.stream()
					.filter(testQuestionAnswer -> testQuestionAnswer.getTestQuestionId() == testQuestion.getId())
					.collect(Collectors.toList());
			if(listOfAllPossibleAnswersForQuestion.size() == 0)continue;
			System.out.println("Проверяем вопрос " + testQuestion.getId());

			
			pointsForCorrectAnswerToQuestion = 0;
			isCorrectAnswer = false;
			isPartlyCorrectAnswer = false;
			testQuestionAnswerLiteViewModels = new ArrayList<TestQuestionAnswerLiteViewModel>();
			testQuestionAnswerIdsFilledByUserSet = new HashSet<Integer>();
			
			for(TestQuestionAnswer testQuestionAnswer : listOfAllPossibleAnswersForQuestion) {
				System.out.println("Проверяем ответ " + testQuestionAnswer.getId());
				
				for(TestUserActionLocalStorageModel testUserActionLocalStorageModel : testUserActionLocalStorageModels) {
					if(testUserActionLocalStorageModel.getTestQuestionId() != testQuestion.getId())continue;
					
					if(testUserActionLocalStorageModel.getTestQuestionAnswerIdsSet().contains(testQuestionAnswer.getId())) {
						testQuestionAnswerIdsFilledByUserSet.add(testQuestionAnswer.getId());
						if(testQuestionAnswer.getIsCorrect() == 1) {
							System.out.println("Найден правильный ответ с id ответа " + testQuestionAnswer.getId());
							pointsForCorrectAnswerToQuestion += testQuestionAnswer.getPoints();
							if(!isCorrectAnswer)isCorrectAnswer = true;
							if(!isPartlyCorrectAnswer)isPartlyCorrectAnswer = true;
						} else {
							System.out.println("Обнуление результата за вопрос, неправильный ответ с id ответа " + testQuestionAnswer.getId());
							pointsForCorrectAnswerToQuestion = 0;
							isCorrectAnswer = false;
							break;
						}
					}
				}
				
				testQuestionAnswerLiteViewModel = new TestQuestionAnswerLiteViewModel(
						testQuestionAnswer.getId(),
						testQuestionAnswer.getNumberOfAnswer(),
						testQuestionAnswer.getTypeOfAnswer(),
						testQuestionAnswer.getTextContent()
				);
				testQuestionAnswerLiteViewModels.add(testQuestionAnswerLiteViewModel);
			}
			System.out.println("За вопрос  " + testQuestion.getId() + " было начислено " + pointsForCorrectAnswerToQuestion + " баллов");
			//pointsResult += pointsForCorrectAnswerToQuestion;
			
			testUserActionQuetionResultOfUserViewModel = new TestUserActionQuetionResultOfUserViewModel(
					testQuestion.getId(),
					testUser.getUserId(),
					testUser.getTestId(),
					//testQuestion.getNumberOfQuestion(),
					numberOfQuestionInTest,
					testQuestion.getTextContent(),
					testQuestionAnswerLiteViewModels,
					testQuestionAnswerIdsFilledByUserSet,
					isCorrectAnswer,
					isPartlyCorrectAnswer,
					pointsForCorrectAnswerToQuestion
			);
			testUserActionQuetionResultOfUserViewModels.add(testUserActionQuetionResultOfUserViewModel);
		}
		
		
		return testUserActionQuetionResultOfUserViewModels;
	}
}
