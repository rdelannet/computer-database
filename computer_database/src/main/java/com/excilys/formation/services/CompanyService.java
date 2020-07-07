package com.excilys.formation.services;

import java.util.List;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public interface CompanyService {
	
	public boolean deleteCompany(Company company);
	public Company getCompany(int id);
	public List<Company> getAllCompany();
	public int getNbCompany();
	public List<Company> getCompanyPages(int offset,int nbPage);
	public Company getCompanyFromComputer(Computer computer);
	
}
