package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;


public class CompanyDAO extends DAO<Company>{

	public CompanyDAO(Connection conn) {
		super(conn);
		
	}

	
	public boolean create(Company obj) {
		
		return false;
	}

	
	public boolean delete(Company obj) {
		
		return false;
	}


	public boolean update(Company obj) {
		
		return false;
	}

	
	public Company find(int id) {
		Company company = new Company();  

	    try {
	      ResultSet result = this.connect.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE, 
	        ResultSet.CONCUR_READ_ONLY
	      ).executeQuery("SELECT * FROM company WHERE id = " + id);
	        if(result.first())
	          company = new Company(id, result.getString("name"));         
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return company;
	}


	@Override
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			while(result.next()) {
				companies.add(new Company(result.getInt("id"), result.getString("name")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	
}

