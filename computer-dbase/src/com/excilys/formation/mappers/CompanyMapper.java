package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;

public class CompanyMapper  extends AbstractMapper<Company>{

	@Override
	public Company resultToObject(ResultSet result) throws SQLException {
		Company company = null;
		if(result.first()) {
			company = new Company(result.getInt("id"), result.getString("name"));
		}
		return company;
	}

	@Override
	public List<Company> resultToList(ResultSet result) throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		while(result.next()){
			companies.add(new Company(result.getInt("id"), result.getString("name")));
		}
		
		return companies;
	}


}
