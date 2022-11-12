package com.topprofessors.ViewModel.Course;

import java.util.ArrayList;

import com.topprofessors.Model.Course.CourseStatusTypeForUser;
import com.topprofessors.ViewModel.Discipline.DisciplineLiteViewModel;

public class CourseInfoViewModel {

	private int id;
	private String name;
	private String description;
	private int active;
	private String posterSrc;
	private CourseStatusTypeForUser status;
	
	private ArrayList<DisciplineLiteViewModel> disciplineLiteViewModels;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getPosterSrc() {
		return posterSrc;
	}
	public void setPosterSrc(String posterSrc) {
		this.posterSrc = posterSrc;
	}
	public CourseStatusTypeForUser getStatus() {
		return status;
	}
	public void setStatus(CourseStatusTypeForUser status) {
		this.status = status;
	}

	
	public ArrayList<DisciplineLiteViewModel> getDisciplineLiteViewModels() {
		return disciplineLiteViewModels;
	}
	public void setDisciplineLiteViewModels(ArrayList<DisciplineLiteViewModel> disciplineLiteViewModels) {
		this.disciplineLiteViewModels = disciplineLiteViewModels;
	}
	public CourseInfoViewModel(int id, String name, String description, int active, String posterSrc,
			CourseStatusTypeForUser status, ArrayList<DisciplineLiteViewModel> disciplineLiteViewModels) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.posterSrc = posterSrc;
		this.status = status;
		this.disciplineLiteViewModels = disciplineLiteViewModels;
	}

	

	
	
	
}
