package computer_database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.dbunit.DatabaseUnitException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.formation.connect.ConnectDB;
import com.excilys.formation.dao.ComputerDAO;
import com.excilys.formation.model.Computer;

public class ComputerDAOTest {
	
	@InjectMocks private ConnectDB dbConnection;
	  @Mock private Connection mockConnection;
	  @Mock private Statement mockStatement;
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }

	@Test
	public void testCreateComputer() throws SQLException {
		String url = "jdbc:mysql://localhost/test-computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "testcdb";
		String password = "qwerty1234";
		LocalDate dateI = LocalDate.of(1998, 06, 15);
		LocalDate dateD = LocalDate.of(1999, 06, 15);
		
		Computer computer = new Computer("Apple",dateI,dateD,6);
		ConnectDB connect = new ConnectDB(url,username,password);
		ComputerDAO computers = new ComputerDAO(connect.getConnection());
		assertNotNull("Test",computers.create(computer));
	}
	@Test
	public void testFind() throws DatabaseUnitException, SQLException {
		//IDatabaseConnection dbUnit = new DatabaseConnection(dbConnection.getConnection());
		String url = "jdbc:mysql://localhost/test-computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "testcdb";
		String password = "qwerty1234";
		ConnectDB connect = new ConnectDB(url,username,password);
		ComputerDAO computers = new ComputerDAO(connect.getConnection());
		assertNotNull("Test",computers.find(1));
		connect.getConnection().close();
		
	}

}
