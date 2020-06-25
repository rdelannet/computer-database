package com.excilys.formation.dto;

import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

public class ComputerDTO implements ComputerDTOIn{
	
	private ComputerDAO computers;
	private ConnectDB connect;
	
	public ComputerDTO() {
		this.computers = new ComputerDAO(connect.getConnection());
	}

	@Override
	public List<Computer> getAllComputer() {
		
		return computers.findAll();
	}

	@Override
	public Computer getComputer(Integer id) {
		
		return computers.find(id);
	}

	@Override
	public boolean createComputerDTO(Computer computer) {
		
		return computers.create(computer);
	}

	@Override
	public boolean deleteComputerDTO(Computer computer) {
		
		return computers.delete(computer);
	}

	@Override
	public boolean updateComputerDTO(Computer computer) {
		
		return computers.update(computer);
	}

	@Override
	public Integer getComputerMaxElement() {
		
		return computers.findMaxElement();
	}

	@Override
	public List<Computer> getPageComputer(Page page) {
		
		return computers.findAllPages(page);
	}

}
