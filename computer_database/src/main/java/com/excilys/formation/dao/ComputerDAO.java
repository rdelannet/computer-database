package com.excilys.formation.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.ComputerMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;



public class ComputerDAO extends DAO<Computer>{
	String insert = "INSERT INTO computer(name) values (?)";
	private String delete = "DELETE FROM computer WHERE id = ?";
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	
	public ComputerDAO(Connection conn) {
		super(conn);

		
	}

	public boolean create(Computer computer)  {
		Computer comp = new Computer();
		try(PreparedStatement statement = this.connect.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS)){
			
			statement.setString(1, computer.getName());
			System.out.println(computer.getName());
			statement.executeUpdate();
			ResultSet resultKeys = statement.getGeneratedKeys();
			if(resultKeys.first()) {
				Integer computerId = resultKeys.getInt(1);
				computer.setId(computerId);
				this.update(computer);
				comp = this.find(computerId);
			}
			
					
		}catch(SQLException eSQL) {
			logger.error("Error Created Computer");
			eSQL.getMessage();
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(Computer computer) {
		
		try(PreparedStatement statement = this.connect.prepareStatement(delete);) {
			
			
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
			System.out.println("coucou");
			String sql = "UPDATE computer SET name = '" + computer.getName() + "'";
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
			
			this.connect.createStatement().
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
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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
	
	public List<Computer> findAllPages(int offset,int nbPage) {
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer LIMIT "+ offset+", "+nbPage);
			computers = ComputerMapper.resultToList(result);
		}catch(SQLException e) {
			logger.error("Error fidnd all pages Computer");
			e.printStackTrace();
		}
		return computers;
	}
	
	public List<Computer> findBySearch(int offset,int nbPage,String search){
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer LEFT JOIN company as c on computer.company_id = c.id WHERE computer.name LIKE '%"+search+"%' OR c.name LIKE '%"+search+"%' LIMIT "+ offset + ", "+ nbPage);
			computers = ComputerMapper.resultToList(result);
		}catch(SQLException e) {
			logger.error("Error find by search");
			e.printStackTrace();
		}
		return computers;
	}
	
	public Integer getComputersNbPages(Page page) {
		Integer nbEntries = findMaxElement();
		Integer nbPages = nbEntries/page.getItemsByPage();
		return nbEntries%page.getItemsByPage() == 0?nbPages:nbPages+1;
	}
	public List<Computer> getComputersByPage(Page page) {
		Integer offset = (page.getCurrentPage()-1)*page.getItemsByPage();
		return findAllPages(offset, page.getItemsByPage());
	}
	public List<Computer> getComputersSearchByPage(Page page,String search) {
		Integer offset = (page.getCurrentPage()-1)*page.getItemsByPage();
		return findBySearch(offset, page.getItemsByPage(), search);
	}
	
	

}
