package com.excilys.formation.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.connect.ConnectDB;

public  abstract class DAO<T> {
	
	protected ConnectDB connect = null;
	
	public DAO(ConnectDB conn) {
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract List<T> findAll();
}
