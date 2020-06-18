package com.excilys.formation.test;

import java.sql.SQLException;
import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.service.CLI;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectDB conn = new ConnectDB();
		CLI cli = new CLI(conn);
		cli.menu();
		cli.chooseNumber();
		
	}

}