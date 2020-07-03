package com.excilys.formation.connect;

import java.sql.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dao.ComputerDAO;



public class ConnectDB  {
	
	private static Connection connect;
	
	/*private String url = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "admincdb";
    private String password = "qwerty1234";*/
    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    
    private static HikariDataSource ds = new HikariDataSource(new HikariConfig("/datasource.properties"));

	/*public ConnectDB() throws SQLException {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.connect = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {
            logger.error("Database Connection Creation Failed : " + ex.getMessage());
        }
		catch(SQLException s) {
			logger.error("Error connect");
			s.printStackTrace();
		}
	}
	public ConnectDB(String urlt,String usernamet,String passwordt)throws SQLException{
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.connect = DriverManager.getConnection(urlt, usernamet, passwordt);

        } catch (ClassNotFoundException ex) {
            logger.error("Database Connection Creation Failed : " + ex.getMessage());
        }
		catch(SQLException s) {
			logger.error("Error connect");
			s.printStackTrace();
		}
	}*/
	
	

	
	public final static Connection getInstance() throws SQLException {
		if(connect == null || connect.isClosed()) {
			connect = ds.getConnection();
		}
		return connect;
	}
	 
		
		
		
	}
