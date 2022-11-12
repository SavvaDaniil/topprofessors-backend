package com.topprofessors.ViewModel.User;

import java.util.Date;
import java.util.List;

import com.topprofessors.ViewModel.Region.RegionLiteViewModel;

public class UserProfileViewModel {

	private int id;
	private String username;
	private String secondname;
	private String firstname;
	private String patronymic;
	private String datebirthday;
	private RegionLiteViewModel regionLiteViewModel;

	private List<RegionLiteViewModel> regionLiteViewModels;
	
	private String nationality;
	private String address;
	private String addressindex;
	private String snils;
	private String telephone;
	private int education;
	private String specialization;

    private String placework;
	private String office;
	
	private int agreement;
	private String photoSrc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getDatebirthday() {
		return datebirthday;
	}
	public void setDatebirthday(String datebirthday) {
		this.datebirthday = datebirthday;
	}
	public RegionLiteViewModel getRegionLiteViewModel() {
		return regionLiteViewModel;
	}
	public void setRegionLiteViewModel(RegionLiteViewModel regionLiteViewModel) {
		this.regionLiteViewModel = regionLiteViewModel;
	}
	
	public List<RegionLiteViewModel> getRegionLiteViewModels() {
		return regionLiteViewModels;
	}
	public void setRegionLiteViewModels(List<RegionLiteViewModel> regionLiteViewModels) {
		this.regionLiteViewModels = regionLiteViewModels;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressindex() {
		return addressindex;
	}
	public void setAddressindex(String addressindex) {
		this.addressindex = addressindex;
	}
	public String getSnils() {
		return snils;
	}
	public void setSnils(String snils) {
		this.snils = snils;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getPlacework() {
		return placework;
	}
	public void setPlacework(String placework) {
		this.placework = placework;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public int getAgreement() {
		return agreement;
	}
	public void setAgreement(int agreement) {
		this.agreement = agreement;
	}
	
	public String getPhotoSrc() {
		return photoSrc;
	}
	public void setPhotoSrc(String photoSrc) {
		this.photoSrc = photoSrc;
	}
	public UserProfileViewModel(int id, String username, String secondname, String firstname, String patronymic,
			String datebirthday, RegionLiteViewModel regionLiteViewModel, String nationality, String address,
			String addressindex, String snils, String telephone, int education, String specialization, String placework,
			String office, int agreement, String photoSrc) {
		super();
		this.id = id;
		this.username = username;
		this.secondname = secondname;
		this.firstname = firstname;
		this.patronymic = patronymic;
		this.datebirthday = datebirthday;
		this.regionLiteViewModel = regionLiteViewModel;
		this.nationality = nationality;
		this.address = address;
		this.addressindex = addressindex;
		this.snils = snils;
		this.telephone = telephone;
		this.education = education;
		this.specialization = specialization;
		this.placework = placework;
		this.office = office;
		this.agreement = agreement;
		this.photoSrc = photoSrc;
	}


	


}
