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
import com.excilys.formation.mappers.MapperRowComputer;
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
	private String findAllPagesQ = "SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id LIMIT :offset , :nbPage";
	private String findSearch = "SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id WHERE computer.name LIKE :search OR c.name LIKE :search LIMIT  :offset , :nbPage";
	private String findOrder = "SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id ORDER BY :order :ascending LIMIT :getOffset, :getNbPages";
	private String count = "SELECT count(*) as count FROM computer";
	private String sqlId = "SELECT id FROM computer";
	private SpringConf hikari;
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private ConnectDB connect;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ComputerDAO(NamedParameterJdbcTemplate jdbcTemplate) throws SQLException {
		
		this.jdbcTemplate =  jdbcTemplate;
;		
	}

	public boolean create(Computer computer)  {
		

		try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			 vParams.addValue("name",computer.getName());
			 vParams.addValue("introduced", computer.getDateInt());
			 vParams.addValue("discontinued", computer.getDateDisc());
			 vParams.addValue("company_id", computer.getCompany().getId());
			 
			 jdbcTemplate.update(insert,vParams);
			
		} catch (Exception e) {
			logger.error("Error Add Computer");
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}

	public boolean delete(int id) {
		
		try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			 vParams.addValue("id",id);
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
		
		
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			vParams.addValue("id", computer.getId());
			vParams.addValue("name",computer.getName());
			vParams.addValue("introduced", computer.getDateInt());
			vParams.addValue("discontinued", computer.getDateDisc());
			vParams.addValue("company_id", computer.getCompany().getId());
			
			jdbcTemplate.update(sqlUpdate,vParams);
			

			
			return true;
	}

	public Computer find(int id) {
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("id", id);
		
		Computer computer = jdbcTemplate.queryForObject(findComputer, vParams,new MapperRowComputer());
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		
		List<Computer> vListStatut = null;
		vListStatut = jdbcTemplate.query(findAllComputer, new MapperRowComputer());
			
		
		return vListStatut;
        
	}
	
	public int findMaxElement() {
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(connect.getHikariDataSource());
		int maxElem = vJdbcTemplate.queryForObject(count, Integer.class);
		
		return maxElem;
	}
	
	public List<Computer> findAllPages(int offset,int nbPage) {
		List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("offset", offset)
				.addValue("nbPage",nbPage);
		
		computers = jdbcTemplate.query(findAllPagesQ,vParams, new MapperRowComputer());
		return computers;
	}
	
	public List<Computer> findBySearch(int offset,int nbPage,String search){
		List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("search", "%"+search+"%")
				.addValue("offset", offset)
				.addValue("nbPage",nbPage);
	
		computers = jdbcTemplate.query(findSearch,vParams, new MapperRowComputer());
		return computers;
	}
	public List<Computer> findOrder(Page page,String order,String ascending){
		List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				
				.addValue("getOffset", page.getOffset())
				.addValue("getNbPages",page.getNbPages());
		
		
			
			
		
		computers = jdbcTemplate.query("SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id ORDER BY "+order+" "+ascending+" LIMIT :getOffset, :getNbPages",vParams, new MapperRowComputer());
		return computers;
	}
	

	
	

}
