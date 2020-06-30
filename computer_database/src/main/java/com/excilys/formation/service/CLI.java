package com.excilys.formation.service;

import java.time.LocalDate;


import java.util.Scanner;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.CompanyDAO;
import com.excilys.formation.dao.ComputerDAO;

import com.excilys.formation.model.Computer;
import com.excilys.formation.pagination.Page;

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
				int show;
				try {
					show = scan.nextInt();
				} catch (Exception e) {
					show = scan.nextInt();
				}
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
		ComputerDAO computers = new ComputerDAO(conn.getConnection());
		
		System.out.println("---------List of all computers-----------");
		System.out.println("Enter 1 to see an other pages, 0 to end");
		int pages;
		Page page = new Page();
		page.setMaxElem(computers.findMaxElement());
		
		try {
			pages = Integer.parseInt(scan.next());
		} catch (Exception e) {
			System.out.println("Not a number");
			pages = 0;
			//e.printStackTrace();
		}
		
		
		
		while(page.getNbPages() <= page.getMaxElem()) {
			if(pages == 0){
				return;
			}
			switch(pages) {
				case 1:
					
					for(int j = 0; j < computers.findAllPages(10,page.getNbPages()).size();j++) {
						
						System.out.println(computers.findAllPages(10,page.getNbPages()).get(j));
					}
					
					page.setNbPages(page.getNbPages()+10);
					System.out.println("Enter 1 to see an other pages,2 to back and 0 to end");
					try {
						
						pages = scan.nextInt();
					} catch (Exception e) {
						System.out.println("Not a number");
						pages = 0;
						//e.printStackTrace();
						return;
					}
					
					break;
				case 0:
					break;
				case 2:
					for(int j = 0; j < computers.findAllPages(10,page.getNbPages()).size();j++) {
						System.out.println(computers.findAllPages(10,page.getNbPages()).get(j));
					}
					if(page.getNbPages()>= 10) {
						page.setNbPages(page.getNbPages()-10);
					}
					else {
						System.out.println("End of the list");
						pages = 1;
						break;
					}
					
					try {
						System.out.println("Enter 1 to see an other pages,2 to back and  0 to end");
						pages = scan.nextInt();
					} catch (Exception e) {
						
						System.out.println("Not a number");
						pages = 0;
						//e.printStackTrace();
						return;
					}
					break;
				default :
					System.out.println("Wrong input. Enter a good one");
					pages = scan.nextInt();
					break;
					
				
			}
		}
		
	}
	public void showListCompanies() {
		CompanyDAO companies = new CompanyDAO(conn.getConnection());
		
		System.out.println("---------List of all companies-----------");
		System.out.println("Enter 1 to see an other pages, 0 to end");
		int pages;
		try {
			pages = Integer.parseInt(scan.next());
		} catch (Exception e) {
			System.out.println("Not a number");
			pages = 0;
			
		}
		Page page = new Page();
		page.setMaxElem(companies.findMaxElement());
		while(page.getNbPages() <= page.getMaxElem()) {
			
			if(pages == 0){
				return;
			}
			switch(pages) {
				case 1:
					
					for(int j = 0; j < companies.findAllPages(page).size();j++) {
						System.out.println(companies.findAllPages(page).get(j));
					}
					page.setNbPages(page.getNbPages()+10);
					try {
						System.out.println("Enter 1 to see an other pages,2 to back and 0 to end");
						pages = scan.nextInt();
					} catch (Exception e) {
						
						System.out.println("Not a number");
						pages = 0;
						//e.printStackTrace();
						return;
					}
					break;
				case 0:
					break;
				case 2:
					for(int j = 0; j < companies.findAllPages(page).size();j++) {
						System.out.println(companies.findAllPages(page).get(j));
					}
					if(page.getNbPages()>= 10) {
						page.setNbPages(page.getNbPages()-10);
					}
					try {
						System.out.println("Enter 1 to see an other pages,2 to back and  0 to end");
						pages = scan.nextInt();
					} catch (Exception e) {
						
						System.out.println("Not a number");
						pages = 0;
						//e.printStackTrace();
						return;
					}
					break;
				default :
					System.out.println("Wrong input. Enter a good one");
					pages = scan.nextInt();
					return;
					
				
			}
		}
	
		
	}
	
	public void showDetails(int i) {
		ComputerDAO computer = new ComputerDAO(conn.getConnection());
		System.out.println(computer.find(i));
		
	}
	
	public LocalDate addDate(Computer computer) {
		
		String start = scan.next();
		if(start.equals("yes")) {
			System.out.println("Enter the year of your date :");
			int month;
			int year;
			int day;
			try {
				year = Integer.parseInt(scan.next());
				
			} catch (Exception e) {
				System.out.println("Not the value we need");
				year = Integer.parseInt(scan.next());
				//e.printStackTrace();
			}
			System.out.println("Enter the month of your date : (ex : 06 for june)");
			try {
				 month = Integer.parseInt(scan.next());
			} catch (Exception e) {
				System.out.println("Not the value we need");
				month = Integer.parseInt(scan.next());
				//e.printStackTrace();
			}
			System.out.println("Enter the day of your date :");
			try {
				day = Integer.parseInt(scan.next());
			} catch (Exception e) {
				System.out.println("Not the value we need");
				day = Integer.parseInt(scan.next());
				//e.printStackTrace();
			}
			
			return (LocalDate.of(year, month, day));
		}
		return null;
	}
	public void createComputer() {
		ComputerDAO computerD = new ComputerDAO(conn.getConnection());
		Computer computer = new Computer();
		
		System.out.println("Entrer the id of the computer :");
		try {
			String id = scan.next();
			
			computer.setId(Integer.parseInt(id));
			
		} catch (Exception e) {
			System.out.println("Not a number");
			//e.printStackTrace();
		}
		
		System.out.println("Entrer the name of the computer :");
		String name = scan.next();
		computer.setName(name);
		System.out.println("Do you want to enter introduced date ? ( yes or no )");
		computer.setDateInt(addDate(computer));
		
		System.out.println("Do you want to enter a production end date ? ( yes or no )");
		computer.setDateDisc(addDate(computer));
		System.out.println("Do you want to enter a company id ? ( yes or no )");
		String company = scan.next();
		if(company.equals("yes")) {
			System.out.println("Enter the id of the company :");
			Integer idCompany ;
			try {
				idCompany = Integer.parseInt(scan.next());
				computer.setCompanyId(idCompany);
				
			} catch (Exception e) {
				System.out.println(" Error Enter the id of the company :");
				idCompany = null;
				//e.printStackTrace();
			}
			
		}
		computerD.create(computer);
		
	}
	
	public void deleteComputer() {
		ComputerDAO computer = new ComputerDAO(conn.getConnection());
		System.out.println("Enter the id of the computer you want to delete :");
		Integer delete;
		try {
			delete = Integer.parseInt(scan.next());
		} catch (Exception e) {
			System.out.println("Error Enter the id of the computer you want to delete :");
			delete = null;
			//e.printStackTrace();
		}
		computer.delete(computer.find(delete));
		
		
	}
	
	public void updateComputer() {
		ComputerDAO computer = new ComputerDAO(conn.getConnection());
		
		System.out.println("Enter the id of the computer that you want to update :");
		Integer id;
		try {
			id = Integer.parseInt(scan.next());
			
		} catch (Exception e) {
			System.out.println("Error Enter the id of the computer that you want to update :");
			id = null;
			//e.printStackTrace();
		}
		Computer computerA = computer.find(id);
		
		
	
		System.out.println("Entrer the name of the computer :");
		String name = scan.next();
		computerA.setName(name);
		
		System.out.println("Do you want to enter introduced date ? ( yes or no )");
		computerA.setDateInt(addDate(computerA));
		
		System.out.println("Do you want to enter a production end date ? ( yes or no )");
		computerA.setDateDisc(addDate(computerA));
			
		System.out.println("Do you want to change a company id ? ( yes or no )");
		String company = scan.next();
		if(company.equals("yes")) {
			System.out.println("Enter the id of the company :");
			Integer idCompany ;
			try {
				idCompany = Integer.parseInt(scan.next());
				computerA.setCompanyId(idCompany);
				
			} catch (Exception e) {
				System.out.println("Error Enter the id of the company :");
				idCompany = null;
				//e.printStackTrace();
			}
			
		}
		computer.update(computerA);
		
	}
	
	
	
	
	
	
	
	
}
