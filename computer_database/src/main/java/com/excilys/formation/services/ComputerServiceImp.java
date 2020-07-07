package com.excilys.formation.services;
import java.sql.Connection;
import java.util.List;

import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

public class ComputerServiceImp implements ComputerService {
	
	private  ComputerDAO computerDao;
	
	
	public ComputerServiceImp(ComputerDAO comp) {
		this.computerDao = comp;
	}

	@Override
	public List<Computer> getAllComputer() {
		return computerDao.findAll();
	}

	@Override
	public Computer getComputer(Integer id) {
		
		return computerDao.find(id);
	}

	@Override
	public boolean updateComputer(Computer computer) {
		
		return computerDao.update(computer);
	}

	@Override
	public boolean createComputer(Computer computer) {
		
		return computerDao.create(computer);
	}

	@Override
	public boolean deleteComputer(Computer computer) {
		return computerDao.delete(computer);
		
	}
	@Override
	public List<Computer> getComputersByPage(Page page) {
		Integer offset = (page.getCurrentPage()-1)*page.getItemsByPage();
		return computerDao.findAllPages(offset, page.getItemsByPage());
	}
	
	@Override
	public List<Computer> getComputersSearchByPage(Page page,String search) {
		Integer offset = (page.getCurrentPage()-1)*page.getItemsByPage();
		return computerDao.findBySearch(offset, page.getItemsByPage(), search);
	}

	@Override
	public Integer getNbComputers() {
		
		return computerDao.findMaxElement();
	}

	public List<Computer> getComputersOrderByPage(Page page,String order,String ascending) {
		Integer offset = (page.getCurrentPage()-1)*page.getItemsByPage();
		return computerDao.findOrder(offset, page.getItemsByPage(),order,ascending);
	}
	public Integer getComputersNbPages(Page page) {
		Integer nbEntries = computerDao.findMaxElement();
		Integer nbPages = nbEntries/page.getItemsByPage();
		return nbEntries%page.getItemsByPage() == 0?nbPages:nbPages+1;
	}

	

	
	
	
}
