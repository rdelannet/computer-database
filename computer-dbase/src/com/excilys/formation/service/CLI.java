package com.excilys.formation.service;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class CLI {
	
	private Scanner scan;
	ConnectDB conn ;
	
	public CLI(ConnectDB con) {
		this.scan = new Scanner(System.in);
		this.conn = con;
	}
	
	public void menu() {
		System.out.println("----------Welcome to the Computer Database------------");
		System.out.println("Choose what you want in the menus about all companies and computer");
		System.out.println("You just need to press one of this number to show ,create , delete or update about the computer database");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("1 - List Computers");
		System.out.println("2 - List Companies");
		System.out.println("3 - Show Computer details");
		System.out.println("4 - Create a Computer");
		System.out.println("5 - Update a Computer");
		System.out.println("6 - Delete a Computer");
		
	}
	
	public void missNumber() {
		System.out.println("You should press a number between 1 and 6");
		
	}
	
	public void chooseNumber() {
		System.out.println("Please press a number between 1 and 6");
		String choose = scan.next();
		System.out.println(choose);
		
		while(true) {
			switch(choose) {
			case "1":
				System.out.println("SHOW COMPUTERS");
				showListComputers();
				menu();
				choose = scan.next();
				break;
			case "2":
				System.out.println("SHOW COMPANIES");
				showListCompanies();
				menu();
				choose = scan.next();
				break;
			case "3":
				System.out.println("SHOW DETAILS");
				System.out.println("Entrer the id of the computer :");
				int show = scan.nextInt();
				showDetails(show);
				menu();
				choose = scan.next();
				break;
			case "4":
				System.out.println("CREATE");
				createComputer();
				menu();
				choose = scan.next();
				break;
			case "5":
				System.out.println("UPDATE");
				updateComputer();
				menu();
				choose = scan.next();
				break;
			case "6":
				System.out.println("DELETE");
				deleteComputer();
				menu();
				choose = scan.next();
				break;
			default:

				System.out.println("You should press a number between 1 and 6");
				choose = scan.next();
				break;
				
				
			}
		}
		
		
	}
	
	public void showListComputers() {
		ComputerDAO computers = new ComputerDAO(conn);
		List<Computer> printComputer= new ArrayList<Computer>();
		System.out.println("---------List of all computers-----------");
		System.out.println(computers.findAll());
		printComputer = computers.findAll();
		for(int i = 0; i < printComputer.size();i++) {
			System.out.println(printComputer.get(i));
		}
		
	}
	public void showListCompanies() {
		CompanyDAO companies = new CompanyDAO(conn);
		List<Company> printCompanies = new ArrayList<Company>();
		System.out.println("---------List of all companies-----------");
		printCompanies = companies.findAll();
		for(int i = 0; i < printCompanies.size();i++) {
			System.out.println(printCompanies.get(i));
		}
		
	
		
	}
	
	public void showDetails(int i) {
		ComputerDAO computer = new ComputerDAO(conn);
		System.out.println(computer.find(i));
		
	}
	public void createComputer() {
		ComputerDAO computerD = new ComputerDAO(conn);
		Computer computer = new Computer();
		
		System.out.println("Entrer the id of the computer :");
		int id = Integer.parseInt(scan.next());
		computer.setId(id);
		
		System.out.println("Entrer the name of the computer :");
		String name = scan.next();
		computer.setName(name);
		
		System.out.println("Do you want to enter a production start date ? ( yes or no )");
		String start = scan.next();
		if(start.equals("yes")) {
			System.out.println("Enter the year of introduced date :");
			int year = Integer.parseInt(scan.next());
			System.out.println("Enter the month of introduced date : (ex : 06 for june)");
			int month = Integer.parseInt(scan.next());
			System.out.println("Enter the day of introduced date :");
			int day = Integer.parseInt(scan.next());
			computer.setDateInt(LocalDate.of(year, month, day));
			
			
		}
		System.out.println("Do you want to enter a production end date ? ( yes or no )");
		String end = scan.next();
		if(end.equals("yes")) {
			System.out.println("Enter the year of discontinued date :");
			int year = Integer.parseInt(scan.next());
			System.out.println("Enter the month of discontinued date : (ex : 06 for june)");
			int month = Integer.parseInt(scan.next());
			System.out.println("Enter the day of discontinued date :");
			int day = Integer.parseInt(scan.next());
			computer.setDateInt(LocalDate.of(year, month, day));
			
		}
		System.out.println("Do you want to enter a company id ? ( yes or no )");
		String company = scan.next();
		if(company.equals("yes")) {
			System.out.println("Enter the id of the company:");
			int idCompany = Integer.parseInt(scan.next());
			computer.setCompanyId(idCompany);
			
		}
		computerD.create(computer);
		
	}
	
	public void deleteComputer() {
		ComputerDAO computer = new ComputerDAO(conn);
		System.out.println("Enter the id of the computer you want to delete :");
		int delete = Integer.parseInt(scan.next());
		computer.delete(computer.find(delete));
		
		
	}
	
	public void updateComputer() {
		ComputerDAO computer = new ComputerDAO(conn);
		
		System.out.println("Enter the id of the computer that you want to update :");
		int id = Integer.parseInt(scan.next());
		Computer computerA = computer.find(id);
		
		System.out.println("Do you want to change the name ? ( yes or no )");
		String bool = scan.next();
		if(bool.equals("yes")) {
			System.out.println("Enter the name : ");
			String name = scan.next();
			computerA.setName(name);
		}
		System.out.println("Do you want to change the company id ? ( yes or no )");
		bool = scan.next();
		if(bool.equals("yes")) {
			System.out.println("Enter the company id: ");
			int idCompany = Integer.parseInt(scan.next());
			computerA.setCompanyId(idCompany);
		}
		System.out.println("Do you want to change the introduced date  ? ( yes or no )");
		bool = scan.next();
		if(bool.equals("yes")) {
			System.out.println("Enter the year : ");
			int year = Integer.parseInt(scan.next());
			System.out.println("Enter the month  : (ex : 06 for june) ");
			int month = Integer.parseInt(scan.next());
			System.out.println("Enter the day : ");
			int day = Integer.parseInt(scan.next());
			computerA.setDateInt(LocalDate.of(year,month,day));
		}
		System.out.println("Do you want to change the discontinued date? ( yes or no )");
		bool = scan.next();
		if(bool.equals("yes")) {
			System.out.println("Enter the year : ");
			int year = Integer.parseInt(scan.next());
			System.out.println("Enter the month : ");
			int month = Integer.parseInt(scan.next());
			System.out.println("Enter the day  : ");
			int day = Integer.parseInt(scan.next());
			computerA.setDateDisc(LocalDate.of(year, month, day));
		}
		
		computer.update(computerA);
		
	}
	
	
	
	
	
	
	
	
}
