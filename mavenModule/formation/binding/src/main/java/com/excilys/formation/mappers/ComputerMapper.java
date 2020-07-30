package com.excilys.formation.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class ComputerMapper {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
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
	public static ComputerDTO toComputerDTO(Computer computer) {
        ComputerDTO dto = new ComputerDTO();
        try {
            dto.setId(String.valueOf(computer.getId()));
            dto.setName(computer.getName());
            if (computer.getDateInt() == null) {
                dto.setIntroduced("");
            } else {
                dto.setIntroduced(computer.getDateInt().toString());
            }
            if (computer.getDateDisc() == null) {
                dto.setDiscontinued("");
            } else {
                dto.setDiscontinued(computer.getDateDisc().toString());
            }
            if (computer.getCompany() != null) {
                dto.setCompanyDTO(CompanyMapper.toCompanyDTO(computer.getCompany()));
            }
        } catch (RuntimeException e) {
            logger.error("error when converting a computerDTO to a computer");
        }
        return dto;
    }

}
