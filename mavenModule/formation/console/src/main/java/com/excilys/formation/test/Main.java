package com.excilys.formation.test;

import java.sql.SQLException;




import com.excilys.formation.ui.CLI;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		CLI cli = new CLI();
		cli.menu();
		cli.chooseNumber();
		
	}

}