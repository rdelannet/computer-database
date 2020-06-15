package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Database {
	List<Company> companies = new LinkedList<Company>();
	List<Computer> computers = new ArrayList<Computer>();
	
	public Database(List<Company> companies, List<Computer> computers) {
		super();
		this.companies = companies;
		this.computers = computers;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
	
	public void addComputer(Computer computer) {
		computers.add(computer);
	}
	public void removeComputer(Computer computer) {
		for(Computer c : computers) {
			if(c.equals(computer)) {
				computers.remove(computer);
			}
		}
		
	}
	
	public void printCompanies(Company...compa) {
		for(int i = 0; i < compa.length;i++) {
			if(compa[i].equals(companies.get(i))) {
				System.out.println(compa);
			}
			else {
				System.out.println("Pas dans la base");
			}
		}
	}

	
	
	
	
}
