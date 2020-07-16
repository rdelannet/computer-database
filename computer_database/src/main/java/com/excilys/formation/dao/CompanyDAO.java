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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.mappers.CompanyMapper;
import com.excilys.formation.mappers.MapperRowCompany;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

@Repository
public class CompanyDAO extends DAO<Company>{
	
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private String sqlComp = "SELECT id,name FROM company";
	private String count = "SELECT count(*) as count FROM company";
	private String findCompany = "SELECT id,name FROM company WHERE id = :id";
	private String findAllPagesQ = "SELECT id,name FROM company LIMIT :offset , :nbPage";
	private String deleteComp = "DELETE FROM computer WHERE company_id = :id";
	private String delete = "DELETE FROM company WHERE id = :id";
	@Autowired
	private ConnectDB connect;
	
	NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public CompanyDAO(NamedParameterJdbcTemplate jdbcTemplate) throws SQLException {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	

	public boolean delete(int id) {
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		 vParams.addValue("company_id",id);
		 jdbcTemplate.update(deleteComp,vParams);
		 MapSqlParameterSource params = new MapSqlParameterSource();
		 vParams.addValue("id",id);
		 jdbcTemplate.update(delete,vParams);
		 return true;
	}




	
	public Company find(int id) {
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("id", id);
		
		Company company = jdbcTemplate.queryForObject(findCompany, vParams,new MapperRowCompany());
		return company;
	}


	@Override
	public List<Company> findAll() {
		List<Company> vListStatut = null;
		try {
			
			JdbcTemplate vJdbcTemplate = new JdbcTemplate(connect.getHikariDataSource());
			
			vListStatut = vJdbcTemplate.query(sqlComp, new MapperRowCompany());
			
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
	
	public List<Company> findAllPages(int offset,int nbPage) {
		List<Company> company = new ArrayList<Company>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("offset", offset)
				.addValue("nbPage",nbPage);
		
		company = jdbcTemplate.query(findAllPagesQ,vParams, new MapperRowCompany());
		return company;
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

