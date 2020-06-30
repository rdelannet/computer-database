package com.excilys.formation.model;

import java.time.LocalDate;


public class Computer {
	
	 private int id;
	 private String name;
	 private LocalDate introduced;
	 private LocalDate discontinued;
	 private Integer companyId;
	 
	 public Computer() {
		 
	 }
	 
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Computer(int id,String name) {
		this.id = id;
		this.name = name;
	 }

	public Computer(int id,String name, LocalDate dateIn, LocalDate dateOut, int manufacturer) {
		this.name = name;
		this.companyId = manufacturer;
		this.id = id;
		if(dateIn.isBefore(dateOut)) {
			this.introduced = dateIn;
			this.discontinued = dateOut;
		}
	}
	
	public Computer(int id,String name,LocalDate dateIn,LocalDate dateOut) {
		this.id = id;
		this.name = name;
		if(dateIn.isBefore(dateOut)) {
			this.introduced = dateIn;
			this.discontinued = dateOut;
		}
	 }
	
	public Computer(int id,String name,int manufacturer) {
		this.id = id;
		this.name = name;
		this.companyId = manufacturer;
		
	 }
	public Computer(int id,String name,LocalDate dateIn,int manufacturer) {
		this.id = id;
		this.name = name;
		this.introduced = dateIn;
		this.companyId = manufacturer;
		
	 }





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + companyId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (companyId != other.companyId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateInt() {
		return introduced;
	}

	public void setDateInt(LocalDate date) {
		this.introduced = date;
	}

	public LocalDate getDateDisc() {
		return discontinued;
	}

	public void setDateDisc(LocalDate dateDisc) {
		this.discontinued = dateDisc;
	}

	public Integer getCompanyId() {
		if(companyId == null) {
			return (Integer) null;
		}
		return companyId;
	}

	public void setCompanyId(int manufacturer) {
		this.companyId = manufacturer;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name= " + name + ", introduced= " + introduced + ", discontinued= " + discontinued
				+ ", companyId= " + companyId + "]";
	}
	 
	
	 
}