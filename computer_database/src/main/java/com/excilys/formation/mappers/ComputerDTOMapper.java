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
					computerDTO.getCompanyDTO().getId());
	
		
	}
	
	public static ComputerDTO computerToDTO (Computer computer) {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setId(Integer.valueOf(computer.getId()).toString());
		computerDto.setName(computer.getName());
		
		if(computer.getDateInt() != null) {
			computerDto.setIntroduced(computer.getDateInt().toString());
		}
		if(computer.getDateDisc() != null) {
			computerDto.setIntroduced(computer.getDateDisc().toString());
		}
		if(computer.getCompanyId() != null ) {
			computerDto.setCompanyDTO(new CompanyDTO(computer.getCompanyId()));
		}
		
		return computerDto;
	}			
}
