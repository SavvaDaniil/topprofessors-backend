package com.topprofessors.Facade;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.topprofessors.Abstract.ViewModel.MessageAbstractViewModel;
import com.topprofessors.Entity.Admin;
import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.Discipline;
import com.topprofessors.Entity.Lesson;
import com.topprofessors.Entity.Message;
import com.topprofessors.Entity.User;
import com.topprofessors.Service.AdminService;
import com.topprofessors.Service.CourseService;
import com.topprofessors.Service.DisciplineService;
import com.topprofessors.Service.LessonService;
import com.topprofessors.Service.MessageService;
import com.topprofessors.Service.UserService;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Admin.AdminLiteViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;
import com.topprofessors.ViewModel.Message.MessageChatViewModel;
import com.topprofessors.ViewModel.Message.MessageFromAdminViewModel;
import com.topprofessors.ViewModel.Message.MessageFromUserViewModel;
import com.topprofessors.ViewModel.Message.MessageParentPreviewViewModel;
import com.topprofessors.ViewModel.Message.MessageParentViewModel;
import com.topprofessors.ViewModel.Message.PanelListOfMessageParentPreviews;
import com.topprofessors.ViewModel.User.UserLiteViewModel;

@Component
public class MessageFacade {

	@Autowired
	UserService userService;

	@Lazy
	@Autowired
	AdminService adminService;

	@Lazy
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
	LessonService lessonService;
	
	@Autowired
	MessageService messageService;
	

	public JsonAnswerStatus getChatForUser(int userId, int parentMessageId) {

		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Message messageParent = messageService.findById(parentMessageId);
		if(messageParent == null)return new JsonAnswerStatus("error", "messageParent_not_found");
		if(messageParent.getUserIdFrom() != userId)return new JsonAnswerStatus("error", "access_denied");

		Discipline discipline = disciplineService.findById(messageParent.getDisciplineId());
		MessageChatViewModel messageChatViewModel = this.getChatForUser(user, messageParent, discipline);
		
		return new JsonAnswerStatus("success", null, messageChatViewModel);
	}
	
	
	public MessageChatViewModel getChatForUser(User user, Message messageParent, Discipline discipline) {
		if(user == null || messageParent == null)return null;

		DisciplineLiteViewModel disciplineLiteViewModel = (discipline != null ? disciplineFacade.toLite(discipline) : null);
		
		return new MessageChatViewModel(
			disciplineLiteViewModel,
			this.toParentViewModel(messageParent),
			this.listChatForUser(user, messageParent)
		);
	}
	
	
	public ArrayList<MessageAbstractViewModel> listChatForUser(User user,  Message messageParent){
		if(user == null || messageParent == null)return null;
		
		ArrayList<MessageAbstractViewModel> messageViewModels = new ArrayList<MessageAbstractViewModel>();
		ArrayList<Message> messagesOfChat = messageService.listByParentMessageId(messageParent.getId());
		
		ArrayList<Discipline> disciplines = disciplineService.listAll();
		DisciplineLiteViewModel disciplineLiteViewModel = null;
		Optional<Discipline> disciplineOptional;
		ArrayList<Admin> admins = adminService.listAll();
		AdminLiteViewModel adminLiteViewModel = null;
		Optional<Admin> adminOptional;
		
		UserLiteViewModel userLiteViewModel;
		
		MessageAbstractViewModel messageFromViewModel;
		for(Message message : messagesOfChat) {
			//System.out.println("Рассматриваем message.id: " + message.getId());
			
			if(disciplineLiteViewModel == null) {
				if(message.getDisciplineId() != 0) {
					disciplineOptional = disciplines.stream().filter(discipline -> discipline.getId() == message.getDisciplineId()).findFirst();
					if(disciplineOptional != null) {
						disciplineLiteViewModel = new DisciplineLiteViewModel(disciplineOptional.get().getId(), disciplineOptional.get().getName());
					}
				}
			}
			
			if(message.getUserIdFrom() == user.getId()) {
				
				userLiteViewModel = new UserLiteViewModel(user.getId(), user.getUsername(), user.getSecondname(), user.getFirstname());
				
				messageFromViewModel = new MessageFromUserViewModel(
					message.getId(),
					message.getUserIdFrom(),
					message.getUserIdTo(),
					disciplineLiteViewModel,
					message.getMessageText(),
					false,
					message.getDateOfAdd(),
					message.getDateOfUpdate(),
					userLiteViewModel
				);
				messageViewModels.add(messageFromViewModel);
			} else if(message.getUserIdFrom() != 0 && message.getUserIdTo() == user.getId()) {
				//тут viewmodel от админа
				
				adminOptional = admins.stream().filter(admin -> admin.getId() == message.getUserIdFrom()).findFirst();
				
				adminLiteViewModel = 
						(adminOptional != null ? new AdminLiteViewModel(adminOptional.get().getId(), adminOptional.get().getPosition()) : null);
				

				messageFromViewModel = new MessageFromAdminViewModel(
					message.getId(),
					message.getUserIdFrom(),
					message.getUserIdTo(),
					disciplineLiteViewModel,
					message.getMessageText(),
					false,
					message.getDateOfAdd(),
					message.getDateOfUpdate(),
					adminLiteViewModel
				);
				messageViewModels.add(messageFromViewModel);
			}
		}
		
		//обновить статус у всех сообщений, что их увидел пользователь
		Date dateCurrent = new Date();
		messageService.updateListAsReadedByUser(messageParent.getId(), new Timestamp(dateCurrent.getTime()));
		
		return messageViewModels;
	}
	
	
	public JsonAnswerStatus listAllChatPreviewsForUser(int userId) {
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		PanelListOfMessageParentPreviews panelListOfMessageParentPreviews = new PanelListOfMessageParentPreviews(listAllChatPreviewsForUser(user));
		
		return new JsonAnswerStatus("success", null, panelListOfMessageParentPreviews);
	}
	
	public ArrayList<MessageParentPreviewViewModel> listAllChatPreviewsForUser(User user){
		if(user == null)return null;

		ArrayList<MessageParentPreviewViewModel> messageStartChatPreviewViewModels = new ArrayList<MessageParentPreviewViewModel>();
		ArrayList<Message> messagesStartOfUser = messageService.listAllStartOfUser(user.getId());
		
		ArrayList<Discipline> disciplines = disciplineService.listAll();
		DisciplineLiteViewModel disciplineLiteViewModel;
		Optional<Discipline> disciplineOptional;
		for(Message message : messagesStartOfUser) {
			disciplineLiteViewModel = null;
			if(message.getDisciplineId() != 0) {
				disciplineOptional = disciplines.stream().filter(discipline -> discipline.getId() == message.getDisciplineId()).findFirst();
				
				disciplineLiteViewModel = (disciplineOptional != null 
						? new DisciplineLiteViewModel(disciplineOptional.get().getId(), disciplineOptional.get().getName())
								: null);
			}
			
			messageStartChatPreviewViewModels.add(this.toMessageStartChatPreviewViewModel(user, message, disciplineLiteViewModel));
		}

		return messageStartChatPreviewViewModels;
	}
	
	public JsonAnswerStatus startChatFromUser(int userId, int courseId, int disciplineId, int lessonId, String messageText) {
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Course course = courseService.findById(courseId);
		if(course == null)return new JsonAnswerStatus("error", "course_not_found");
		
		Discipline discipline = disciplineService.findById(disciplineId);
		if(discipline == null)return new JsonAnswerStatus("error", "discipline_not_found");
		
		Lesson lesson = lessonService.findById(lessonId);
		if(lesson == null)return new JsonAnswerStatus("error", "lesson_not_found");
		
		if(this.countOfUnreadMessagesByAdminForUser(userId, disciplineId, lessonId) > 3)return new JsonAnswerStatus("error", "limit");
		
		Date dateOfAdd = new Date();
		
		Message message = new Message();
		message.setIsParent(1);
		/*
		message.setUserFrom(user);
		
		message.setCourse(course);
		message.setDiscipline(discipline);
		message.setLesson(lesson);
		*/
		message.setUserIdFrom(user.getId());
		message.setCourseId(courseId);
		message.setDisciplineId(disciplineId);
		message.setLessonId(lessonId);
		
		message.setMessageText(messageText);
		message.setIsCheckedByUser(1);
		message.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
		message.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
		//message.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
		//message.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
		
		message = messageService.add(message);
		if(message == null) {
			return new JsonAnswerStatus("error", "try_add");
		}
		
		return new JsonAnswerStatus("success");
	}
	
	
	public JsonAnswerStatus newMessageInChatFromUser(int userId, int parentMessageId, String messageText) {
		
		if(messageText == null)return new JsonAnswerStatus("error", "no_message_text");
		
		User user = userService.findById(userId);
		if(user == null)return new JsonAnswerStatus("error", "user_not_found");
		
		Message parentMessage = messageService.findById(parentMessageId);
		if(parentMessage == null)return new JsonAnswerStatus("error", "parentMessage_not_found");
		if(parentMessage.getIsClosedByAdminForce() == 1 || parentMessage.getIsClosedByUser() == 1)
			return new JsonAnswerStatus("error", "parentMessage_closed");

		Message newMessageFromUser = this.generateNewMessageInChatFromUser(user, parentMessage, messageText);
		if(newMessageFromUser == null)return new JsonAnswerStatus("error", "try_create");
		newMessageFromUser = messageService.add(newMessageFromUser);
		if(newMessageFromUser == null)return new JsonAnswerStatus("error", "try_add");
		
		return new JsonAnswerStatus("success");
	}
	
	public Message generateNewMessageInChatFromUser(User user, Message parentMessage, String messageText) {
		if(user == null || parentMessage == null || messageText.isEmpty())return null;
		
		Date dateOfAdd = new Date();
		
		Message message = new Message();
		message.setIsParent(0);
		message.setParentMessageId(parentMessage.getId());
		message.setUserIdFrom(user.getId());
		message.setCourseId(parentMessage.getCourseId());
		message.setDisciplineId(parentMessage.getDisciplineId());
		message.setLessonId(parentMessage.getLessonId());
		
		message.setMessageText(messageText);
		message.setIsCheckedByUser(1);
		message.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
		message.setDateOfUpdate(new Timestamp(dateOfAdd.getTime()));
		
		return message;
	}
	
	public int countOfUnreadMessagesByAdminForUser(int userId, int disciplineId, int lessonId) {
		List<Message> messages = messageService.listAllForUserByUserIdAndDisciplineIdAndLessonId(userId, disciplineId, lessonId);
		if(messages.isEmpty())return 0;
		
		return (int)messages
			.stream()
			.filter(message -> message.getIsDeleted() == 0 && message.getIsCheckedByAdmin() == 0).count();
	}
	
	public MessageParentPreviewViewModel toMessageStartChatPreviewViewModel(
			User user, Message message, DisciplineLiteViewModel disciplineLiteViewModel) {
		if(message == null)return null;
		
		return new MessageParentPreviewViewModel(
			message.getId(),
			message.getDateOfAdd(),
			disciplineLiteViewModel,
			message.getMessageText(),
			(messageService.findFirstUnreadForUserByParentMessageId(user.getId(), message.getId()) != null)
		);
	}
	
	public MessageParentViewModel toParentViewModel(Message message) {
		return new MessageParentViewModel(
			message.getId(),
			message.getUserIdFrom(),
			message.getUserIdTo(),
			message.getCourseId(),
			message.getDisciplineId(),
			message.getLessonId(),
			message.getMessageText(),
			this.countOfUnreadMessagesByAdminForUser(message.getUserIdFrom(), message.getDisciplineId(), message.getLessonId()) > 3,
			(message.getIsClosedByUser() == 1),
			(message.getIsClosedByAdminForce() == 1),
			(message.getIsCheckedByUser() == 1),
			(message.getIsCheckedByAdmin() == 1),
			message.getDateOfAdd(),
			message.getDateOfUpdate()
		);
	}
	
	public ArrayList<Message> listForUser(int userId, int disciplineId, int lessonId){
		
		
		
		return null;
	}
}
