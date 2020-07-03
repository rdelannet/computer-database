package com.excilys.formation.connect;


import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionH2 {
	
	private static Connection connect;
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionH2.class);
	
	private static HikariDataSource ds;
	
	public ConnectionH2() {
		ds = new HikariDataSource(new HikariConfig("/datasource.properties"));
	}
	
	
	public Connection getInstance() throws SQLException {
		if(connect == null || connect.isClosed()) {
			try {
				connect = ds.getConnection();
			} catch(SQLException eSQL) {
				logger.error("Error connection to DB",eSQL);
				
			}
		}
		return connect;
	}
}
