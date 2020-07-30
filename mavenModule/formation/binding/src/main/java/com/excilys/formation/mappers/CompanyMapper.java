package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;

public class CompanyMapper  {
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
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
	public static CompanyDTO toCompanyDTO(Company company) {
		CompanyDTO dto = new CompanyDTO();
		dto.setId(company.getId());
		dto.setName(company.getName());
		return dto;
	}


}
