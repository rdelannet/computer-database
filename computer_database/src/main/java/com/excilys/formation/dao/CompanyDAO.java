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
	
	
	@Autowired
	private ConnectDB connect;
		
	public CompanyDAO() throws SQLException {
		
	}
	

	
	public boolean create(Company company) {
		String sql = "INSERT INTO company(id,name) values (?,?)";
		try {
			PreparedStatement statement = this.connect.getInstance().prepareStatement(sql);
			statement.setInt(1,company.getId());
			statement.setString(2,company.getName());
			//statement.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error create Company");
			e.printStackTrace();
			return false;
		}
		return true;
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean update(Company company) {
		String sql = "UPDATE company SET id = ?, name = ? WHERE id = ?";
		
		try {
			PreparedStatement statement = this.connect.getInstance().prepareStatement(sql);
			statement.setInt(1,company.getId());
			statement.setString(2,company.getName());
			statement.setInt(3,company.getId());
			//statement.executeUpdate();
			
			
		} catch (Exception e) {
			logger.error("Error update Company");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public Company find(int id) {
		Company company = null;  
		
	    try {
	   
	      ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	    		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
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
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");
			System.out.println(result);
			companies = CompanyMapper.resultToList(result);
			
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
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT count(*) as count FROM company");
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
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer LIMIT "+ offset+", "+nbPage);
			computers = CompanyMapper.resultToList(result);
		}catch(SQLException e) {
			logger.error("Error fidnd all pages Computer");
			e.printStackTrace();
		}
		return computers;
	}
	
	/*public Company getCompanyFromComputer(Computer computer) {
		if(computer.getCompany() != null) {
			return find(computer.getCompany().getId());
		}
		return null;
	}*/
	public static void main(String[] args) throws SQLException {
		CompanyDAO companyDAO = new CompanyDAO();
		Company company = new Company(378,"ChipTest");
		System.out.println(companyDAO.findAll());
		
	}
	
}

