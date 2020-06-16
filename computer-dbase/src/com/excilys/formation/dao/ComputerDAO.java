package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Computer;

public class ComputerDAO extends DAO<Computer>{

	public ComputerDAO(Connection conn) {
		super(conn);
		
	}

	public boolean create(Computer obj) {
		return false;
	}

	public boolean delete(Computer obj) {
		return false;
	}

	public boolean update(Computer obj) {
		
		return false;
	}

	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = " + id);
			if(result.first()) {
				computer = new Computer(id,result.getString("name"),result.getDate("introduced").toLocalDate(),
						result.getDate("discontinued").toLocalDate(),result.getInt("company_id"));
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
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			while(result.next()) {
				computers.add(new Computer(result.getInt("id"),result.getString("name"),result.getDate("introduced").toLocalDate(),
						result.getDate("discontinued").toLocalDate(),result.getInt("company_id")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	

}
