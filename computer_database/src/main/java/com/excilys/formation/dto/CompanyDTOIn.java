package com.excilys.formation.dto;

import java.util.List;

import com.excilys.formation.model.Company;
import com.excilys.formation.pagination.Page;

public interface CompanyDTOIn {
	
	List<Company> getAllCompany();
	
	Company getCompany(Integer id);
	
	Integer getCompanyMaxElement();
	
	List<Company> getPageComputer(Page page);
}
