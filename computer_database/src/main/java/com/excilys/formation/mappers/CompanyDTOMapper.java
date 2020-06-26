package com.excilys.formation.mappers;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;

public class CompanyDTOMapper {
	
	public static Company CompanyDtoToCompany(CompanyDTO companyDto) {
		Company company = new Company(Integer.parseInt(companyDto.getId()),companyDto.getName());
		return company;
	}

}
