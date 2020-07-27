package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperRowComputer implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet result,int numRow) throws SQLException{
		Computer computer = new Computer();
		computer.setId(result.getInt("computer.id"));
		computer.setName(result.getString("computer.name"));
		if(result.getDate("introduced") != null) {
			computer.setDateInt(result.getDate("introduced").toLocalDate());
		}
		
		if(result.getDate("discontinued") != null) {
			computer.setDateDisc(result.getDate("discontinued").toLocalDate());
		}
		
		if(result.getInt("company_id") != 0) {
			Company company = new Company(result.getInt("company_id"),result.getString("c.name"));
			computer.setCompany(company);
		}
		return computer;
	}

}
