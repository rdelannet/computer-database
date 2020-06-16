package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.model.Computer;

public class ComputerDAO extends DAO<Computer>{

	public ComputerDAO(ConnectDB conn) {
		super(conn);
		
	}

	public boolean create(Computer computer)  {
		String sql = "INSERT INTO computer(id,name) values (?,?)";
		try {
			PreparedStatement statement = this.connect.getConnection().prepareStatement(sql);
			statement.setInt(1, computer.getId());
			statement.setString(2, computer.getName());
			statement.executeUpdate();
			
			Integer computerId = computer.getId();
			computer.setId(computerId);
			this.update(computer);
			computer = this.find(computerId);
					
		}catch(SQLException eSQL) {
			System.out.println("Error Created Computer");
			System.out.println(eSQL.getMessage());
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(Computer computer) {
		try {
			String sql = "DELETE FROM computer WHERE id = ?";
			PreparedStatement statement = this.connect.getConnection().prepareStatement(sql);
			statement.setInt(1, computer.getId());
			statement.executeUpdate();
		}catch(SQLException eSQL) {
			System.out.println("Error Delete Computer");
			eSQL.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Computer computer) {
		
		try {
			String sql = "UPDATE computer SET id = '" + computer.getId() + "'" + " , name = '" + computer.getName() + "'";
			if(computer.getDateInt() != null) {
				sql += ", introduced = '" + Date.valueOf(computer.getDateInt()) + "'";
			}			
			if(computer.getDateDisc() != null) {
				sql += ", discontinued = '" + Date.valueOf(computer.getDateDisc()) + "'";
			}		
			if(computer.getCompanyId() != null) {
				sql += ", company_id = '" +computer.getCompanyId() + "'"; 
			}		
			sql += " WHERE id = "+ computer.getId();
			System.out.println(sql);
			this.connect.getConnection().createStatement().
				executeUpdate(sql);
			computer = this.find(computer.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.getConnection().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
			if(result.first()) {
				computer = new Computer(id,result.getString("name"));
				if(result.getDate("introduced") != null) {
					computer.setDateInt(result.getDate("introduced").toLocalDate());
				}
				if(result.getDate("discontinued") != null) {
					computer.setDateDisc(result.getDate("discontinued").toLocalDate());
				}
				if(result.getInt("company_id") != 0) {
					computer.setCompanyId(result.getInt("company_id"));
				}
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = this.connect.getConnection().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			while(result.next()) {
				Computer computer = new Computer();
				computer.setId(result.getInt("id"));
				computer.setName(result.getString("name"));
				if(result.getDate("introduced") != null) {
					computer.setDateInt(result.getDate("introduced").toLocalDate());
				}
				if(result.getDate("discontinued") != null) {
					computer.setDateDisc(result.getDate("discontinued").toLocalDate());
				}
				if(result.getInt("company_id") != 0) {
					computer.setCompanyId(result.getInt("company_id"));
				}
				computers.add(computer);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	

}
