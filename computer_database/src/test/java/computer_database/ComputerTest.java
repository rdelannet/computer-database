package computer_database;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.formation.model.Computer;

public class ComputerTest {

	@Test
	public void TestgetId() {
		Computer computer = new Computer(1,"Apple");
		assertTrue("GetId fonctionne pas",computer.getId()==1);
	}
	@Test
	public void TestSetId() {
		Computer computer = new Computer();
		computer.setId(2);
		assertTrue("Set fonctionne pas",computer.getId()==2);
	}
	@Test
	public void TestSGetName() {
		String name = "Apple";
		Computer computer = new Computer(1,name);
		
		assertTrue("GetName fonctionne pas",name.equals(computer.getName()));
	}

}
