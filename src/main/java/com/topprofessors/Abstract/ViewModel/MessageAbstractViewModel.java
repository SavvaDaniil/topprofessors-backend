package com.topprofessors.Abstract.ViewModel;

import java.util.Date;

import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;

public abstract class MessageAbstractViewModel {

	private int id;
	private int fromWhoId;
	private int toWhoId;
	private DisciplineLiteViewModel disciplineLiteViewModel;
	
	private String messageContent;
	private boolean isReaded;
	
	private Date dateOfAdd;
	private Date dateOfUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromWhoId() {
		return fromWhoId;
	}
	public void setFromWhoId(int fromWhoId) {
		this.fromWhoId = fromWhoId;
	}
	public int getToWhoId() {
		return toWhoId;
	}
	public void setToWhoId(int toWhoId) {
		this.toWhoId = toWhoId;
	}
	public DisciplineLiteViewModel getDisciplineLiteViewModel() {
		return disciplineLiteViewModel;
	}
	public void setDisciplineLiteViewModel(DisciplineLiteViewModel disciplineLiteViewModel) {
		this.disciplineLiteViewModel = disciplineLiteViewModel;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public boolean getIsReaded() {
		return isReaded;
	}
	public void setIsReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}
	public Date getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public Date getDateOfUpdate() {
		return dateOfUpdate;
	}
	public void setDateOfUpdate(Date dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}
	
	public MessageAbstractViewModel(int id, int fromWhoId, int toWhoId, DisciplineLiteViewModel disciplineLiteViewModel,
			String messageContent, boolean isReaded, Date dateOfAdd, Date dateOfUpdate) {
		super();
		this.id = id;
		this.fromWhoId = fromWhoId;
		this.toWhoId = toWhoId;
		this.disciplineLiteViewModel = disciplineLiteViewModel;
		this.messageContent = messageContent;
		this.isReaded = isReaded;
		this.dateOfAdd = dateOfAdd;
		this.dateOfUpdate = dateOfUpdate;
	}



	
	
}
