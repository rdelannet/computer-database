package com.excilys.formation.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.connect.ConnectionH2;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class CompanyDAOTest {
	
	private CompanyDAO companyDao;
	private ConnectionH2 connect;
	@Before
	public void setUp() throws Exception {
		companyDao = new CompanyDAO();
	}
	
	@Test
	public void findAll() {
		assertTrue(companyDao.findAll().size() == 50);
	}
	
	@Test
	public void findMaxElement() {
		assertTrue(companyDao.findMaxElement() == 50);
	}
	
	@Test
	public void find() {
		Computer computer = new Computer("MacBook Pro 15.4 inch");
		computer.setId(1);
		computer.setCompany(new Company(1,"Apple Inc."));
		assertEquals(companyDao.find(1), computer);
	}
	
	@Test
	public void findAllPages() {
		assertTrue(companyDao.findAllPages(10,10).size() == 10);
	}
	
	@Test
	public void create() {
		Company company = new Company(1,"JOJO Company");
		
		boolean newComp = companyDao.create(company);
		
		assertTrue(newComp == true);
	}
	
	@Test
	public void delete() {
		Company company = new Company(1,"JOJO Company");
		companyDao.delete(company);
		assertTrue(companyDao.findMaxElement() == 49);
	}
	



}
