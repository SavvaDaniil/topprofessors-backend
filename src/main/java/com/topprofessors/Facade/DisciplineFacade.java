package com.topprofessors.Facade;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.Entity.ConnectionDisciplineToCourse;
import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.HomeworkUser;
import com.topprofessors.Entity.Lesson;
import com.topprofessors.Entity.Test;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Observer.DisciplineUserFileObserver;
import com.topprofessors.Service.ConnectionDisciplineToCourseService;
import com.topprofessors.Service.CourseService;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.DisciplineUserFileService;
import com.topprofessors.Service.HomeworkUserService;
import com.topprofessors.Service.LessonService;
import com.topprofessors.Service.TestService;
import com.topprofessors.Service.TestUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Discipline.DisciplineInfoViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineResultOfUserLiteViewModel;
import com.topprofessors.ViewModel.HomeworkUser.HomeworkUserResultLiteViewModel;
import com.topprofessors.ViewModel.Lesson.LessonLiteViewModel;
import com.topprofessors.ViewModel.Lesson.LessonViewModel;
import com.topprofessors.ViewModel.Test.TestLiteViewModel;
import com.topprofessors.ViewModel.Test.TestResultOfUserLiteViewModel;

@Component
public class DisciplineFacade {

	@Autowired
	DisciplineService disciplineService;

	@Lazy
	@Autowired
	CourseService courseService;

	@Lazy
	@Autowired
	UserService userService;
	
	@Lazy
	@Autowired
	TestFacade testFacade;
	
	@Lazy
	@Autowired
	TestService testService;
	
	@Autowired
	LessonFacade lessonFacade;

	@Lazy
	@Autowired
	LessonService lessonService;
	
	@Autowired
	MessageFacade messageFacade;

	@Lazy
	@Autowired
	TestUserFacade testUserFacade;

	@Autowired
	ConnectionDisciplineToCourseService connectionDisciplineToCourseService;

	@Lazy
	@Autowired
	DisciplineUserFileService disciplineUserFileService;

	@Lazy
	@Autowired
	DisciplineUserFileObserver disciplineUserFileObserver;
	
	@Lazy
	@Autowired
	HomeworkFacade homeworkFacade;
	
	@Lazy
	@Autowired
	HomeworkUserService homeworkUserService;
	

	@Value("${host}")
	private String HOST;
	
	private final String UPLOAD_FOLDER_PATH = "uploads/discipline/user_file";
	
	public JsonAnswerStatus getInfoForUser(int userId, int courseId, int disciplineId, int numberOfLesson) {
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Discipline discipline = disciplineService.findById(disciplineId);
		if(discipline == null)return new JsonAnswerStatus("error", "not_found");
		
		//
		
		//проверить доступ
		
		
		DisciplineInfoViewModel disciplineInfoViewModel = getInfo(user, discipline, numberOfLesson);
		return new JsonAnswerStatus("success", null, disciplineInfoViewModel);
	}
	
	public DisciplineInfoViewModel getInfo(User user, Discipline discipline, int numberOfLesson) {
		if(discipline == null)return null;
		ArrayList<LessonLiteViewModel> lessonLiteViewModels = lessonFacade.listAllLiteByDisciplineId(discipline.getId());
		
		LessonViewModel lessonViewModel = null;
		if(!lessonLiteViewModels.isEmpty() && numberOfLesson > 0) {
			lessonViewModel = lessonFacade.getViewModel(lessonLiteViewModels.get(numberOfLesson - 1).getId());
		}
		
		DisciplineInfoViewModel disciplineInfoViewModel = new DisciplineInfoViewModel(
			discipline.getId(),
			discipline.getName(),
			discipline.getDescription(),
			discipline.getIsWithoutTest(),
			lessonLiteViewModels,
			lessonViewModel,
			(lessonViewModel != null && user != null ? messageFacade.countOfUnreadMessagesByAdminForUser(user.getId(), discipline.getId(), lessonViewModel.getId()) : 0),
			discipline.getIsWithoutHomework()
		);

		if(discipline.getTestId() != 0) {
			Test test = testService.findById(discipline.getTestId());
			if(test != null) {
				disciplineInfoViewModel.setTestLiteViewModel(
					testFacade.getLite(test)
				);
				
				if(user != null) {
					//ищем состояние user по тесту
					disciplineInfoViewModel.setTestUserLiteViewModel(testUserFacade.getLiteByTestIdAndUserId(test.getId(), user.getId()));
				}
			}
		}
		
		if(discipline.getHomeworkId() != 0) {
			disciplineInfoViewModel.setHomeworkLiteForUserViewModel(homeworkFacade.getLiteForUserById(user, discipline.getHomeworkId()));
		}
		
		return disciplineInfoViewModel;
	}
	
	public ArrayList<DisciplineLiteViewModel> listLitesByCourseId(int courseId){

		//НУЖНО ЧЕРЕЗ INNER JOIN
		
		List<Discipline> disciplines = disciplineService.listAll();
		
		List<ConnectionDisciplineToCourse> connectionDisciplineToCourses = connectionDisciplineToCourseService.listAllByCourseId(courseId);
		ArrayList<DisciplineLiteViewModel> disciplineLiteViewModels = new ArrayList<DisciplineLiteViewModel>();
		for(ConnectionDisciplineToCourse connectionDisciplineToCourse : connectionDisciplineToCourses) {
			for(Discipline discipline : disciplines) {
				//if(connectionDisciplineToCourse.getDiscipline() != null) {
					if(connectionDisciplineToCourse.getDisciplineId() == discipline.getId()) {
						disciplineLiteViewModels.add(new DisciplineLiteViewModel(discipline.getId(), discipline.getName()));
					}
				//}
			}
		}
		
		return disciplineLiteViewModels;
	}

	public ArrayList<Discipline> listByCourse(
			Course course, 
			ArrayList<Discipline> disciplinesAll, 
			ArrayList<ConnectionDisciplineToCourse> connectionDisciplineToCoursesAll){
		
		if(course == null)return null;
		
		ArrayList<Discipline> disciplinesOfCourse = new ArrayList<Discipline>();
		for(ConnectionDisciplineToCourse connectionDisciplineToCourse : connectionDisciplineToCoursesAll) {
			if(connectionDisciplineToCourse.getCourseId() != course.getId())continue;
			for(Discipline discipline : disciplinesAll) {
				if(connectionDisciplineToCourse.getDisciplineId() == discipline.getId()) {
					disciplinesOfCourse.add(discipline);
				}
			}
		}
		
		return disciplinesOfCourse;
	}
	
	public DisciplineResultOfUserLiteViewModel getResultOfUserLiteViewModel(
			User user, Discipline discipline, Test testOfDiscipline, TestUser testUser
		) {
		if(discipline == null)return null;
		
		DisciplineResultOfUserLiteViewModel disciplineResultOfUserLiteViewModel = null;
		
		if(discipline.getIsWithoutTest() == 1) {
			disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(discipline.getId(), discipline.getName(), true);
		} else {
			if(testOfDiscipline == null) {
				disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(discipline.getId(), discipline.getName());
				return disciplineResultOfUserLiteViewModel;
			}
			if(discipline.getTestId() != testOfDiscipline.getId()) {
				System.out.println("Тест "+testOfDiscipline.getId()+" не относится к дисциплине " + discipline.getId());
				return null;
			}

			if(testUser == null) {
				//тест не открывали
				disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(
						discipline.getId(), 
						discipline.getName(),
						new TestResultOfUserLiteViewModel(testOfDiscipline.getId(), testOfDiscipline.getName())
				);
			} else if(testUser.getIsFinished() == 0) {
				//тест открыли, но не начинали
				disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(
						discipline.getId(), 
						discipline.getName(),
						new TestResultOfUserLiteViewModel(testOfDiscipline.getId(), testOfDiscipline.getName())
				);
			} else if(testUser.getIsFinished() == 1) {
				//тест еще не закончили
				disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(
						discipline.getId(), 
						discipline.getName(),
						new TestResultOfUserLiteViewModel(
							testOfDiscipline.getId(), 
							testOfDiscipline.getName(),
							testUser.getId(),
							true, 
							false, 
							false, 
							testOfDiscipline.getNeededPoints(), 
							0
						)
				);
			} else if(testUser.getIsFinished() == 2) {
				disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(
						discipline.getId(), 
						discipline.getName(),
						new TestResultOfUserLiteViewModel(
							testOfDiscipline.getId(), 
							testOfDiscipline.getName(), 
							testUser.getId(),
							true, 
							true,
							testUser.getIsDone() == 1, 
							testOfDiscipline.getNeededPoints(),
							testUser.getPoints()
						)
				);
			}
		}

		if(disciplineResultOfUserLiteViewModel == null) {
			disciplineResultOfUserLiteViewModel = new DisciplineResultOfUserLiteViewModel(discipline.getId(), discipline.getName());
		}
		
		if(discipline.getIsWithoutHomework() == 1) {
			disciplineResultOfUserLiteViewModel.setIsWithoutHomework(true);
			return disciplineResultOfUserLiteViewModel;
		} else if(user != null) {
			HomeworkUser homeworkUser = homeworkUserService.findByUserIdAndHomeworkId(user.getId(), discipline.getHomeworkId());
			if(homeworkUser == null) {
				return disciplineResultOfUserLiteViewModel;
			}
			HomeworkUserResultLiteViewModel homeworkUserResultLiteViewModel = new HomeworkUserResultLiteViewModel(
					homeworkUser.getId(), 
					(homeworkUser.getIsAccepted() == 1),
					(homeworkUser.getIsDenied() == 1)
				);
			disciplineResultOfUserLiteViewModel.setHomeworkUserResultLiteViewModel(homeworkUserResultLiteViewModel);
			return disciplineResultOfUserLiteViewModel;
		}
		
		
		return null;
	}
	
	public DisciplineLiteViewModel toLite(Discipline discipline) {
		if(discipline == null)return null;
		return new DisciplineLiteViewModel(discipline.getId(), discipline.getName());
	}
	
	public JsonAnswerStatus newFileFromUser(int userId, int courseId, int disciplineId, int lessonId, MultipartFile file) {

		if(file.isEmpty())return new JsonAnswerStatus("error", "no_file");
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Course course = courseService.findById(courseId);
		if(course == null)return new JsonAnswerStatus("error", "course_not_found");

		Discipline discipline = disciplineService.findById(disciplineId);
		if(discipline == null)return new JsonAnswerStatus("error", "discipline_not_found");
		
		Lesson lesson = lessonService.findById(lessonId);
		if(lesson == null)return new JsonAnswerStatus("error", "lesson_not_found");

        try {

    		String filenameWithoutType = disciplineUserFileService.generateFilenameWithoutType(user, course, discipline, lesson);
    		if(filenameWithoutType == null)return new JsonAnswerStatus("error", "fail_create_filename");
        	
            if(! new File(UPLOAD_FOLDER_PATH).exists())
            {
                new File(UPLOAD_FOLDER_PATH).mkdir();
            }

            System.out.println("realPathtoUploads: " + UPLOAD_FOLDER_PATH);
            
            File uploadFolderPathDest = new File(UPLOAD_FOLDER_PATH);
            
            // Convert to absolute path
            File destFile = new File(uploadFolderPathDest.getAbsolutePath(), filenameWithoutType + ".jpg");
            System.out.println("filePathDest.getAbsolutePath(): " + uploadFolderPathDest.getAbsolutePath());
            if (!uploadFolderPathDest.exists()) {
            	uploadFolderPathDest.mkdirs();
            }
            file.transferTo(destFile);

            String urlToFile = HOST + "/" + UPLOAD_FOLDER_PATH + "/" +  filenameWithoutType + ".jpg";
            if(!disciplineUserFileObserver.sendFileToAdminFromUser(
            		user,
            		course,
            		discipline,
            		lesson,
            		destFile, 
            		urlToFile
            		)
        		)
            	return new JsonAnswerStatus("error", "fail_send_email_to_admin");

			return new JsonAnswerStatus("success");
            
        } catch(Exception ex) {
        	System.out.println("DisciplineFacade newFileFromUser exception: " + ex);
        }
		
		return new JsonAnswerStatus("error", null);
		
	}
	
	
	
	
}
