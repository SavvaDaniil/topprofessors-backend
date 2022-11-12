package com.topprofessors.ViewModel.Message;

import java.util.Date;

import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;

public class MessageParentPreviewViewModel {
	
	private int id;
	private Date dateOfAdd;
	private DisciplineLiteViewModel disciplineLiteViewModel;
	private String textContent;
	private boolean isAnyUnread;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public DisciplineLiteViewModel getDisciplineLiteViewModel() {
		return disciplineLiteViewModel;
	}
	public void setDisciplineLiteViewModel(DisciplineLiteViewModel disciplineLiteViewModel) {
		this.disciplineLiteViewModel = disciplineLiteViewModel;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public boolean getIsAnyUnread() {
		return isAnyUnread;
	}
	public void setIsAnyUnread(boolean isAnyUnread) {
		this.isAnyUnread = isAnyUnread;
	}
	
	
	public MessageParentPreviewViewModel(int id, Date dateOfAdd, DisciplineLiteViewModel disciplineLiteViewModel,
			String textContent, boolean isAnyUnread) {
		super();
		this.id = id;
		this.dateOfAdd = dateOfAdd;
		this.disciplineLiteViewModel = disciplineLiteViewModel;
		this.textContent = textContent;
		this.isAnyUnread = isAnyUnread;
	}

	
	
}
