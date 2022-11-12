package com.topprofessors.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "lesson")
public class Lesson {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;
	
	//@Column(name="name", nullable=true, columnDefinition="varchar(512)")
    private String name;

	//@Column(name="number_of_lesson", nullable=false, columnDefinition="int(2) default 0")
	private int numberOfLesson;

	//@Column(name="video_type", nullable=false, columnDefinition="int(1) default 0")
	private int videoType;
	
	//@Column(name="video_youtube_link", nullable=true, columnDefinition="varchar(256)")
    private String videoYoutubeLink;

	//@Column(name="hash", nullable=true, columnDefinition="varchar(64)")
    private String hash;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "disciplineId", referencedColumnName = "id")
	//private Discipline discipline;
    private int disciplineId;

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

	public int getNumberOfLesson() {
		return numberOfLesson;
	}

	public void setNumberOfLesson(int numberOfLesson) {
		this.numberOfLesson = numberOfLesson;
	}

	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	/*
	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	*/


	public int getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
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

	public String getVideoYoutubeLink() {
		return videoYoutubeLink;
	}

	public void setVideoYoutubeLink(String videoYoutubeLink) {
		this.videoYoutubeLink = videoYoutubeLink;
	}
	
	
}
