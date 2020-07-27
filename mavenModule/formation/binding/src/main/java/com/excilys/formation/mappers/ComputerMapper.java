package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class ComputerMapper {
	
	
	public static Computer resultToObject(ResultSet result) throws SQLException {
		Computer computer = null;
		
		if(result.first()) {
			
			computer = new Computer(result.getString("name"));
			computer.setId(result.getInt("id"));
			if(result.getDate("introduced") != null) {
				computer.setDateInt(result.getDate("introduced").toLocalDate());
			}
			if(result.getDate("discontinued") != null) {
				computer.setDateDisc(result.getDate("discontinued").toLocalDate());
			}
			if(result.getInt("company_id") != 0) {
				computer.setCompany(new Company(result.getInt("company_id"),result.getString("c.name")));
			}
		}
		return computer;
	}

	
	public static Computer resultToList(ResultSet result) throws SQLException {
		//List<Computer> computers = new ArrayList<Computer>();
		
			Computer computer = new Computer();
			
			computer.setName(result.getString("name"));
			computer.setId(result.getInt("id"));
			if(result.getDate("introduced") != null) {
				
				computer.setDateInt(result.getDate("introduced").toLocalDate());
			}
			if(result.getDate("discontinued") != null) {
				
				computer.setDateDisc(result.getDate("discontinued").toLocalDate());
			}
			if(result.getInt("company_id") != 0) {
				computer.setCompany(new Company(result.getInt("company_id"),result.getString("c.name")));
			}
			
		return computer;
	}

}
