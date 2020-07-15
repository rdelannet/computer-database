package com.excilys.formation.mappers;

import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class ComputerDTOMapper {
	
	public static Computer dtoToComputer (ComputerDTO computerDTO) {
		Computer computer = new Computer();
		if(computerDTO.getIntroduced() == null ) {
			
			computer = new Computer(					
					computerDTO.getName(),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
		}
		else if(computerDTO.getDiscontinued() == null) {
			
			computer = new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
		}
		else {
			
			computer =new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					LocalDate.parse(computerDTO.getDiscontinued()),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
			
		}
			
	
		
	}
	public static Computer dtoToComputerC (ComputerDTO computerDTO) {
		Computer computer = new Computer();
		if(computerDTO.getIntroduced() == null ) {
			
			computer = new Computer(					
					computerDTO.getName(),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			
			return computer;
		}
		else if(computerDTO.getDiscontinued() == null) {
			
			computer = new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			
			return computer;
		}
		else {
			
			computer =new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					LocalDate.parse(computerDTO.getDiscontinued()),
					new Company(computerDTO.getCompanyDTO().getId(),computerDTO.getCompanyDTO().getName()));
			
			return computer;
			
		}
			
	
		
	}
	
	public static ComputerDTO computerToDTO (Computer computer)  {
		ComputerDTO computerDto = new ComputerDTO();
		
		computerDto.setName(computer.getName());
		computerDto.setId(String.valueOf(computer.getId()));
		if(computer.getDateInt() != null) {
			computerDto.setIntroduced(computer.getDateInt().toString());
		}
		if(computer.getDateDisc() != null) {
			computerDto.setDiscontinued(computer.getDateDisc().toString());
		}
		if(computer.getCompany() != null ) {
			computerDto.setCompanyDTO(new CompanyDTO(computer.getCompany()));
		}
		
		return computerDto;
	}			
}
