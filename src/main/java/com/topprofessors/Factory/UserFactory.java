package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.User;

public class UserFactory {

	public User createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setAuthKey(resultSet.getString("auth_key"));
			user.setAccessToken(resultSet.getString("access_token"));
			user.setActive(resultSet.getInt("active"));
			user.setSecondname(resultSet.getString("secondname"));
			user.setFirstname(resultSet.getString("firstname"));
			user.setPatronymic(resultSet.getString("patronymic"));
			user.setDatebirthday(resultSet.getDate("datebirthday"));
			user.setPlacebirthday(resultSet.getString("placebirthday"));
			user.setNationality(resultSet.getString("nationality"));
			user.setDocument(resultSet.getString("document"));
			user.setWhendocument(resultSet.getString("whendocument"));
			user.setAddress(resultSet.getString("address"));
			user.setAddressindex(resultSet.getString("addressindex"));
			user.setSnils(resultSet.getString("snils"));
			
			//user.XXXXXXXXXXXXXXXXX(resultSet.getString("region_id"));
			user.setRegionId(resultSet.getInt("region_id"));
			
			user.setTelephone(resultSet.getString("telephone"));
			user.setEducation(resultSet.getInt("education"));
			user.setPlaceeducation(resultSet.getString("placeeducation"));
			user.setYeareducation(resultSet.getString("yeareducation"));
			user.setDiplom(resultSet.getString("diplom"));
			user.setSpecialization(resultSet.getString("specialization"));
			user.setPlacework(resultSet.getString("placework"));
			user.setOffice(resultSet.getString("office"));
			user.setUraddress(resultSet.getString("uraddress"));
			user.setOrganizationemail(resultSet.getString("organizationemail"));
			user.setOrganizationtelfax(resultSet.getString("organizationtelfax"));
			user.setAgreement(resultSet.getInt("agreement"));
			user.setStatusROP(resultSet.getInt("status_rop"));
			user.setGrantedZayavkiCourses(resultSet.getInt("granted_zayavki_courses"));
			user.setDateOfAdd(resultSet.getTimestamp("date_of_add"));

			user.setForgetLastTry(resultSet.getTimestamp("forget_last_try"));
			user.setForgetCode(resultSet.getString("forget_code"));
			user.setForgetTryCount(resultSet.getInt("forget_try_count"));
			
			
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
