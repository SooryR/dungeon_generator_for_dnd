package dungeon;
import dnd.models.Exit;
import dnd.models.Monster;
import org.junit.Test;
import soory.Door;
import soory.PassageSection;
import soory.Passage;
import static org.junit.Assert.*;
public class PassageSectionTest {
    
/* set up similar to the sample in PassageTest.java */
	private Passage testerOne;
    private Passage testerTwo;
    private PassageSection sectionOne;
    private PassageSection sectionTwo;
    public PassageSectionTest() {
    }



	@Test
	public void testSetDescriptionMonster() {
		System.out.println("testSetDescriptionMonster");
		sectionOne = new PassageSection("Monster");
		if(sectionOne != null) {
			boolean result = (sectionOne.getDescription().contains("Monster"));
			boolean result2 = result && (sectionOne.getMonster() != null);
            boolean expResult = true;
            assertEquals(expResult, result2);
		} else {
			fail("There is no PassageSection");
		}
	}
	
	@Test
	public void testGetStairs() {
		System.out.println("testGetStairs");
		sectionOne = new PassageSection("Stairs");
		if(sectionOne != null) {
			boolean result = (sectionOne.getDescription().contains("Stairs"));
			boolean result2 = result && (sectionOne.getStairs() != null);
            boolean expResult = true;
            assertEquals(expResult, result2);
		} else {
			fail("There is no PassageSection");
		}
	}
    
    @Test
	public void testGetDoor() {
		System.out.println("testGetDoor");
		sectionOne = new PassageSection("Door archway");
		if(sectionOne != null) {
			boolean result = (sectionOne.getDescription().contains("archway"));
			boolean result2 = result && (sectionOne.getDoor() != null) && true;
			result2 = result2 && (sectionOne.getDoor().isArchway() == true) && true;
            boolean expResult = true;
            assertEquals(expResult, result2);
		} else {
			fail("There is no PassageSection");
		}
	}
    
    
    @Test
	public void testGetDescription() {
		System.out.println("testGetDescription");
		sectionOne = new PassageSection("lol test this mofo");
		if(sectionOne != null) {
			boolean result = (sectionOne.getDescription().contains("lol testa this mofo"));
            boolean expResult = false;
            assertEquals(expResult, result);
		} else {
			fail("There is no PassageSection");
		}
	}
    
}
