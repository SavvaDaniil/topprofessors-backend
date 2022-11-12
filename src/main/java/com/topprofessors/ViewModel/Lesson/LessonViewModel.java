package com.topprofessors.ViewModel.Lesson;

import java.util.ArrayList;

import com.topprofessors.Entity.Discipline;

public class LessonViewModel {

	private int id;
	private String name;
	private int videoType;
	private String videoYoutubeLink;
	private String videoSrc;
	private ArrayList<String> sliders;
	
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
	public int getVideoType() {
		return videoType;
	}
	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}
	public String getVideoYoutubeLink() {
		return videoYoutubeLink;
	}
	public void setVideoYoutubeLink(String videoYoutubeLink) {
		this.videoYoutubeLink = videoYoutubeLink;
	}
	public String getVideoSrc() {
		return videoSrc;
	}
	public void setVideoSrc(String videoSrc) {
		this.videoSrc = videoSrc;
	}
	public ArrayList<String> getSliders() {
		return sliders;
	}
	public void setSliders(ArrayList<String> sliders) {
		this.sliders = sliders;
	}
	public LessonViewModel(int id, String name, int videoType, String videoYoutubeLink, String videoSrc,
			ArrayList<String> sliders) {
		super();
		this.id = id;
		this.name = name;
		this.videoType = videoType;
		this.videoYoutubeLink = videoYoutubeLink;
		this.videoSrc = videoSrc;
		this.sliders = sliders;
	}


	
}
