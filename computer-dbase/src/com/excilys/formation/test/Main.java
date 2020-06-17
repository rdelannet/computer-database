package com.excilys.formation.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CLI;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectDB conn = new ConnectDB();
		CLI cli = new CLI(conn);
		cli.menu();
		cli.chooseNumber();
		
	}

}