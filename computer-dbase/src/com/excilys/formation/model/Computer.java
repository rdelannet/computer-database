package com.excilys.formation.model;

import java.time.LocalDate;


public class Computer {
	
	 private int id;
	 private String name;
	 private LocalDate dateIn;
	 private LocalDate dateOut;
	 private int manufacturer;
	 
	 public Computer() {
		 
	 }
	 
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Computer(String name) {
		 this.name = name;
	 }

	public Computer(int id,String name, LocalDate dateIn, LocalDate dateOut, int manufacturer) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.id = id;
		
		if(dateIn.isBefore(dateOut)) {
			this.dateIn = dateIn;
			this.dateOut = dateOut;
		}
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + id;
		result = prime * result + manufacturer;
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
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (id != other.id)
			return false;
		if (manufacturer != other.manufacturer)
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

	public LocalDate getDate() {
		return dateIn;
	}

	public void setDate(LocalDate date) {
		this.dateIn = date;
	}

	public LocalDate getDateDisc() {
		return dateOut;
	}

	public void setDateDisc(LocalDate dateDisc) {
		this.dateOut = dateDisc;
	}

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}
	 
	@Override
	public String toString() {
		return "Computer [name=" + name + ", dateIn=" + dateIn + ", dateOut=" + dateOut + ", manufacturer="
				+ manufacturer + "]";
	}
	 
}