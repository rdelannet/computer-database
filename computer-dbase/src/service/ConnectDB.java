package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {
	private static ConnectDB instance;
	private Connection connect;
	
	private String url = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "admincdb";
    private String password = "qwerty1234";

	public ConnectDB() throws SQLException {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.connect = DriverManager.getConnection(url, username, password);
           
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
		catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		
		return connect;
	}
	
	public final static ConnectDB getInstance() throws SQLException {
		if(instance == null) {
			instance = new ConnectDB();
		}else if( instance.getConnection().isClosed()) {
			instance = new ConnectDB();
		}
		return instance;
	}

	public static void main(String[] args) throws SQLException {
		ConnectDB  con = new ConnectDB();
		
	}
}
