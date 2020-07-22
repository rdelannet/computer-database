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
import com.excilys.formation.model.QCompany;
import com.excilys.formation.model.QComputer;
import com.excilys.formation.pagination.Page;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
	
	
	public CompanyDAO() {
		
	}
	

	

	public boolean delete(int id) {
		QCompany company = QCompany.company; 
		QComputer computer = QComputer.computer;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

		try {
			queryFactory.delete(computer).where(company.id.eq(id)).execute();
			queryFactory.delete(company).where(company.id.eq(id)).execute();
			return true;

		}catch (Exception dae) {
			logger.error("Not able to delete computer",dae);
			return false;
		}
			
	}




	
	public Company find(int id) {
		
		QCompany company = QCompany.company;
		JPAQuery<Company>  query = new JPAQuery<Company>(entityManager);
		try {
			return query.from(company)
					.where(company.id.eq(id)).fetchOne();
		}catch (Exception dae) {
			logger.error("Not able to find company",dae);
			return null;
		}	
	}


	@Override
	public List<Company> findAll() {
		QCompany company = QCompany.company;
		JPAQuery<Company>  query = new JPAQuery<Company> (entityManager);	
		
		try {
			return  (ArrayList<Company>)  query.from(company).fetch();
		}catch (Exception e) {
			logger.error("Not able to find all companies",e);
			return new ArrayList<Company>();
		}
	}
	public Long findMaxElement() {
		
		QCompany company = QCompany.company;
		JPAQuery<Company>  query = new JPAQuery<Company>(entityManager);	
		try {
			return query.from(company).fetchCount();
		}catch (Exception dae) {
			logger.error("Not able to get max",dae);
			return 0L;
		}
	}
	
	public List<Company> findAllPages(int offset,int nbPage) {
		QCompany company = QCompany.company;
		JPAQuery<Company> query = new JPAQuery<Company>(entityManager);
		try {
			return (ArrayList<Company>) query.from(company).offset(offset).limit(nbPage).fetch();
		} catch (Exception e) {
			logger.error("find all pages");
			return null;
		}
		
		
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

