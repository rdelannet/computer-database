package com.excilys.formation.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.CompanyMapper;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

@Repository
public class CompanyDAO extends DAO<Company>{
	
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private String sqlComp = "SELECT id,name FROM company";
	private String count = "SELECT count(*) as count FROM company";
	
	
	@Autowired
	private ConnectDB connect;
		
	public CompanyDAO() throws SQLException {
		
	}
	

	

	public boolean delete(Company company) {
		String sqlCompany = "DELETE FROM company WHERE id = ?";
		String sqlComputer = "DELETE FROM computer WHERE company_id = ?";
		try {
			connect.getInstance().setAutoCommit(false);
			PreparedStatement statementComputer = this.connect.getInstance().prepareStatement(sqlComputer);
			statementComputer.setInt(1,company.getId());
			statementComputer.executeUpdate();
			
			PreparedStatement statementCompany = this.connect.getInstance().prepareStatement(sqlCompany);
			statementCompany.setInt(1,company.getId());
			statementCompany.executeUpdate();
			connect.getInstance().commit();
			
		} catch (SQLException e) {
			logger.error("Error delete Company");
			try {
				connect.getInstance().rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}




	
	public Company find(int id) {
		Company company = null;  
		
	    try {
	   
	      ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	    		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id,name FROM company WHERE id = " + id);
	      System.out.println(result);
	      company = CompanyMapper.resultToObject(result);
	       
	    } catch (SQLException e) {
	    	
	    	logger.error("Error find Company");
	      e.printStackTrace();
	    }
	    return company;
	}


	@Override
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlComp);
			System.out.println(result);
			while(result.next()) {
				companies.add(CompanyMapper.resultToList(result));
			}
			
			
		}catch(SQLException e) {
			logger.error("Error find all Company");
			e.printStackTrace();
		}
		return companies;
	}
	public int findMaxElement() {
		
		Page page = new Page();
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery(count);
			if(result.first()) {
				page.setMaxElem(result.getInt(1));
			}
			
		}catch(SQLException e) {
			logger.error("Error find max Company");
			e.printStackTrace();
		}
		return page.getMaxElem();
	}
	
	public List<Company> findAllPages(int offset,int nbPage) {
		List<Company> computers = new ArrayList<Company>();
		
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id,name FROM company LIMIT "+ offset+", "+nbPage);
			while(result.next()) {
				computers.add(CompanyMapper.resultToList(result));
			}
				
			
		}catch(SQLException e) {
			logger.error("Error fidnd all pages Companies");
			e.printStackTrace();
		}
		return computers;
	}




	@Override
	public boolean create(Company obj) {
		
		return false;
	}




	@Override
	public boolean update(Company obj) {
		
		return false;
	}
	

	
	
}

