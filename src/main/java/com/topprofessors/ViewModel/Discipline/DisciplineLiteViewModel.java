package com.topprofessors.ViewModel.Discipline;

public class DisciplineLiteViewModel {

	private int id;
	private String name;
	
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
	public DisciplineLiteViewModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
