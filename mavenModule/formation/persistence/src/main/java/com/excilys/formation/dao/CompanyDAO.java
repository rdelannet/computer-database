package com.excilys.formation.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.QCompany;
import com.excilys.formation.model.QComputer;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CompanyDAO extends DAO<Company>{
	
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	
	
	//NamedParameterJdbcTemplate jdbcTemplate;
	
	
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
			System.out.println("test");
			System.out.println(query.from(company)
					.where(company.id.eq(id)).fetchOne());
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
	public Company create(Company obj) {
		
		return obj;
	}




	@Override
	public boolean update(Company obj) {
		
		return false;
	}
	

	
	
}

