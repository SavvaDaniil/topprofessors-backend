package com.topprofessors.Entity;

import java.sql.Timestamp;

//@Entity
//@Table(name ="homework")
public class Homework {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;
	
	//@Column(name="name", nullable=true, columnDefinition="varchar(512)")
    private String name;

	//@Column(name="description", nullable=true, columnDefinition="text")
    private String description;
	
	//@Column(name="status", nullable=false, columnDefinition="int(1) default 0")
	private int status;

	//@Column(name="dateOfAdd", nullable=true, columnDefinition="DATETIME")
	private Timestamp dateOfAdd;

	//@Column(name="dateOfUpdate", nullable=true, columnDefinition="DATETIME")
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
