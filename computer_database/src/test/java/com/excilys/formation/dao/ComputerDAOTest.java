package com.excilys.formation.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;


public class ComputerDAOTest {
	
	private ComputerDAO computerDao;
	//private ConnectionH2 connect;

	@Before
	public void setUp() throws Exception {
		//computerDao = new ComputerDAO();
	}

	@Test
	public void findAll() {
		assertTrue(computerDao.findAll().size() == 50);
	}
	
	@Test
	public void findMaxElement() {
		assertTrue(computerDao.findMaxElement() == 50);
	}
	
	@Test
	public void find() {
		Computer computer = new Computer("MacBook Pro 15.4 inch");
		computer.setId(1);
		computer.setCompany(new Company(1,"Apple Inc."));
		assertEquals(computerDao.find(1), computer);
	}
	
	@Test
	public void findAllPages() {
		assertTrue(computerDao.findAllPages(10,10).size() == 10);
	}
	
	@Test
	public void create() {
		Computer computer = new Computer();
		computer.setName("test");
		boolean newComp = computerDao.create(computer);
		
		assertTrue(newComp == true);
	}
	
	@Test
	public void delete() {
		Computer computer = new Computer("MacBook Pro 15.4 inch");
		//computerDao.delete(computer);
		assertTrue(computerDao.findMaxElement() == 49);
	}
	
	@Test
	public void update() {
		Computer computer = new Computer("MacBook Pro 15.4 inch");
		computer.setId(1);
		computer.setCompany(new Company(2,"Thinking Machines"));
		computerDao.update(computer);
		assertTrue(computer.getCompany().getId() == 2);
	}
	
	@Test
	public void countSearch() {
		assertTrue(computerDao.findBySearch(0,10,"Mac").size() == 25);
	}
	

}
