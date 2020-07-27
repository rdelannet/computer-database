package com.excilys.formation.dto;



import org.springframework.beans.factory.annotation.Autowired;


import com.excilys.formation.model.Company;


public class CompanyDTO {
	
	private Integer id;
	private String name;
	
	
	

	
	public CompanyDTO(Company company) {
		
		this.id = company.getId();
		this.name = company.getName();
		
	}
	public CompanyDTO(int id) {

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
		return  name;
	}
}
