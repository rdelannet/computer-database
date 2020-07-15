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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.configuration.SpringConf;
import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.ComputerMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;


@Repository
public class ComputerDAO extends DAO<Computer>{
	private String insert = "INSERT INTO computer(name,introduced,discontinued,company_id) values (:name,:introduced,:discontinued,:company_id)";
	private String delete = "DELETE FROM computer WHERE id = :id";
	private String sqlUpdate = "UPDATE computer SET name = :name , introduced = :introduced , discontinued = :discontinued , company_id = :company_id WHERE id = :id";
	private String findComputer = "SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id WHERE computer.id = :id";
	private String findAllComputer = "SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id";
	private String count = "SELECT count(*) as count FROM computer";
	private String sqlId = "SELECT id FROM computer";
	private SpringConf hikari;
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private ConnectDB connect;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ComputerDAO() throws SQLException {
		
		
;		
	}

	public boolean create(Computer computer)  {
		

		try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			 vParams.addValue("name",computer.getName());
			 vParams.addValue("introduced", computer.getDateInt());
			 vParams.addValue("discontinued", computer.getDateDisc());
			 vParams.addValue("company_id", computer.getCompany().getId());
			 NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(connect.getHikariDataSource());
			 vJdbcTemplate.update(insert,vParams);
			
		} catch (Exception e) {
			logger.error("Error Add Computer");
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	public boolean delete(Computer computer) {
		
		try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			 vParams.addValue("id",computer.getId());
			 NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(connect.getHikariDataSource());
			 vJdbcTemplate.update(delete,vParams);
	
		}catch(Exception eSQL) {
			logger.error("Error Delete Computer");
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Computer computer) {
		
		try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("computer.id", computer.getId());
			vParams.addValue("computer.name",computer.getName());
			vParams.addValue("introduced", computer.getDateInt());
			vParams.addValue("discontinued", computer.getDateDisc());
			vParams.addValue("company_id", computer.getCompany().getId());
			NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(connect.getHikariDataSource());
			vJdbcTemplate.update(sqlUpdate,vParams);

			
			
		} catch (Exception e) {
			logger.error("Error update Computer");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Computer find(int id) {
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(connect.getHikariDataSource());
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("id", id);
		RowMapper<Computer> vRowMapper = new RowMapper<Computer>() {
			public Computer mapRow(ResultSet result,int numRow) throws SQLException{
				Computer computerR = new Computer();
				computerR.setId(result.getInt("computer.id"));
				computerR.setName(result.getString("computer.name"));
				computerR.setDateInt((result.getDate("introduced").toLocalDate()));
				computerR.setDateDisc((result.getDate("discontinued").toLocalDate()));
				computerR.setCompany(new Company(result.getInt("company_id"),result.getString("c.name")));
				return computerR;
			}
		};
		Computer computer = vJdbcTemplate.queryForObject(findComputer, vParams,vRowMapper);
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		
		List<Computer> vListStatut = null;
		try {
			
			JdbcTemplate vJdbcTemplate = new JdbcTemplate(connect.getHikariDataSource());
			RowMapper<Computer> vRowMapper = new RowMapper<Computer>() {
				public Computer mapRow(ResultSet result,int numRow) throws SQLException{
					Computer computer = new Computer();
					computer.setId(result.getInt("computer.id"));
					computer.setName(result.getString("computer.name"));
					computer.setDateInt((result.getDate("introduced").toLocalDate()));
					computer.setDateDisc((result.getDate("discontinued").toLocalDate()));
					computer.setCompany(new Company(result.getInt("company_id"),result.getString("c.name")));
					return computer;
				}
				
				
			};
			vListStatut = vJdbcTemplate.query(findAllComputer, vRowMapper);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vListStatut;
        
	}
	
	public int findMaxElement() {
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(connect.getHikariDataSource());
		int maxElem = vJdbcTemplate.queryForObject(count, Integer.class);
		
		return maxElem;
	}
	
	public List<Computer> findAllPages(int offset,int nbPage) {
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id LIMIT "+ offset+", "+nbPage);
			while(result.next()) {
				computers.add(ComputerMapper.resultToList(result));
			}
			
		}catch(SQLException e) {
			logger.error("Error fidnd all pages Computer");
			e.printStackTrace();
		}
		return computers;
	}
	
	public List<Computer> findBySearch(int offset,int nbPage,String search){
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id WHERE computer.name LIKE '%"+search+"%' OR c.name LIKE '%"+search+"%' LIMIT "+ offset + ", "+ nbPage);
			while(result.next()) {
				computers.add(ComputerMapper.resultToList(result));
			}
		}catch(SQLException e) {
			logger.error("Error find by search");
			e.printStackTrace();
		}
		return computers;
	}
	public List<Computer> findOrder(Page page,String order,String ascending){
		List<Computer> computers = new ArrayList<Computer>();
		try {
			ResultSet result = this.connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id ORDER BY "+ order+" "+ascending+" LIMIT "+ page.getOffset() + ", "+ page.getNbPages()+" ");
			while(result.next()) {
				computers.add(ComputerMapper.resultToList(result));
			}
			
		}catch(SQLException e) {
			logger.error("Error find by order");
			e.printStackTrace();
		}
		return computers;
	}
	
	public Integer getComputersNbPages(Page page) {
		Integer nbEntries = findMaxElement();
		Integer nbPages = nbEntries/page.getItemsByPage();
		return nbEntries%page.getItemsByPage() == 0?nbPages:nbPages+1;
	}
	
	

}
