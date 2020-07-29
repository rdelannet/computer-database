package com.excilys.formation.services;

import java.util.List;

import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

public interface ComputerService {
	
	List<Computer> getAllComputer();
	Computer getComputer(Integer id);
	
	boolean updateComputer(Computer computer);
	
	Computer createComputer(Computer computer);
	
	boolean deleteComputer(int i);
	
	List<Computer> getComputersByPage(Page page);
	
	List<Computer> getComputersSearchByPage(Page page,String searc);
	
	Long getNbComputers();
	
	
	List<Computer> getComputersOrderByPage(Page page,String order,String ascending);
	Long getComputersNbPages(Page page);

}
