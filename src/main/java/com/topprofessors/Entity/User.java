package com.topprofessors.Entity;

import java.sql.Date;
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
import javax.persistence.Transient;


//@Entity
//@Table(name = "user")
public class User {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@Column(name="username", nullable=true, columnDefinition="varchar(256)")
    private String username;

	//@Column(name="password", nullable=true, columnDefinition="varchar(256)")
    private String password;

	//@Column(name="auth_key", nullable=true, columnDefinition="varchar(256)")
    private String authKey;

	//@Column(name="access_token", nullable=true, columnDefinition="varchar(256)")
    private String accessToken;

	//@Column(name="active", nullable=false, columnDefinition="int(1) default 0")
    private int active;


	//@Column(name="dateOfAdd", nullable=true, columnDefinition="date")
    private Timestamp dateOfAdd;
	
	//@Column(name="secondname", nullable=true, columnDefinition="varchar(256)")
    private String secondname;

	//@Column(name="firstname", nullable=true, columnDefinition="varchar(256)")
    private String firstname;

	//@Column(name="patronymic", nullable=true, columnDefinition="varchar(256)")
    private String patronymic;

	//@Column(name="datebirthday", nullable=true, columnDefinition="date")
    private Date datebirthday;

	//@Column(name="placebirthday", nullable=true, columnDefinition="varchar(256)")
    private String placebirthday;

	//@Column(name="nationality", nullable=true, columnDefinition="varchar(256)")
    private String nationality;

	//@Column(name="document", nullable=true, columnDefinition="varchar(256)")
    private String document;

	//@Column(name="whendocument", nullable=true, columnDefinition="varchar(256)")
    private String whendocument;

	//@Column(name="address", nullable=true, columnDefinition="varchar(256)")
    private String address;

	//@Column(name="addressindex", nullable=true, columnDefinition="varchar(256)")
    private String addressindex;

	//@Column(name="snils", nullable=true, columnDefinition="varchar(256)")
    private String snils;
	
	//@OneToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "region_id", referencedColumnName = "id")
    //private Region region;
	
	@Transient
	private int regionId;

	//@Column(name="telephone", nullable=true, columnDefinition="varchar(256)")
    private String telephone;

	//@Column(name="education", columnDefinition="int(1)")
    private int education;

	//@Column(name="placeeducation", nullable=true, columnDefinition="varchar(256)")
    private String placeeducation;

	//@Column(name="yeareducation", nullable=true, columnDefinition="varchar(256)")
    private String yeareducation;

	//@Column(name="diplom", nullable=true, columnDefinition="varchar(256)")
    private String diplom;

	//@Column(name="specialization", nullable=true, columnDefinition="varchar(512)")
    private String specialization;

	//@Column(name="placework", nullable=true, columnDefinition="varchar(512)")
    private String placework;

	//@Column(name="office", nullable=true, columnDefinition="varchar(512)")
    private String office;

	//@Column(name="uraddress", nullable=true, columnDefinition="varchar(512)")
    private String uraddress;

	//@Column(name="organizationemail", nullable=true, columnDefinition="varchar(512)")
    private String organizationemail;

	//@Column(name="organizationtelfax", nullable=true, columnDefinition="varchar(512)")
    private String organizationtelfax;

	//@Column(name="agreement", nullable=false, columnDefinition="int(1) default 0")
    private int agreement;

	//@Column(name="statusROP", nullable=false, columnDefinition="int(1) default 0")
    private int statusROP;

	//@Column(name="granted_zayavki_courses", nullable=false, columnDefinition="int(1) default 0")
    private int grantedZayavkiCourses;
    
    private Timestamp forgetLastTry;
	//@Column(name="forget_code", nullable=true, columnDefinition="varchar(6)")
    private String forgetCode;
    
    private int forgetTryCount;
    

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Timestamp getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(Timestamp dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
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

	public Date getDatebirthday() {
		return datebirthday;
	}

	public void setDatebirthday(Date datebirthday) {
		this.datebirthday = datebirthday;
	}

	public String getPlacebirthday() {
		return placebirthday;
	}

	public void setPlacebirthday(String placebirthday) {
		this.placebirthday = placebirthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getWhendocument() {
		return whendocument;
	}

	public void setWhendocument(String whendocument) {
		this.whendocument = whendocument;
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

	/*
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	*/

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
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

	public String getPlaceeducation() {
		return placeeducation;
	}

	public void setPlaceeducation(String placeeducation) {
		this.placeeducation = placeeducation;
	}

	public String getYeareducation() {
		return yeareducation;
	}

	public void setYeareducation(String yeareducation) {
		this.yeareducation = yeareducation;
	}

	public String getDiplom() {
		return diplom;
	}

	public void setDiplom(String diplom) {
		this.diplom = diplom;
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

	public String getUraddress() {
		return uraddress;
	}

	public void setUraddress(String uraddress) {
		this.uraddress = uraddress;
	}

	public String getOrganizationemail() {
		return organizationemail;
	}

	public void setOrganizationemail(String organizationemail) {
		this.organizationemail = organizationemail;
	}

	public String getOrganizationtelfax() {
		return organizationtelfax;
	}

	public void setOrganizationtelfax(String organizationtelfax) {
		this.organizationtelfax = organizationtelfax;
	}

	public int getAgreement() {
		return agreement;
	}

	public void setAgreement(int agreement) {
		this.agreement = agreement;
	}

	public int getStatusROP() {
		return statusROP;
	}

	public void setStatusROP(int statusROP) {
		this.statusROP = statusROP;
	}

	public int getGrantedZayavkiCourses() {
		return grantedZayavkiCourses;
	}

	public void setGrantedZayavkiCourses(int grantedZayavkiCourses) {
		this.grantedZayavkiCourses = grantedZayavkiCourses;
	}

	public Timestamp getForgetLastTry() {
		return forgetLastTry;
	}

	public void setForgetLastTry(Timestamp forgetLastTry) {
		this.forgetLastTry = forgetLastTry;
	}

	public String getForgetCode() {
		return forgetCode;
	}

	public void setForgetCode(String forgetCode) {
		this.forgetCode = forgetCode;
	}

	public int getForgetTryCount() {
		return forgetTryCount;
	}

	public void setForgetTryCount(int forgetTryCount) {
		this.forgetTryCount = forgetTryCount;
	}

	
	
	
}
