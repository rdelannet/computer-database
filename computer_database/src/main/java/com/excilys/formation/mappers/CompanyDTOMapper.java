package com.excilys.formation.mappers;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.model.Company;

public class CompanyDTOMapper {
	
	public static Company CompanyDtoToCompany(CompanyDTO companyDto) {
		Company company = new Company(companyDto.getId(),companyDto.getName());
		return company;
	}
	
	public static CompanyDTO companytoCompanyDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setId(company.getId());
		companyDto.setName(company.getName());
		return companyDto;
	}

}
