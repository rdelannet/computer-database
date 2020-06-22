package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Computer;

public class ComputerMapper {

	
	public static Computer resultToObject(ResultSet result) throws SQLException {
		Computer computer = null;
		
		if(result.first()) {
			
			computer = new Computer(result.getInt("id"),result.getString("name"));
			if(result.getDate("introduced") != null) {
				computer.setDateInt(result.getDate("introduced").toLocalDate());
			}
			if(result.getDate("discontinued") != null) {
				computer.setDateDisc(result.getDate("discontinued").toLocalDate());
			}
			if(result.getInt("company_id") != 0) {
				computer.setCompanyId(result.getInt("company_id"));
			}
		}
		return computer;
	}

	
	public static List<Computer> resultToList(ResultSet result) throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		while(result.next()) {
			Computer computer = new Computer();
			computer.setId(result.getInt("id"));
			computer.setName(result.getString("name"));
			if(result.getDate("introduced") != null) {
				computer.setDateInt(result.getDate("introduced").toLocalDate());
			}
			if(result.getDate("discontinued") != null) {
				computer.setDateDisc(result.getDate("discontinued").toLocalDate());
			}
			if(result.getInt("company_id") != 0) {
				computer.setCompanyId(result.getInt("company_id"));
			}
			computers.add(computer);
		}
		return computers;
	}

}
