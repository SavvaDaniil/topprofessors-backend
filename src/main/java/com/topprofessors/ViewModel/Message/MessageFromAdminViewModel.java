package com.topprofessors.ViewModel.Message;

import java.util.Date;

import com.topprofessors.Abstract.ViewModel.MessageAbstractViewModel;
import com.topprofessors.ViewModel.Admin.AdminLiteViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;

public class MessageFromAdminViewModel  extends MessageAbstractViewModel {

	private AdminLiteViewModel adminLiteViewModel;

	public AdminLiteViewModel getAdminLiteViewModel() {
		return adminLiteViewModel;
	}
	public void setAdminLiteViewModel(AdminLiteViewModel adminLiteViewModel) {
		this.adminLiteViewModel = adminLiteViewModel;
	}
	
	public MessageFromAdminViewModel(int id, int fromWhoId, int toWhoId,
			DisciplineLiteViewModel disciplineLiteViewModel, String messageContent, boolean isReaded, Date dateOfAdd,
			Date dateOfUpdate, AdminLiteViewModel adminLiteViewModel) {
		super(id, fromWhoId, toWhoId, disciplineLiteViewModel, messageContent, isReaded, dateOfAdd, dateOfUpdate);
		this.adminLiteViewModel = adminLiteViewModel;
	}

	
}
