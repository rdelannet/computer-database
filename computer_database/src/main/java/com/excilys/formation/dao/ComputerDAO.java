package com.excilys.formation.dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.ComputerMapper;

import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;



public class ComputerDAO extends DAO<Computer>{
	private String insert = "INSERT INTO computer(id,name) values (?,?)";
	private String delete = "DELETE FROM computer WHERE id = ?";
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	public ComputerDAO(ConnectDB conn) {
		super(conn);
		
	}

	public boolean create(Computer computer)  {
		
		try(PreparedStatement statement = this.connect.getConnection().prepareStatement(insert)){
			statement.setInt(1, computer.getId());
			statement.setString(2, computer.getName());
			statement.executeUpdate();
			
			Integer computerId = computer.getId();
			computer.setId(computerId);
			this.update(computer);
			computer = this.find(computerId);
					
		}catch(SQLException eSQL) {
			logger.error("Error Created Computer");
			eSQL.getMessage();
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(Computer computer) {
		
		try(PreparedStatement statement = this.connect.getConnection().prepareStatement(delete);) {
			
			
			statement.setInt(1, computer.getId());
			statement.executeUpdate();
		}catch(SQLException eSQL) {
			logger.error("Error Delete Computer");
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Computer computer) {
		
		try {
			String sql = "UPDATE computer SET id = '" + computer.getId() + "'" + " , name = '" + computer.getName() + "'";
			if(computer.getDateInt() != null) {
				sql += ", introduced = '" + Date.valueOf(computer.getDateInt()) + "'";
			}			
			if(computer.getDateDisc() != null) {
				sql += ", discontinued = '" + Date.valueOf(computer.getDateDisc()) + "'";
			}		
			if(computer.getCompanyId() != null) {
				sql += ", company_id = '" +computer.getCompanyId() + "'"; 
			}		
			sql += " WHERE id = "+ computer.getId();
			
			this.connect.getConnection().createStatement().
				executeUpdate(sql);
			computer = this.find(computer.getId());
			
		} catch (Exception e) {
			logger.error("Error update Computer");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Computer find(int id) {
		Computer computer = null;
		try {
			
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
			
			computer = ComputerMapper.resultToObject(result);			
				
			
		}catch(SQLException e) {
			logger.error("Error find Computer");
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> computers = null;
		
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			computers = ComputerMapper.resultToList(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	
	public int findMaxElement() {
		
		Page page = new Page();
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT count(*) as count FROM computer");
			if(result.first()) {
				page.setMaxElem(result.getInt(1));
			}
			
		}catch(SQLException e) {
			logger.error("Error find max Computer");
			e.printStackTrace();
		}
		return page.getMaxElem();
	}
	
	public List<Computer> findAllPages(Page page) {
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = this.connect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer LIMIT 10 OFFSET "+page.getNbPages());
			computers = ComputerMapper.resultToList(result);
		}catch(SQLException e) {
			logger.error("Error fidnd all pages Computer");
			e.printStackTrace();
		}
		return computers;
	}
	

}
