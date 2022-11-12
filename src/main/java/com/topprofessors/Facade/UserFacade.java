package com.topprofessors.Facade;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.topprofessors.Component.JwtUtil;
import com.topprofessors.Component.Md5Component;
import com.topprofessors.Component.RandomComponent;
import com.topprofessors.DTO.User.UserProfileEditDTO;
import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.Region;
import com.topprofessors.Entity.TestUser;
import com.topprofessors.Entity.User;
import com.topprofessors.Model.User.UserImgDoc;
import com.topprofessors.Observer.UserObserver;
import com.topprofessors.Service.CourseService;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.RegionService;
import com.topprofessors.Service.TestUserService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Region.RegionLiteViewModel;
import com.topprofessors.ViewModel.User.UserForgetViewModel;
import com.topprofessors.ViewModel.User.UserImgDocViewModel;
import com.topprofessors.ViewModel.User.UserImgDocsViewModel;
import com.topprofessors.ViewModel.User.UserLiteViewModel;
import com.topprofessors.ViewModel.User.UserLoginResultViewModel;
import com.topprofessors.ViewModel.User.UserProfileViewModel;

@Component
public class UserFacade {
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;

	@Lazy
	@Autowired
	UserObserver userObserver;
	
	@Lazy
	@Autowired
	RegionService regionService;
	
	@Lazy
	@Autowired
	CourseFacade courseFacade;
	
	@Lazy
	@Autowired
	CourseService courseService;
	
	@Lazy
	@Autowired
	DisciplineService disciplineService;
	
	@Lazy
	@Autowired
	TestUserService testUserService;
	
	@Lazy
	@Autowired
	ConnectionCourseToUserFacade connectionCourseToUserFacade;
	
	private final String SALT_FOR_LOGIN = "XXXXXXXXXXXX";
	
	
	private final HashSet<String> availableFileNames = new HashSet<String>(Arrays.asList("photo", "diplom", "supplement", 
			"statement", "changesecondname"));
	private final String userImgDocFolderSalt1 = "XXXXXXXXXXXX";
	private final String userImgDocFolderSalt2 = "XXXXXXXXXXXX";

	public JsonAnswerStatus checkCoursesForDoneByUserId(int userId) {

		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");

		this.checkCoursesForDone(user);
		return new JsonAnswerStatus("success");
	}
	
	public JsonAnswerStatus checkAllUsersForDoneAnyCourses() {

		ArrayList<User> users = userService.listAll();
		for(User user : users) {
			this.checkCoursesForDone(user);
		}

		return new JsonAnswerStatus("success");
	}
	
	public void checkCoursesForDone(User user) {
		ArrayList<Course> coursesOfUserNotDone = courseService.listAllAvailableForUserAndNotDone(user.getId());
		ArrayList<TestUser> testUsers = testUserService.listAllByUser(user.getId());
		
		//Optional<TestUser> testUserOptional;
		//TestUser testUserChecking;
		
		//int numberOfDisiplinesWhichDone = 0;
		//int countOfDisciplinesInCourse = 0;
		ArrayList<Discipline> disciplinesOfCourse;
		boolean isCourseDone = false;
		
		for(Course course : coursesOfUserNotDone){
			disciplinesOfCourse = disciplineService.listAllByCourseId(course.getId());
			if(disciplinesOfCourse.isEmpty()) {
				System.out.println("Дисциплин для курса " + course.getId() + " не найдено");
				continue;
			}
			
			isCourseDone = courseFacade.checkCourseForDone(user, course, disciplinesOfCourse, testUsers);
			
			if(!isCourseDone)continue;
			
			connectionCourseToUserFacade.setDoneByUserIdAndCourseId(user.getId(), course.getId());
				
			//отправить сообщение о сдаче курса пользователю
			userObserver.sendMailToUserThatCourseIsDone(user, course);
			
			//проставить активность пользователю, что он что-то сдал
			
			
			
		}
	}
	
	
	
	public JsonAnswerStatus updateAgreement(int userId, boolean newValue) {
		User user = userService.findById(userId);
		if(user == null)return new UserLoginResultViewModel("error", "not_found");
		
		if(newValue) {
			user.setAgreement(2);
		} else {
			if(user.getAgreement() == 2) {
				user.setAgreement(1);
			} else {
				user.setAgreement(0);
			}
		}
		
		return (userService.update(user)
				? new JsonAnswerStatus("success")
						: new JsonAnswerStatus("error"));
	}
	
	public UserLoginResultViewModel updateProfile(int userId, UserProfileEditDTO userProfileEditDTO) {
		User user = userService.findById(userId);
		if(user == null)return new UserLoginResultViewModel("error", "not_found");
		
		user.setSecondname(userProfileEditDTO.getSecondname());
		user.setFirstname(userProfileEditDTO.getFirstname());
		user.setPatronymic(userProfileEditDTO.getPatronymic());
		if(userProfileEditDTO.getDatebirthday() == null) {
			user.setDatebirthday(null);
		} else {
			
			try {
				Date newDatebirthday = new SimpleDateFormat("yyyy-MM-dd").parse(userProfileEditDTO.getDatebirthday());
				if(newDatebirthday != null) {
					user.setDatebirthday(new java.sql.Date(newDatebirthday.getTime()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(userProfileEditDTO.getRegion_id() == 0) {
			user.setRegionId(0);
		} else {
			Region region = regionService.findById(userProfileEditDTO.getRegion_id());
			if(region == null)return new UserLoginResultViewModel("error", "region_not_found");
			user.setRegionId(region.getId());
		}
		
		
		user.setNationality(userProfileEditDTO.getNationality());
		user.setAddress(userProfileEditDTO.getAddress());
		user.setAddressindex(userProfileEditDTO.getAddressindex());
		user.setSnils(userProfileEditDTO.getSnils());
		user.setTelephone(userProfileEditDTO.getTelephone());
		
		if(userProfileEditDTO.getEducation() < 0 || userProfileEditDTO.getEducation() > 4)
			return new UserLoginResultViewModel("error", "wrong_education");
		user.setEducation(userProfileEditDTO.getEducation());
		
		user.setSpecialization(userProfileEditDTO.getSpecialization());
		user.setPlacework(userProfileEditDTO.getPlacework());
		user.setOffice(userProfileEditDTO.getOffice());
		
		String accessToken = null;
		if(userProfileEditDTO.getPassword() != null && userProfileEditDTO.getPassword() != "") {
			try {
				UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
				//BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();
				//String passwordHash = passwordEcorder.encode(userProfileEditDTO.getPassword());
				String passwordHash = this.generatePasswordHash(userProfileEditDTO.getPassword());
				user.setPassword(passwordHash);
				
				JwtUtil jwtUtil = new JwtUtil();
				accessToken = jwtUtil.generateToken(userDetails);
			
			} catch(UsernameNotFoundException exception) {
				return new UserLoginResultViewModel("error", "try_change_password");
			}
		}
		
		if(!userService.update(user))return new UserLoginResultViewModel("error", "try_update");

		if(accessToken != null) return new UserLoginResultViewModel("success", null, accessToken);
		
		return new UserLoginResultViewModel("success", null);
	}
	
	public UserProfileViewModel getProfile(int userId) {
		User user = userService.findById(userId);
		if(user == null)return null;
		
		String datebirthdayStr = null;
		if(user.getDatebirthday() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			datebirthdayStr = dateFormat.format(user.getDatebirthday());
		}
		Region regionOfUser = null;
		if(user.getRegionId() != 0) {
			regionOfUser = regionService.findById(user.getRegionId());
		}
		
		return new UserProfileViewModel(
			user.getId(),
			user.getUsername(),
			user.getSecondname(),
			user.getFirstname(),
			user.getPatronymic(),
			datebirthdayStr,
			//(user.getRegion() != null ? new RegionLiteViewModel(user.getRegion().getId(), user.getRegion().getName()) : null),
			(regionOfUser != null ? new RegionLiteViewModel(regionOfUser.getId(), regionOfUser.getName()) : null),
			user.getNationality(),
			user.getAddress(),
			user.getAddressindex(),
			user.getSnils(),
			user.getTelephone(),
			user.getEducation(),
			user.getSpecialization(),
			user.getPlacework(),
			user.getOffice(),
			user.getAgreement(),
			//getPhotoSrc(user.getId())
			getUserImgDocSrc(user.getId(), "photo", 0)
		);
	}
	
	public boolean isNesessaryFieldsAreFilled(User user) {
		if(user == null)return false;
		return user.getSecondname() != null
				&& user.getFirstname() != null
				&& user.getPatronymic() != null
				&& user.getDatebirthday() != null
				&& user.getRegionId() != 0
				&& user.getNationality() != null
				&& user.getAddress() != null
				&& user.getAddressindex() != null
				&& user.getSnils() != null
				&& user.getTelephone() != null
				&& user.getEducation() != 0
				&& user.getSpecialization() != null
				&& user.getPlacework() != null;
	}
	
	public boolean isNesessaryImgDocsUploaded(User user) {
		if(user == null)return false;
		return !listAllImgDocByUserImgDocType(user.getId(), "diplom").isEmpty()
				&& !listAllImgDocByUserImgDocType(user.getId(), "supplement").isEmpty()
				&& !listAllImgDocByUserImgDocType(user.getId(), "statement").isEmpty();
	}
	
	public UserLoginResultViewModel login(String username, String password) {

		if(username == null)return new UserLoginResultViewModel("error", "wrong");
		
		try {
			username = username.toLowerCase();
			System.out.println("username: " + username);
			UserDetails userDetails = userService.loadUserByUsername(username);
			//BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
			//String passwordCheck = passwordEncoder.encode(password);
			String passwordCheck = this.generatePasswordHash(password);
			System.out.println("passwordCheck: " + passwordCheck);
			//System.out.println("userDetails.getPassword(): " + userDetails.getPassword());
			
			//if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			if(!this.generatePasswordHash(password).equals(userDetails.getPassword())) {
				System.out.println("Пароль не правильный");
				return new UserLoginResultViewModel("error", "wrong");
			}
			
			JwtUtil jwtUtil = new JwtUtil();
			String accessToken = jwtUtil.generateToken(userDetails);
			return new UserLoginResultViewModel("success", null, accessToken);
		
		} catch(UsernameNotFoundException exception) {
			return new UserLoginResultViewModel("error", "wrong");
		}
	}
	
	public JsonAnswerStatus forget(int step, String username, int forgetId, String code) {
		
		if(step != 0 && step != 1)return new JsonAnswerStatus("error", "no_step");
		
		if(step == 0 && username != null) {
			username = username.toLowerCase();
			User user = userService.findByUsername(username);
			if(user == null)return new JsonAnswerStatus("error", "user_not_found");
			
			String newCode = RandomComponent.RandomIntAsString(6);
			Date dateLastTry = new Date();
			
			//need check forget
			
			user.setForgetLastTry(new Timestamp(dateLastTry.getTime()));
			user.setForgetCode(newCode);
			user.setForgetTryCount(0);
			userService.update(user);
			
			userObserver.sendMailForgetNewCode(user, newCode);
			
			UserForgetViewModel userForgetViewModel = new UserForgetViewModel(user.getId());
			return new JsonAnswerStatus("success", null, userForgetViewModel);
			
		} else if(step == 1 && forgetId != 0 && code != null) {
			User user = userService.findById(forgetId);
			if(user == null)return new JsonAnswerStatus("error", "user_not_found");
			if(user.getForgetCode() == null)return new JsonAnswerStatus("error", "code_empty");

			Date dateLastTry = new Date();
			user.setForgetLastTry(new Timestamp(dateLastTry.getTime()));
			if(user.getForgetCode().equals(code)) {
				String newPassword = RandomComponent.RandomString(6);
				String newPasswordHash = this.generatePasswordHash(newPassword);
				user.setForgetCode(null);
				user.setPassword(newPasswordHash);
				if(!userService.update(user))return new JsonAnswerStatus("error", "try_save_password");
				
				userObserver.sendMailNewPassword(user, newPassword);
				
				//auth
				try {
					UserDetails userDetails = userService.loadUserById(forgetId);
					JwtUtil jwtUtil = new JwtUtil();
					String accessToken = jwtUtil.generateToken(userDetails);
					UserForgetViewModel userForgetViewModel = new UserForgetViewModel(accessToken);
					return new JsonAnswerStatus("success", null, userForgetViewModel);
				} catch(UsernameNotFoundException exception) {
					return new JsonAnswerStatus("error", "try_generate_access_token");
				}
				
			} else {
				if(user.getForgetTryCount() == 0) {
					user.setForgetTryCount(1);
					if(!userService.update(user))return new JsonAnswerStatus("error", "try_update_count");
					return new JsonAnswerStatus("error", "try_2");
				} else if(user.getForgetTryCount() == 1) {
					user.setForgetTryCount(2);
					if(!userService.update(user))return new JsonAnswerStatus("error", "try_update_count");
					return new JsonAnswerStatus("error", "try_1");
				} else if(user.getForgetTryCount() >= 2) {
					user.setForgetCode(null);
					return new JsonAnswerStatus("error", "try_limit");
				}
			}
		}
		
		return new JsonAnswerStatus("error");
	}
	
	
	
	public JsonAnswerStatus add(String username, String password) {
		if(username == null)return new JsonAnswerStatus("error", "no_username");
		username = username.toLowerCase();
		User userAlreadyExist = userService.findByUsername(username);
		if(userAlreadyExist != null)return new JsonAnswerStatus("error", "already_exist");

		BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();
		String passwordHash = passwordEcorder.encode(password);
		
		return (userService.add(username, passwordHash) != null
				? new JsonAnswerStatus("success")
				: new JsonAnswerStatus("error"));
	}
	
	
	
	public byte[] photoGet(int userId) {
		return null;
	}
	
	public JsonAnswerStatus photoUpload(HttpServletRequest request, MultipartFile file, int userId) {
        if (!file.isEmpty()) {
            try {
            	...
                
    			return new JsonAnswerStatus("success");
                
            } catch(Exception ex) {
            	System.out.println("UserFacade photoUpload exception: " + ex);
        		return new JsonAnswerStatus("error", "try_save_file");
            }
        }
		return new JsonAnswerStatus("error");
	}
	
	
	public byte[] getImgDocFile(String userFolder, String imgDocFileName, int numberOfFile) {
		if(!this.availableFileNames.contains(imgDocFileName))return null;
		
		String fileName = numberOfFile + ".jpg";
		File filePathDest = new File("uploads/user/" + userFolder + "/" + imgDocFileName);
        File destFile = new File(filePathDest.getAbsolutePath(), fileName);
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {
        	try {
	        	Path path = Paths.get(destFile.getAbsolutePath());
	            byte[] arr = Files.readAllBytes(path);
	            return arr;
        	} catch(Exception ex) {
            	System.out.println("getImgDocFileForUser Exception: " + ex.getMessage());
        	}
        } else {
        	System.out.println("File not found");
        }
        return null;
	}

	public JsonAnswerStatus uploadImgDocFile(int userId, String imgDocFileName, MultipartFile file) {
		if(file == null)return new JsonAnswerStatus("error", "no_file");
		if(!this.availableFileNames.contains(imgDocFileName))return new JsonAnswerStatus("error", "filename_not_available");
		
        if (!file.isEmpty()) {
           
			...
        }
		return new JsonAnswerStatus("error");
	}

	public JsonAnswerStatus deleteImgDocFile(int userId, String imgDocFileName, int numberOfFile) {
		...
	}
	
	public UserImgDocsViewModel getAllImgDocFilesOfUser(int userId) {
		return new UserImgDocsViewModel(
			listAllImgDocByUserImgDocType(userId, "diplom"),
			listAllImgDocByUserImgDocType(userId, "supplement"),
			listAllImgDocByUserImgDocType(userId, "statement"),
			listAllImgDocByUserImgDocType(userId, "changesecondname")
		);
	}

	private ArrayList<UserImgDocViewModel> listAllImgDocByUserImgDocType(int userId, String imgDocFileName){
		if(!this.availableFileNames.contains(imgDocFileName))return null;
		ArrayList<UserImgDocViewModel> changeSecondnameSrcs = new ArrayList<UserImgDocViewModel>();
		String changeSecondnameSrc;
		for(int i = 0; i < 10; i++) {
			changeSecondnameSrc = null;
			changeSecondnameSrc = getUserImgDocSrc(userId, imgDocFileName, i);
			if(changeSecondnameSrc != null)changeSecondnameSrcs.add(new UserImgDocViewModel(i, changeSecondnameSrc));
		}
		return changeSecondnameSrcs;
	}
	

	private String getUserImgDocSrc(int userId, String imgDocFileName, int numberOfFile) {
		...
	}
	
	
	private String getHashFolderForImgDoc(int userId) {
		Md5Component md5Component = new Md5Component();
		
		String hashFolder = md5Component.getHash(...);
		return hashFolder;
	}
	
	
	private String generatePasswordHash(String password) {
		Md5Component md5Component = new Md5Component();
		return md5Component.getHash(...);
	}
	
	public UserLiteViewModel toLite(User user) {
		if(user == null)return null;
		return new UserLiteViewModel(user.getId(), user.getUsername(), user.getSecondname(), user.getFirstname());
	}
	
}
