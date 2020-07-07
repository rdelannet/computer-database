package com.excilys.formation.services;

import java.util.List;

import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class CompanyServiceImp implements CompanyService {
	
	private  CompanyDAO companyDao;

	
	public CompanyServiceImp(CompanyDAO comp) {
		this.companyDao = comp;
	}
	
	@Override
	public boolean deleteCompany(Company company) {
		
		return companyDao.delete(company);
	}

	@Override
	public Company getCompany(int id) {
		// TODO Auto-generated method stub
		return companyDao.find(id);
	}

	@Override
	public List<Company> getAllCompany() {
		
		return companyDao.findAll();
	}

	@Override
	public int getNbCompany() {
		
		return companyDao.findMaxElement();
	}

	@Override
	public List<Company> getCompanyPages(int offset, int nbPage) {
		
		return companyDao.findAllPages(offset, nbPage);
	}

	@Override
	public Company getCompanyFromComputer(Computer computer) {
		if(computer.getCompany() != null) {
			return companyDao.find(computer.getCompany().getId());
		}
		return null;
	}
	
	

}
