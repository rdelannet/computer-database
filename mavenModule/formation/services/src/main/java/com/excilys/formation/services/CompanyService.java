package com.excilys.formation.services;

import java.util.List;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public interface CompanyService {
	
	public boolean deleteCompany(int id);
	public Company getCompany(int id);
	public List<Company> getAllCompany();
	public Long getNbCompany();
	public List<Company> getCompanyPages(int offset,int nbPage);
	public Company getCompanyFromComputer(Computer computer);
	
}
