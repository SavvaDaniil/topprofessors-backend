package com.topprofessors.ViewModel.Message;

import java.util.Date;

import com.topprofessors.Abstract.ViewModel.MessageAbstractViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;
import com.topprofessors.ViewModel.User.UserLiteViewModel;

public class MessageFromUserViewModel extends MessageAbstractViewModel {

	private UserLiteViewModel userLiteViewModel;
	
	public UserLiteViewModel getUserLiteViewModel() {
		return userLiteViewModel;
	}
	public void setUserLiteViewModel(UserLiteViewModel userLiteViewModel) {
		this.userLiteViewModel = userLiteViewModel;
	}


	
	public MessageFromUserViewModel(int id, int fromWhoId, int toWhoId, DisciplineLiteViewModel disciplineLiteViewModel,
			String messageContent, boolean isReaded, Date dateOfAdd, Date dateOfUpdate,
			UserLiteViewModel userLiteViewModel) {
		super(id, fromWhoId, toWhoId, disciplineLiteViewModel, messageContent, isReaded, dateOfAdd, dateOfUpdate);
		this.userLiteViewModel = userLiteViewModel;
	}




	
}
