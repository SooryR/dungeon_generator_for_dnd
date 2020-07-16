package dungeon;

import dnd.models.Exit;
import soory.Passage;
import soory.PassageSection;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;


public class PassageTest {
    //you don't have to use instance variables but it is easier
    // in many cases to have them and use them in each test
    private Passage testerOne;
    private Passage testerTwo;
    private PassageSection sectionOne;
    private PassageSection sectionTwo;

    public PassageTest() {
    }

	@Before
	public void setup(){
		//set up any instance variables here so that they have fresh values for every test
	}  

	@Test
	public void testAddPassageSection() {
		System.out.println("testAddPassageSection");
		testerOne = new Passage();
		sectionOne = new PassageSection("Monster");
		testerOne.addPassageSection(sectionOne);
		if(testerOne != null) {
			boolean result = (testerOne.getDescription().contains("Monster"));
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Passage");
		}
	}


	@Test
	public void testSetDoor() {
		System.out.println("testSetDoor");
		testerOne = new Passage();
		sectionOne = new PassageSection("Door archway");
		sectionTwo = new PassageSection("Door archway");
		testerOne.addPassageSection(sectionOne);
		testerOne.setDoor(sectionOne.getDoor());
		if(testerOne != null) {
			boolean result = (testerOne.checkIfConected(sectionTwo.getDoor()));
            boolean expResult = false;
            assertEquals(expResult, result);
		} else {
			fail("There is no Passage");
		}
	}
	
	@Test
	public void testGetMonster() {
		System.out.println("testGetMonster");
		testerOne = new Passage();
		sectionOne = new PassageSection("Monster");
		sectionTwo = new PassageSection("Monster");
		testerOne.addPassageSection(sectionOne);
		testerOne.addPassageSection(sectionTwo);
		if(testerOne != null) {
			boolean result = (testerOne.getMonster(0) != sectionTwo.getMonster());
			result = result && (testerOne.getMonster(1) == sectionTwo.getMonster());
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Passage");
		}
	}
	
	@Test
	public void testGetDoor() {
		System.out.println("testGetDoor");
		testerOne = new Passage();
		sectionOne = new PassageSection("Door archway");
		sectionTwo = new PassageSection("Door archway");
		testerOne.addPassageSection(sectionOne);
		testerOne.setDoor(sectionOne.getDoor());
		if(testerOne != null) {
			boolean result = (testerOne.getDoor(0) == sectionOne.getDoor());
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Passage");
		}
	}
	
}
