package com.excilys.formation.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.CompanyMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.pagination.Page;


public class CompanyDAO extends DAO<Company>{
	
	

	public CompanyDAO(ConnectDB conn) {
		super(conn);
		
	}

	
	public boolean create(Company company) {
		String sql = "INSERT INTO company(id,name) values (?,?)";
		try {
			PreparedStatement statement = this.connect.getConnection().prepareStatement(sql);
			statement.setInt(1,company.getId());
			statement.setString(2,company.getName());
			//statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

	public boolean delete(Company company) {
		String sql = "DELETE FROM company WHERE id = ?";
		try {
			PreparedStatement statement = this.connect.getConnection().prepareStatement(sql);
			statement.setInt(1,company.getId());
			//statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean update(Company company) {
		String sql = "UPDATE company SET id = ?, name = ? WHERE id = ?";
		try {
			PreparedStatement statement = this.connect.getConnection().prepareStatement(sql);
			statement.setInt(1,company.getId());
			statement.setString(2,company.getName());
			statement.setInt(3,company.getId());
			//statement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public Company find(int id) {
		Company company = new Company();  

	    try {
	      ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	    		    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id = " + id);
	       company = CompanyMapper.resultToObject(result);     
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return company;
	}


	@Override
	public List<Company> findAll() {
		List<Company> companies = new ArrayList<Company>();
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");
			companies = CompanyMapper.resultToList(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	public int findMaxElement() {
		
		Page page = new Page();
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT count(*) as count FROM company");
			if(result.first()) {
				page.setMaxElem(result.getInt(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return page.getMaxElem();
	}
	
	public List<Company> findAllPages(Page page) {
		List<Company> companies = new ArrayList<Company>();
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company LIMIT 10" +" OFFSET "+page.getNbPages());
			companies = CompanyMapper.resultToList(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	
}

