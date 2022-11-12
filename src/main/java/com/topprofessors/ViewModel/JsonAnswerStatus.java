package com.topprofessors.ViewModel;

import com.topprofessors.ViewModel.Course.CourseInfoViewModel;
import com.topprofessors.ViewModel.Course.CoursesResultOfUserLitesViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineInfoViewModel;
import com.topprofessors.ViewModel.Lesson.LessonViewModel;
import com.topprofessors.ViewModel.Message.MessageChatViewModel;
import com.topprofessors.ViewModel.Message.PanelListOfMessageParentPreviews;
import com.topprofessors.ViewModel.OnlineAuditoriumData.OnlineAuditoriumLiteViewModel;
import com.topprofessors.ViewModel.OnlineAuditoriumMessage.OnlineAuditoriumMessageLitesViewModel;
import com.topprofessors.ViewModel.Test.TestIndexViewModel;
import com.topprofessors.ViewModel.Test.TestLiteViewModel;
import com.topprofessors.ViewModel.Test.TestStartViewModel;
import com.topprofessors.ViewModel.TestQuestion.TestQuestionLiteViewModel;
import com.topprofessors.ViewModel.TestUser.TestUserResultOfUserViewModel;
import com.topprofessors.ViewModel.User.UserForgetViewModel;
import com.topprofessors.ViewModel.User.UserImgDocsViewModel;
import com.topprofessors.ViewModel.User.UserProfileViewModel;

public class JsonAnswerStatus {

	private String status;
	private String errors;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}

	
	public JsonAnswerStatus() {
		super();
	}
	public JsonAnswerStatus(String status) {
		super();
		this.status = status;
	}
	public JsonAnswerStatus(String status, String errors) {
		super();
		this.status = status;
		this.errors = errors;
	}
	

	private UserForgetViewModel userForgetViewModel;
	public UserForgetViewModel getUserForgetViewModel() {
		return userForgetViewModel;
	}
	public void setUserForgetViewModel(UserForgetViewModel userForgetViewModel) {
		this.userForgetViewModel = userForgetViewModel;
	}
	public JsonAnswerStatus(String status, String errors, UserForgetViewModel userForgetViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.userForgetViewModel = userForgetViewModel;
	}


	private UserProfileViewModel userProfileViewModel;
	public UserProfileViewModel getUserProfileViewModel() {
		return userProfileViewModel;
	}
	public void setUserProfileViewModel(UserProfileViewModel userProfileViewModel) {
		this.userProfileViewModel = userProfileViewModel;
	}
	public JsonAnswerStatus(String status, String errors, UserProfileViewModel userProfileViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.userProfileViewModel = userProfileViewModel;
	}
	
	
	
	private UserImgDocsViewModel userImgDocsViewModel;
	public UserImgDocsViewModel getUserImgDocsViewModel() {
		return userImgDocsViewModel;
	}
	public void setUserImgDocsViewModel(UserImgDocsViewModel userImgDocsViewModel) {
		this.userImgDocsViewModel = userImgDocsViewModel;
	}
	public JsonAnswerStatus(String status, String errors, UserImgDocsViewModel userImgDocsViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.userImgDocsViewModel = userImgDocsViewModel;
	}
	
	
	private CourseInfoViewModel courseInfoViewModel;
	public CourseInfoViewModel getCourseInfoViewModel() {
		return courseInfoViewModel;
	}
	public void setCourseInfoViewModel(CourseInfoViewModel courseInfoViewModel) {
		this.courseInfoViewModel = courseInfoViewModel;
	}
	public JsonAnswerStatus(String status, String errors, CourseInfoViewModel courseInfoViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.courseInfoViewModel = courseInfoViewModel;
	}
	
	
	
	
	private DisciplineInfoViewModel disciplineInfoViewModel;
	public DisciplineInfoViewModel getDisciplineInfoViewModel() {
		return disciplineInfoViewModel;
	}
	public void setDisciplineInfoViewModel(DisciplineInfoViewModel disciplineInfoViewModel) {
		this.disciplineInfoViewModel = disciplineInfoViewModel;
	}
	public JsonAnswerStatus(String status, String errors, DisciplineInfoViewModel disciplineInfoViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.disciplineInfoViewModel = disciplineInfoViewModel;
	}
	
	
	
	
	
	private LessonViewModel lessonViewModel;
	public LessonViewModel getLessonViewModel() {
		return lessonViewModel;
	}
	public void setLessonViewModel(LessonViewModel lessonViewModel) {
		this.lessonViewModel = lessonViewModel;
	}
	public JsonAnswerStatus(String status, String errors, LessonViewModel lessonViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.lessonViewModel = lessonViewModel;
	}
	
	
	
	
	private TestIndexViewModel testIndexViewModel;
	public TestIndexViewModel getTestIndexViewModel() {
		return testIndexViewModel;
	}
	public void setTestIndexViewModel(TestIndexViewModel testIndexViewModel) {
		this.testIndexViewModel = testIndexViewModel;
	}
	public JsonAnswerStatus(String status, String errors, TestIndexViewModel testIndexViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.testIndexViewModel = testIndexViewModel;
	}
	
	
	private TestQuestionLiteViewModel testQuestionLiteViewModel;
	public TestQuestionLiteViewModel getTestQuestionLiteViewModel() {
		return testQuestionLiteViewModel;
	}
	public void setTestQuestionLiteViewModel(TestQuestionLiteViewModel testQuestionLiteViewModel) {
		this.testQuestionLiteViewModel = testQuestionLiteViewModel;
	}
	public JsonAnswerStatus(String status, String errors, TestQuestionLiteViewModel testQuestionLiteViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.testQuestionLiteViewModel = testQuestionLiteViewModel;
	}



	private TestStartViewModel testStartViewModel;
	public TestStartViewModel getTestStartViewModel() {
		return testStartViewModel;
	}
	public void setTestStartViewModel(TestStartViewModel testStartViewModel) {
		this.testStartViewModel = testStartViewModel;
	}
	public JsonAnswerStatus(String status, String errors, TestStartViewModel testStartViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.testStartViewModel = testStartViewModel;
	}

	
	private PanelListOfMessageParentPreviews panelListOfMessageParentPreviews;
	public PanelListOfMessageParentPreviews getPanelListOfMessageParentPreviews() {
		return panelListOfMessageParentPreviews;
	}
	public void setPanelListOfMessageParentPreviews(PanelListOfMessageParentPreviews panelListOfMessageParentPreviews) {
		this.panelListOfMessageParentPreviews = panelListOfMessageParentPreviews;
	}
	public JsonAnswerStatus(String status, String errors,
			PanelListOfMessageParentPreviews panelListOfMessageParentPreviews) {
		super();
		this.status = status;
		this.errors = errors;
		this.panelListOfMessageParentPreviews = panelListOfMessageParentPreviews;
	}
	
	
	private CoursesResultOfUserLitesViewModel coursesResultOfUserLitesViewModel;
	public CoursesResultOfUserLitesViewModel getCoursesResultOfUserLitesViewModel() {
		return coursesResultOfUserLitesViewModel;
	}
	public void setCoursesResultOfUserLitesViewModel(CoursesResultOfUserLitesViewModel coursesResultOfUserLitesViewModel) {
		this.coursesResultOfUserLitesViewModel = coursesResultOfUserLitesViewModel;
	}
	public JsonAnswerStatus(String status, String errors,
			CoursesResultOfUserLitesViewModel coursesResultOfUserLitesViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.coursesResultOfUserLitesViewModel = coursesResultOfUserLitesViewModel;
	}
	
	
	private TestLiteViewModel testLiteViewModel;
	public TestLiteViewModel getTestLiteViewModel() {
		return testLiteViewModel;
	}
	public void setTestLiteViewModel(TestLiteViewModel testLiteViewModel) {
		this.testLiteViewModel = testLiteViewModel;
	}



	private TestUserResultOfUserViewModel testUserResultOfUserViewModel;
	public TestUserResultOfUserViewModel getTestUserResultOfUserViewModel() {
		return testUserResultOfUserViewModel;
	}
	public void setTestUserResultOfUserViewModel(TestUserResultOfUserViewModel testUserResultOfUserViewModel) {
		this.testUserResultOfUserViewModel = testUserResultOfUserViewModel;
	}
	public JsonAnswerStatus(String status, String errors, TestLiteViewModel testLiteViewModel,
			TestUserResultOfUserViewModel testUserResultOfUserViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.testLiteViewModel = testLiteViewModel;
		this.testUserResultOfUserViewModel = testUserResultOfUserViewModel;
	}
	
	
	
	private MessageChatViewModel messageChatViewModel;
	public MessageChatViewModel getMessageChatViewModel() {
		return messageChatViewModel;
	}
	public void setMessageChatViewModel(MessageChatViewModel messageChatViewModel) {
		this.messageChatViewModel = messageChatViewModel;
	}
	public JsonAnswerStatus(String status, String errors, MessageChatViewModel messageChatViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.messageChatViewModel = messageChatViewModel;
	}
	
	
	private OnlineAuditoriumLiteViewModel onlineAuditoriumLiteViewModel;
	public OnlineAuditoriumLiteViewModel getOnlineAuditoriumLiteViewModel() {
		return onlineAuditoriumLiteViewModel;
	}
	public void setOnlineAuditoriumLiteViewModel(OnlineAuditoriumLiteViewModel onlineAuditoriumLiteViewModel) {
		this.onlineAuditoriumLiteViewModel = onlineAuditoriumLiteViewModel;
	}
	public JsonAnswerStatus(String status, String errors, OnlineAuditoriumLiteViewModel onlineAuditoriumLiteViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.onlineAuditoriumLiteViewModel = onlineAuditoriumLiteViewModel;
	}
	
	
	
	private OnlineAuditoriumMessageLitesViewModel onlineAuditoriumMessageLitesViewModel;
	public OnlineAuditoriumMessageLitesViewModel getOnlineAuditoriumMessageLitesViewModel() {
		return onlineAuditoriumMessageLitesViewModel;
	}
	public void setOnlineAuditoriumMessageLitesViewModel(
			OnlineAuditoriumMessageLitesViewModel onlineAuditoriumMessageLitesViewModel) {
		this.onlineAuditoriumMessageLitesViewModel = onlineAuditoriumMessageLitesViewModel;
	}
	public JsonAnswerStatus(String status, String errors,
			OnlineAuditoriumMessageLitesViewModel onlineAuditoriumMessageLitesViewModel) {
		super();
		this.status = status;
		this.errors = errors;
		this.onlineAuditoriumMessageLitesViewModel = onlineAuditoriumMessageLitesViewModel;
	}

	
}
