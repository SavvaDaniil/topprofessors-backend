package com.topprofessors.Facade;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineResultOfUserLiteViewModel;
import com.topprofessors.Entity.ConnectionCourseToUser;
import com.topprofessors.Entity.ConnectionDisciplineToCourse;
import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.HomeworkUser;
import com.topprofessors.Entity.Test;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Model.Course.CourseStatusTypeForUser;
import com.topprofessors.Service.ConnectionCourseToUserService;
import com.topprofessors.Service.ConnectionDisciplineToCourseService;
import com.topprofessors.Service.CourseService;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.HomeworkUserService;
import com.topprofessors.Service.TestService;
import com.topprofessors.Service.TestUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Course.CourseInfoViewModel;
import com.topprofessors.ViewModel.Course.CoursePreviewForUserViewModel;
import com.topprofessors.ViewModel.Course.CourseResultOfUserLiteViewModel;
import com.topprofessors.ViewModel.Course.CourseStatForUserViewModel;
import com.topprofessors.ViewModel.Course.CoursesResultOfUserLitesViewModel;
import com.topprofessors.ViewModel.Course.JsonAnswerStatusCoursePreviewForUserViewModels;

@Component
public class CourseFacade {

	@Autowired
	CourseService courseService;
	
	@Lazy
	@Autowired
	DisciplineFacade disciplineFacade;
	
	@Lazy
	@Autowired
	DisciplineService disciplineService;

	@Lazy
	@Autowired
	TestService testService;

	@Lazy
	@Autowired
	TestUserService testUserService;

	@Lazy
	@Autowired
	ConnectionCourseToUserService connectionCourseToUserService;

	@Lazy
	@Autowired
	ConnectionDisciplineToCourseService connectionDisciplineToCourseService;

	@Lazy
	@Autowired
	UserFacade userFacade;

	@Lazy
	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	HomeworkUserService homeworkUserService;
	
	/*
	public ArrayList<CourseStatForUserViewModel> getListStatisticByUser(User user) {
		ArrayList<Course> coursesOfUser = courseService.listAllAvailableForUser(user.getId());
		
		
	}
	*/
	
	public boolean checkCourseForDone(User user, Course course, ArrayList<Discipline> disciplinesOfCourse, ArrayList<TestUser> testUsers) {
		
		Optional<TestUser> testUserOptional;
		TestUser testUserChecking;
		
		int numberOfDisiplinesWhichDone = 0;
		int countOfDisciplinesInCourse = 0;
		
		if(disciplinesOfCourse.isEmpty()) {
			System.out.println("Дисциплин для курса " + course.getId() + " не найдено");
			return false;
		}
		
		ArrayList<HomeworkUser> homeworkUsersOfUser = homeworkUserService.listAllByUserId(user.getId());
		
		
		countOfDisciplinesInCourse = disciplinesOfCourse.size();
		numberOfDisiplinesWhichDone = 0;
		boolean isTestDone = false;
		boolean isHomeworkDone = false;
		for(Discipline discipline : disciplinesOfCourse) {
			isTestDone = false;
			isHomeworkDone = false;
			
			if(discipline.getIsWithoutTest() == 1) {
				System.out.println("Дисциплина "+ discipline.getId() +" для курса " + course.getId() + " проходит без теста");
				//numberOfDisiplinesWhichDone+=1;
				//continue;
				isTestDone = true;
			} else {
				if(discipline.getTestId() == 0) {
					System.out.println("Дисциплина "+ discipline.getId() +" для курса " + course.getId() + " не указан тест");
					continue;
				}

				testUserOptional = testUsers.stream()
						.filter(testUser -> testUser.getTestId() == discipline.getTestId())
						.findFirst();
				if(!testUserOptional.isPresent()) {
					System.out.println("Дисциплина "+ discipline.getId() +" для курса " + course.getId() + " не найден test_user");
					isTestDone = false;
				} else {
					testUserChecking = testUserOptional.get();
					if(testUserChecking.getIsDone() == 1) {
						//numberOfDisiplinesWhichDone += 1;
						isTestDone = true;
					}
				}
			}
			
			if(discipline.getIsWithoutHomework() == 1) {
				isHomeworkDone = true;
			} else {
				if(discipline.getHomeworkId() == 0) {
					System.out.println("У дисциплины не указано id ДЗ");
					isHomeworkDone = false;
				} else {
					//System.out.println("discipline.getHomeworkId():" + discipline.getHomeworkId());
					for(HomeworkUser homeworkUser : homeworkUsersOfUser) {
						//System.out.println("Checking homework_user id:" + homeworkUser.getId() + " is_accepted:" + homeworkUser.getIsAccepted());
						if(discipline.getHomeworkId() == homeworkUser.getHomeworkId()) {
							//System.out.println("Checking homework_user id:" + homeworkUser.getId() + " is_accepted:" + homeworkUser.getIsAccepted());
							if(homeworkUser.getIsAccepted() == 1) {
								isHomeworkDone = true;
								break;
							}
						}
					}
				}
			}
			
			if(isTestDone && isHomeworkDone) {
				//System.out.println("Дисциплина " + discipline.getId() + " " + discipline.getName() + " is done");
				numberOfDisiplinesWhichDone += 1;
			} else {
				//System.out.println("Дисциплина " + discipline.getId() + " " + discipline.getName() + " is NOT done"
						//+ " isTestDone: " + isTestDone + " isHomeworkDone: " + isHomeworkDone);
			}
		}
		
		System.out.println("Проверяем на сдачу курс " + course.getId() + " countOfDisciplinesInCourse: " + countOfDisciplinesInCourse +
				" numberOfDisiplinesWhichDone: " + numberOfDisiplinesWhichDone);
		
		return numberOfDisiplinesWhichDone >= countOfDisciplinesInCourse;
	}
	
	public JsonAnswerStatus getInfoForUser(int userId, int courseId) {

		Course course = courseService.findById(courseId);
		if(course == null)return new JsonAnswerStatus("error", "course_not_found");
		System.out.println("userId: " + userId + " courseId: " + courseId);
		//проверка доступа
		ConnectionCourseToUser connectionCourseToUser = connectionCourseToUserService.findByCourseIdAndUserId(courseId, userId);
		if(connectionCourseToUser == null)return new JsonAnswerStatus("error", "access_denied");
		//if(connectionCourseToUser.getIsDone() == 1)return new JsonAnswerStatus("error", "course_done");
		if(connectionCourseToUser.getActive() == 0)return new JsonAnswerStatus("error", "access_denied");
		
		CourseInfoViewModel courseInfoViewModel = this.getInfo(course, userId, connectionCourseToUser);
		if(courseInfoViewModel == null)return new JsonAnswerStatus("error", null);
		
		return new JsonAnswerStatus("success", null, courseInfoViewModel);
	}
	
	public CourseInfoViewModel getInfo(int courseId, int userId, ConnectionCourseToUser connectionCourseToUser) {
		Course course = courseService.findById(courseId);
		if(course == null)return null;
		return this.getInfo(course, userId, connectionCourseToUser);
	}
	
	private CourseInfoViewModel getInfo(Course course, int userId, ConnectionCourseToUser connectionCourseToUser) {
		if(course == null)return null;
		CourseStatusTypeForUser сourseStatusTypeForUser = CourseStatusTypeForUser.CLOSED;
		//ConnectionCourseToUser connectionCourseToUser = connectionCourseToUserService.findByCourseIdAndUserId(course.getId(), userId);
		if(connectionCourseToUser != null) {
			if(connectionCourseToUser.getIsDone() == 1) {
				сourseStatusTypeForUser = CourseStatusTypeForUser.DONE;
			} else if(connectionCourseToUser.getActive() == 1) {
				сourseStatusTypeForUser = CourseStatusTypeForUser.OPENED;
			}
		}
		
		ArrayList<DisciplineLiteViewModel> disciplineLiteViewModels = disciplineFacade.listLitesByCourseId(course.getId());
		
		return new CourseInfoViewModel(
			course.getId(),
			course.getName(),
			course.getDescription(),
			course.getActive(),
			this.getPosterSrc(course.getId()),
			сourseStatusTypeForUser,
			disciplineLiteViewModels
		);
	}
	
	public JsonAnswerStatusCoursePreviewForUserViewModels listAllPreviewsForUser(int userId) {

		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatusCoursePreviewForUserViewModels("error", "not_found");
		if(user.getAgreement() != 2)return new JsonAnswerStatusCoursePreviewForUserViewModels("error", "agreement_not_accepted");
		if(!userFacade.isNesessaryFieldsAreFilled(user))
			return new JsonAnswerStatusCoursePreviewForUserViewModels("error", "profile_not_filled");
		if(!userFacade.isNesessaryImgDocsUploaded(user))
			return new JsonAnswerStatusCoursePreviewForUserViewModels("error", "docs_not_uploaded");
		
		return new JsonAnswerStatusCoursePreviewForUserViewModels("success", null, this.listAllPreviewsForUser(user));
	}
	
	public ArrayList<CoursePreviewForUserViewModel> listAllPreviewsForUser(User user){
		
		List<Course> courses = courseService.listAllActiveAndVisible();
		ArrayList<CoursePreviewForUserViewModel> coursePreviewForUserViewModels = new ArrayList<CoursePreviewForUserViewModel>();
		
		List<ConnectionCourseToUser> connectionCourseToUsers = connectionCourseToUserService.listAllByUser(user.getId());
		//HashSet<ConnectionCourseToUser> hashConnectionCourseToUsers = new HashSet<ConnectionCourseToUser>(connectionCourseToUsers);
		
		CourseStatusTypeForUser courseStatusType;
		for(Course course : courses) {
			
			courseStatusType = CourseStatusTypeForUser.CLOSED;
			for(ConnectionCourseToUser connectionCourseToUser : connectionCourseToUsers) {
				//if(connectionCourseToUser.getCourse() != null) {
					//System.out.println("Рассматриваем курс: " + connectionCourseToUser.getCourse().getId());
					if(connectionCourseToUser.getCourseId() == course.getId()) {
						if(connectionCourseToUser.getIsDone() == 1) {
							courseStatusType = CourseStatusTypeForUser.DONE;
						} else if(connectionCourseToUser.getActive() == 1) {
							courseStatusType = CourseStatusTypeForUser.OPENED;
						}
					}
				//}
			}
			
			coursePreviewForUserViewModels.add(new CoursePreviewForUserViewModel(
					course.getId(),
					course.getName(),
					getPosterSrc(course.getId()),
					courseStatusType
			));
		}
		
		return coursePreviewForUserViewModels;
	}
	
	public JsonAnswerStatus listAllResultLitesViewModel(int userId) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		return new JsonAnswerStatus("success", null, this.listAllResultLitesViewModel(user));
	}
	
	private CoursesResultOfUserLitesViewModel listAllResultLitesViewModel(User user) {
		if(user == null)return null;
		return new CoursesResultOfUserLitesViewModel(this.listAllResultLiteViewModels(user));
	}
	
	private ArrayList<CourseResultOfUserLiteViewModel> listAllResultLiteViewModels(User user){
		if(user == null)return null;
		//ArrayList<Course> coursesForUserWithAnyAccess = courseService.listAllForUserWithAnyAccess(user.getId());
		//ArrayList<CourseResultOfUserLiteViewModel> courseResultOfUserLiteViewModels = new ArrayList<CourseResultOfUserLiteViewModel>();

		//List<ConnectionCourseToUser> connectionCourseToUsers = connectionCourseToUserService.listAllByUser(user.getId());
		
		ArrayList<Course> coursesForUserWithAnyAccess = courseService.listAllForUserWithAnyAccess(user.getId());
		
		List<ConnectionCourseToUser> connectionCourseToUsers = connectionCourseToUserService.listAllByUser(user.getId());
		ArrayList<Discipline> disciplinesAll = disciplineService.listAll();
		ArrayList<Discipline> disciplinesOfCourse;
		ArrayList<ConnectionDisciplineToCourse> connectionDisciplineToCoursesAll = connectionDisciplineToCourseService.listAll();
		
		ArrayList<Test> testsAll = testService.listAll();
		Optional<Test> testOfDiscipline;
		ArrayList<TestUser> testUsersOfUser = testUserService.listAllByUser(user.getId());
		Optional<TestUser> testUserOfTest;
		
		ArrayList<CourseResultOfUserLiteViewModel> courseResultOfUserLiteViewModels = new ArrayList<CourseResultOfUserLiteViewModel>();;
		ArrayList<DisciplineResultOfUserLiteViewModel> disciplineResultOfUserLiteViewModelsOfCourse;
		DisciplineResultOfUserLiteViewModel disciplineResultOfUserLiteViewModel;
		
		CourseStatusTypeForUser courseStatusType;
		for(Course course : coursesForUserWithAnyAccess) {
			
			courseStatusType = CourseStatusTypeForUser.CLOSED;
			for(ConnectionCourseToUser connectionCourseToUser : connectionCourseToUsers) {
				if(connectionCourseToUser.getCourseId() == course.getId()) {
					if(connectionCourseToUser.getIsDone() == 1) {
						courseStatusType = CourseStatusTypeForUser.DONE;
					} else if(connectionCourseToUser.getActive() == 1) {
						courseStatusType = CourseStatusTypeForUser.OPENED;
					}
				}
			}
			
			disciplineResultOfUserLiteViewModelsOfCourse = new ArrayList<DisciplineResultOfUserLiteViewModel>();
			disciplinesOfCourse = disciplineFacade.listByCourse(course, disciplinesAll, connectionDisciplineToCoursesAll);
			
			for(Discipline disciplineOfCourse : disciplinesOfCourse) {
				
				if(disciplineOfCourse.getIsWithoutTest() == 1) {
					testOfDiscipline = null;
					testUserOfTest = null;
				} else {
					testOfDiscipline = testsAll.stream()
							.filter(test -> disciplineOfCourse.getTestId() == test.getId())
							.findFirst();
					testUserOfTest = testUsersOfUser.stream()
							.filter(testUser -> disciplineOfCourse.getTestId() == testUser.getTestId())
							.findFirst();
				}
				
				disciplineResultOfUserLiteViewModel = disciplineFacade.getResultOfUserLiteViewModel(
					user,
					disciplineOfCourse, 
					(testOfDiscipline != null ? testOfDiscipline.isPresent() ? testOfDiscipline.get() : null : null), 
					(testUserOfTest != null ? testUserOfTest.isPresent() ? testUserOfTest.get() : null : null)
				);
				disciplineResultOfUserLiteViewModelsOfCourse.add(disciplineResultOfUserLiteViewModel);
			}
			
			courseResultOfUserLiteViewModels.add(
				new CourseResultOfUserLiteViewModel(
					course.getId(),
					course.getName(),
					courseStatusType,
					disciplineResultOfUserLiteViewModelsOfCourse
				)
			);
			
		}
		
		return courseResultOfUserLiteViewModels;
	}
	

	public byte[] getPoster(int courseId) {
		String fileName = "poster.jpg";
		File filePathDest = new File("uploads/course/" + courseId + "/");
        File destFile = new File(filePathDest.getAbsolutePath(), fileName);
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {
        	try {
	        	Path path = Paths.get(destFile.getAbsolutePath());
	            byte[] arr = Files.readAllBytes(path);
	            return arr;
        	} catch(Exception ex) {
            	System.out.println("getPoster Exception: " + ex.getMessage());
        	}
        }
        return null;
	}
	
	private String getPosterSrc(int courseId) {
		String posterSrc = "/api/course/poster/" + courseId;
		File posterFile = new File("./uploads/course/"+courseId+"/poster.jpg");
		return (posterFile.exists() && !posterFile.isDirectory() ? posterSrc : null);
	}
	
}
