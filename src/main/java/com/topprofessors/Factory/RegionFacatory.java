package com.topprofessors.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topprofessors.Entity.Region;

public class RegionFacatory {

	public Region createFromResultSet(ResultSet resultSet) {
		if(resultSet == null)return null;
		try {
			Region region = new Region();
			region.setId(resultSet.getInt("id"));
			region.setName(resultSet.getString("name"));
			return region;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
