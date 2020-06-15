package model;

import java.time.LocalDate;

public class Computer {
	
	 private String name;
	 private LocalDate dateIn;
	 private LocalDate dateOut;
	 private Company manufacturer;
	 
	 public Computer(String name) {
		 this.name = name;
	 }

	public Computer(String name, LocalDate dateIn, LocalDate dateOut, Company manufacturer) {
		this.name = name;
		this.manufacturer = manufacturer;
		
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
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
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
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
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

	public Company getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}
	 
	@Override
	public String toString() {
		return "Computer [name=" + name + ", dateIn=" + dateIn + ", dateOut=" + dateOut + ", manufacturer="
				+ manufacturer + "]";
	}
	 
}