package com.topprofessors.Entity;

import java.sql.Timestamp;

//@Entity
//@Table(name = "admin")
public class Admin {

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

	//@Column(name="level", nullable=false, columnDefinition="int(1) default 0")
    private int level;
    
    private Timestamp dateOfAdd;

    private String firstname;
    private String position;
    private int main_0_rop_1;
    
    private int region_id;

    private int panel_admins;
    private int panel_users;
    private int panel_messages;
    private int panel_content;
    private int panel_old_users;
    private int panel_content_dangers;
    private int panel_media_manager;
    private int access_of_media_manager;
    private int access_to_delete_user;
    private int panel_analysis_discipline;
    private int panel_analytics;
    private int panel_online_audition;
    private int panel_notice;
    
    
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Timestamp getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Timestamp dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getMain_0_rop_1() {
		return main_0_rop_1;
	}
	public void setMain_0_rop_1(int main_0_rop_1) {
		this.main_0_rop_1 = main_0_rop_1;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getPanel_admins() {
		return panel_admins;
	}
	public void setPanel_admins(int panel_admins) {
		this.panel_admins = panel_admins;
	}
	public int getPanel_users() {
		return panel_users;
	}
	public void setPanel_users(int panel_users) {
		this.panel_users = panel_users;
	}
	public int getPanel_messages() {
		return panel_messages;
	}
	public void setPanel_messages(int panel_messages) {
		this.panel_messages = panel_messages;
	}
	public int getPanel_content() {
		return panel_content;
	}
	public void setPanel_content(int panel_content) {
		this.panel_content = panel_content;
	}
	public int getPanel_old_users() {
		return panel_old_users;
	}
	public void setPanel_old_users(int panel_old_users) {
		this.panel_old_users = panel_old_users;
	}
	public int getPanel_content_dangers() {
		return panel_content_dangers;
	}
	public void setPanel_content_dangers(int panel_content_dangers) {
		this.panel_content_dangers = panel_content_dangers;
	}
	public int getPanel_media_manager() {
		return panel_media_manager;
	}
	public void setPanel_media_manager(int panel_media_manager) {
		this.panel_media_manager = panel_media_manager;
	}
	public int getAccess_of_media_manager() {
		return access_of_media_manager;
	}
	public void setAccess_of_media_manager(int access_of_media_manager) {
		this.access_of_media_manager = access_of_media_manager;
	}
	public int getAccess_to_delete_user() {
		return access_to_delete_user;
	}
	public void setAccess_to_delete_user(int access_to_delete_user) {
		this.access_to_delete_user = access_to_delete_user;
	}
	public int getPanel_analysis_discipline() {
		return panel_analysis_discipline;
	}
	public void setPanel_analysis_discipline(int panel_analysis_discipline) {
		this.panel_analysis_discipline = panel_analysis_discipline;
	}
	public int getPanel_analytics() {
		return panel_analytics;
	}
	public void setPanel_analytics(int panel_analytics) {
		this.panel_analytics = panel_analytics;
	}
	public int getPanel_online_audition() {
		return panel_online_audition;
	}
	public void setPanel_online_audition(int panel_online_audition) {
		this.panel_online_audition = panel_online_audition;
	}
	public int getPanel_notice() {
		return panel_notice;
	}
	public void setPanel_notice(int panel_notice) {
		this.panel_notice = panel_notice;
	}
    
    
}
