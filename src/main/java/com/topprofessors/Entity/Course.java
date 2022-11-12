package com.topprofessors.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name ="course")
public class Course {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;
	
	//@Column(name="name", nullable=true, columnDefinition="varchar(512)")
    private String name;
	
	//@Column(name="description", nullable=true, columnDefinition="text")
    private String description;

	//@Column(name="active", nullable=false, columnDefinition="int(1) default 0")
	private int active;

	//@Column(name="is_visible", nullable=false, columnDefinition="int(1) default 0")
	private int isVisible;

	//@Column(name="is_ready", nullable=false, columnDefinition="int(1) default 0")
	private int is_ready;

	//@Column(name="dateOfAdd", nullable=true, columnDefinition="date")
	private Timestamp dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="date")
	private Timestamp dateOfUpdate;

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

	public int getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}

	public int getIs_ready() {
		return is_ready;
	}

	public void setIs_ready(int is_ready) {
		this.is_ready = is_ready;
	}

	public Timestamp getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(Timestamp dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}

	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	
	
}
