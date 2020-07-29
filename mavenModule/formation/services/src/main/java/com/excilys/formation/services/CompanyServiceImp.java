package com.excilys.formation.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
@Service
public class CompanyServiceImp implements CompanyService {
	@Autowired
	private  CompanyDAO companyDao;

	
	public CompanyServiceImp() throws SQLException {
		
	}
	
	@Override
	public boolean deleteCompany(Company company) {
		
		return companyDao.delete(company.getId());
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
	public Long getNbCompany() {
		
		return companyDao.findMaxElement();
	}

	@Override
	public List<Company> getCompanyPages(int offset, int nbPage) {
		System.out.println(" je suis la ");
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
