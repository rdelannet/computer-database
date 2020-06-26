package com.excilys.formation.mappers;

import java.time.LocalDate;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Computer;

public class ComputerDTOMapper {
	
	public static Computer dtoToComputer (ComputerDTO computerDTO) {
		return new Computer(
			Integer.valueOf(computerDTO.getId()),
			computerDTO.getName(),
			LocalDate.parse(computerDTO.getIntroduced()),
			LocalDate.parse(computerDTO.getDiscontinued()),
			Integer.parseInt(computerDTO.getCompanyDTO().getId()));
	}
	
	public static ComputerDTO computerToDTO (Computer computer) {
		return new ComputerDTO(
			Integer.valueOf(computer.getId()).toString(),
			computer.getName(),
			computer.getDateInt().toString(),
			computer.getDateDisc().toString(),
			new CompanyDTO(Integer.valueOf(computer.getId()).toString(), computer.getName()));
	}			
}
