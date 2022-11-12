package com.topprofessors.ViewModel.Course;

import java.util.ArrayList;

import com.topprofessors.Model.Course.CourseStatusTypeForUser;
import com.topprofessors.ViewModel.Discipline.DisciplineResultOfUserLiteViewModel;

public class CourseResultOfUserLiteViewModel {

	private int id;
	private String name;
	
	private CourseStatusTypeForUser status;
	private ArrayList<DisciplineResultOfUserLiteViewModel> disciplineResultOfUserLiteViewModels;
	
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
	public CourseStatusTypeForUser getStatus() {
		return status;
	}
	public void setStatus(CourseStatusTypeForUser status) {
		this.status = status;
	}
	public ArrayList<DisciplineResultOfUserLiteViewModel> getDisciplineResultOfUserLiteViewModels() {
		return disciplineResultOfUserLiteViewModels;
	}
	public void setDisciplineResultOfUserLiteViewModels(
			ArrayList<DisciplineResultOfUserLiteViewModel> disciplineResultOfUserLiteViewModels) {
		this.disciplineResultOfUserLiteViewModels = disciplineResultOfUserLiteViewModels;
	}
	
	public CourseResultOfUserLiteViewModel(int id, String name, CourseStatusTypeForUser status,
			ArrayList<DisciplineResultOfUserLiteViewModel> disciplineResultOfUserLiteViewModels) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.disciplineResultOfUserLiteViewModels = disciplineResultOfUserLiteViewModels;
	}
	
	
}
