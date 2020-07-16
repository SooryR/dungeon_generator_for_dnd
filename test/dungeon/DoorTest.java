package dungeon;

import dnd.models.Trap;
import dnd.die.Die;
import soory.Passage;
import soory.PassageSection;
import soory.Chamber;
import soory.Door;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class DoorTest {
	private Passage testerOne;
    private Passage testerTwo;
    private Chamber c1;
    private Chamber c2;
    private PassageSection sectionOne;
    private PassageSection sectionTwo;
    private Door door1;
    public DoorTest() {
    }
    
   
    @Test
	public void testSetArchway() {
		System.out.println("testSetArchway");
		door1 = new Door();
		door1.setArchway(true);
		if(door1 != null) {
			boolean result = (door1.getDescription().contains("archway"));
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Door");
		}
	}
	
	
	@Test
	public void testAddOneSpaces() {
		System.out.println("testAddOneSpaces");
		door1 = new Door();
		c1 = new Chamber();
		door1.addOneSpaces(c1);
		if(door1 != null) {
			boolean result = (door1.checkIfConected(c1));
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Door");
		}
	}
	
	
	@Test
	public void testGetDescription() {
		System.out.println("testGetDescription");
		int roll = Die.d20();
		door1 = new Door();
		door1.setTrapped(true,roll);
		Trap t1 = new Trap();
		t1.chooseTrap(roll);
		if(door1 != null) {
			boolean result = (door1.getDescription().contains(t1.getDescription()));
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Door");
		}
	}
	
	@Test
	public void testGetSpaces() {
		System.out.println("testGetSpaces");
		door1 = new Door();
		c1 = new Chamber();
		door1.addOneSpaces(c1);
		if(door1 != null) {
			boolean result = (door1.getSpaces().get(0) == c1);
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no Door");
		}
	}
/* set up similar to the sample in PassageTest.java */
    
}
