package com.excilys.formation.service;
import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;

public class CLI {
	
	
	public void menu() {
		System.out.println("MENU : CHoose your fighter");
		System.out.println("1 - List Computers");
		System.out.println("2 - List Companies");
		System.out.println("3 - Show Computer details");
		System.out.println("4 - Create a Computer");
		System.out.println("5 - Update a Computer");
		System.out.println("6 - Delete a Computer");
		
		System.out.println("Choose a letter : ");
		Scanner choose = new Scanner(System.in);
		int number = choose.nextInt();
		
		ConnectDB conn;
		try {
			conn = new ConnectDB();
			switch(number) {
			case 1:
				ComputerDAO comp = new ComputerDAO(conn);
				comp.findAll();
				break;
			case 2:
				CompanyDAO compB = new CompanyDAO(conn);
				compB.findAll();
				break;
			case 3:
				System.out.println("Choose a ID :");
				Integer i = choose.nextInt();
				ComputerDAO compC = new ComputerDAO(conn);
				compC.find(i);
				break;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
