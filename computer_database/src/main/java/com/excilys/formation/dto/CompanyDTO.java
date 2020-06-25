package com.excilys.formation.dto;

import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.pagination.Page;

public class CompanyDTO implements CompanyDTOIn{
	
	private  CompanyDAO companies ;
	private ConnectDB connect;
	
	//private static final int NB_PAGE = 10;
	
	public CompanyDTO() {
		this.companies = new CompanyDAO(connect.getConnection());
	}

	@Override
	public List<Company> getAllCompany() {
		
		return  companies.findAll();
	}

	@Override
	public Company getCompany(Integer id) {
		
		return companies.find(id);
	}

	@Override
	public Integer getCompanyMaxElement() {
		
		return companies.findMaxElement();
	}

	@Override
	public List<Company> getPageComputer(Page page) {
		
		return companies.findAllPages(page);
	}
	
	
}
