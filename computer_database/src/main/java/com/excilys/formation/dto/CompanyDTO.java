package com.excilys.formation.dto;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.pagination.Page;

public class CompanyDTO {
	
	private Integer id;
	private String name;
	ConnectDB conn ;
	CompanyDAO company;
	
	public CompanyDTO(Integer id) throws SQLException {
		conn = new ConnectDB();
		company = new CompanyDAO(ConnectDB.getInstance());
		this.id = id;
		Company com = company.find(id);
		this.name = com.getName();
		
	}
	public CompanyDTO() {
		
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
