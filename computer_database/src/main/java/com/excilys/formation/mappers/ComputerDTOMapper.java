package com.excilys.formation.mappers;

import java.time.LocalDate;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Computer;

public class ComputerDTOMapper {
	
	public static Computer dtoToComputer (ComputerDTO computerDTO) {
		Computer computer = new Computer();
		if(computerDTO.getIntroduced() == null ) {
			System.out.println("pouet B");
			computer = new Computer(					
					computerDTO.getName(),
					computerDTO.getCompanyDTO().getId());
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
		}
		else if(computerDTO.getDiscontinued() == null) {
			System.out.println("pouet A");
			computer = new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					computerDTO.getCompanyDTO().getId());
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
		}
		else {
			System.out.println("pouet");
			computer =new Computer(
					
					computerDTO.getName(),
					LocalDate.parse(computerDTO.getIntroduced()),
					LocalDate.parse(computerDTO.getDiscontinued()),
					computerDTO.getCompanyDTO().getId());
			computer.setId(Integer.parseInt(computerDTO.getId()));
			return computer;
			
		}
			
	
		
	}
	
	public static ComputerDTO computerToDTO (Computer computer) {
		ComputerDTO computerDto = new ComputerDTO();
		
		computerDto.setName(computer.getName());
		computerDto.setId(String.valueOf(computer.getId()));
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
