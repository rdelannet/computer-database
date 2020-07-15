package com.excilys.formation.connect;

import java.sql.*;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.ComputerDAO;


@Component
public class ConnectDB  {
	
	private static Connection connect;
	
	/*private String url = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "admincdb";
    private String password = "qwerty1234";*/
    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    
    private  DataSource hikariDataSource;

    @Autowired
	public ConnectDB(DataSource hikariDataSource) {
		this.hikariDataSource = hikariDataSource;
	}
	

	
	public synchronized Connection getInstance() throws SQLException {
		if(connect == null || connect.isClosed()) {
			connect = hikariDataSource.getConnection();
		}
		return connect;
	}



	public DataSource getHikariDataSource() {
		return hikariDataSource;
	}


	 
		
		
		
	}
