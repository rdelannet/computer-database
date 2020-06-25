package com.excilys.formation.dto;

import java.util.List;

import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

public interface ComputerDTOIn {
	
	List<Computer> getAllComputer();
	
	Computer getComputer(Integer id);
	
	boolean createComputerDTO(Computer computer);
	
	boolean deleteComputerDTO(Computer computer);
	
	boolean updateComputerDTO(Computer computer);
	
	Integer getComputerMaxElement();
	
	List<Computer> getPageComputer(Page page);
	
	
	
}
