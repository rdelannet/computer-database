package com.excilys.formation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Computer {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	private LocalDate introduced;
	@Column(name = "discontinued")
	private LocalDate discontinued;
	@ManyToOne
	@JoinColumn(nullable = true, name = "company_id")
	private Company company;

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

	public Computer(String name, LocalDate dateIn, LocalDate dateOut, Company company) {
		this.name = name;
		this.company = company;

		if (dateIn.isBefore(dateOut)) {
			this.introduced = dateIn;
			this.discontinued = dateOut;
		}
	}

	public Computer(String name, LocalDate dateIn, LocalDate dateOut) {

		this.name = name;
		if (dateIn.isBefore(dateOut)) {
			this.introduced = dateIn;
			this.discontinued = dateOut;
		}
	}

	public Computer(String name, Company company) {

		this.name = name;
		this.company = company;

	}

	public Computer(String name, LocalDate dateIn, Company company) {

		this.name = name;
		this.introduced = dateIn;
		this.company = company;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
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

	public Company getCompany() {
		if (company == null) {
			return null;
		}
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name= " + name + ", introduced= " + introduced + ", discontinued= "
				+ discontinued + ", companyId= " + company + "]";
	}

}