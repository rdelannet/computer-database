package com.excilys.formation.test;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.ui.CLI;

public class Main {
	@Autowired
	static	ConnectDB conn ;
	public static void main(String[] args) throws SQLException {
		
		CLI cli = new CLI(conn);
		cli.menu();
		cli.chooseNumber();
		
	}

}