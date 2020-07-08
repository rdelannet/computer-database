package com.excilys.formation.dto;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.pagination.Page;

public class CompanyDTO {
	
	private Integer id;
	private String name;
	@Autowired
	ConnectDB conn ;
	@Autowired
	CompanyDAO companyD;
	
	public CompanyDTO(Company company) throws SQLException {
		//conn = new ConnectDB();
		

		
		this.id = company.getId();
		this.name = company.getName();
		
	}
	public CompanyDTO(int id) throws SQLException {
		//conn = new ConnectDB();
		this.id = id;
		
		}
	public CompanyDTO()  {
		
		}

	public Integer getId() {
		return id;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
}
