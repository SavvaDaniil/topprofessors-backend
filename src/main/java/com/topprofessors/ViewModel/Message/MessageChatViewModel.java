package com.topprofessors.ViewModel.Message;

import java.util.ArrayList;

import com.topprofessors.Abstract.ViewModel.MessageAbstractViewModel;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;

public class MessageChatViewModel {

	private DisciplineLiteViewModel disciplineLiteViewModel;
	private MessageParentViewModel messageParentViewModel;
	private ArrayList<MessageAbstractViewModel> messageViewModels;
	
	public DisciplineLiteViewModel getDisciplineLiteViewModel() {
		return disciplineLiteViewModel;
	}
	public void setDisciplineLiteViewModel(DisciplineLiteViewModel disciplineLiteViewModel) {
		this.disciplineLiteViewModel = disciplineLiteViewModel;
	}
	public MessageParentViewModel getMessageParentViewModel() {
		return messageParentViewModel;
	}
	public void setMessageParentViewModel(MessageParentViewModel messageParentViewModel) {
		this.messageParentViewModel = messageParentViewModel;
	}
	public ArrayList<MessageAbstractViewModel> getMessageViewModels() {
		return messageViewModels;
	}
	public void setMessageViewModels(ArrayList<MessageAbstractViewModel> messageViewModels) {
		this.messageViewModels = messageViewModels;
	}
	
	public MessageChatViewModel(DisciplineLiteViewModel disciplineLiteViewModel,
			MessageParentViewModel messageParentViewModel, ArrayList<MessageAbstractViewModel> messageViewModels) {
		super();
		this.disciplineLiteViewModel = disciplineLiteViewModel;
		this.messageParentViewModel = messageParentViewModel;
		this.messageViewModels = messageViewModels;
	}

	
	
}
