package com.topprofessors.ViewModel.Course;

import java.util.ArrayList;

import com.topprofessors.ViewModel.Discipline.DisciplineStatForUserViewModel;

public class CourseStatForUserViewModel {

	private int id;
	private String name;
	private boolean isDone;
	private ArrayList<DisciplineStatForUserViewModel> disciplineStatForUserViewModels;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public ArrayList<DisciplineStatForUserViewModel> getDisciplineStatForUserViewModels() {
		return disciplineStatForUserViewModels;
	}
	
	public void setDisciplineStatForUserViewModels(
			ArrayList<DisciplineStatForUserViewModel> disciplineStatForUserViewModels) {
		this.disciplineStatForUserViewModels = disciplineStatForUserViewModels;
	}
	
	public CourseStatForUserViewModel(int id, String name, boolean isDone,
			ArrayList<DisciplineStatForUserViewModel> disciplineStatForUserViewModels) {
		super();
		this.id = id;
		this.name = name;
		this.isDone = isDone;
		this.disciplineStatForUserViewModels = disciplineStatForUserViewModels;
	}
	
}
