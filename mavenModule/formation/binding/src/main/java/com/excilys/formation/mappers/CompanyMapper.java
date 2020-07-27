package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;

public class CompanyMapper  {

	
	public static Company resultToObject(ResultSet result) throws SQLException {
		Company company = null;
		if(result.first()) {
			company = new Company(result.getInt("id"), result.getString("name"));
		}
		return company;
	}


	public static Company resultToList(ResultSet result) throws SQLException {
		
		
		return new Company(result.getInt("id"), result.getString("name"));
		
		
		
	}


}
