package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.model.Company;


public class MapperRowCompany implements RowMapper<Company>  {

	@Override
	public Company mapRow(ResultSet result,int numRow) throws SQLException{
		Company company = new Company();
		company.setId(result.getInt("id"));
		company.setName(result.getString("name"));
		
		return company;
	}

}
