package computer_database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.formation.connect.ConnectDB;

public class TestConnect {

	@InjectMocks private ConnectDB dbConnection;
	  @Mock private Connection mockConnection;
	  @Mock private Statement mockStatement;
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	 
	  @Test
	  public void testMockDBConnection() throws Exception {
	    Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
	    Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
	    
	    Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	  }

}