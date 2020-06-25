package com.excilys.formation.dto;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

public class ComputerDTO {
	
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO companyDTO;
	
	public ComputerDTO (String id, String name, String introduced, String discontinued, CompanyDTO companyDTO) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyDTO = companyDTO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

}
