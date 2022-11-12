package com.topprofessors.Entity;

import java.sql.Timestamp;

//@Entity
//@Table(name = "online_auditorium_data")
public class OnlineAuditoriumData {


	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	////@Column(name="id", columnDefinition="int(11)")
	private int id;

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "userIdFrom", referencedColumnName = "id")
	//private User userFrom;
	private String name;
	
	private String str_value;
	private int int_value;
	private Timestamp timestamp_value;
	
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
	public String getStr_value() {
		return str_value;
	}
	public void setStr_value(String str_value) {
		this.str_value = str_value;
	}
	public int getInt_value() {
		return int_value;
	}
	public void setInt_value(int int_value) {
		this.int_value = int_value;
	}
	public Timestamp getTimestamp_value() {
		return timestamp_value;
	}
	public void setTimestamp_value(Timestamp timestamp_value) {
		this.timestamp_value = timestamp_value;
	}
	
}
