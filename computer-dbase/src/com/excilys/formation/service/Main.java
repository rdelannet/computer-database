package com.excilys.formation.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class Main {

	public static void main(String[] args) throws SQLException {
		/*ConnectDB conn = new ConnectDB();
		Computer comp = new Computer(576,"iphone X");
		CompanyDAO companyDao = new CompanyDAO(conn);
		ComputerDAO computerDao = new ComputerDAO(conn);
		List<Company> companies = new ArrayList<Company>();
		companies = companyDao.findAll();
		
		for(Company company: companies) {
			System.out.println(company);
		}
		
		Company company = companyDao.find(10);
		System.out.println(company);
		
		
		
		Computer computer2 = computerDao.find(7);
		
		System.out.println(computerDao.create(comp));
		computerDao.delete(comp);
		List<Computer> allcomputer = new ArrayList<Computer>();
		allcomputer = computerDao.findAll();
		for(Computer computer: allcomputer) {
			System.out.println(computer);
		}
		conn.getConnection().close();*/
		CLI cli = new CLI();
		cli.menu();
	}

}