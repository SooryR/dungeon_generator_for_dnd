package dungeon;
import soory.Chamber;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChamberTest {
    ChamberShape theShape;
    ChamberContents theContents;
    Monster myMonster;

    public ChamberTest() {
    }


	@Test
	public void testGetMonsters() {
		System.out.println("testGetMonsters");
		Chamber chamber = new Chamber();
		if(chamber != null) {
			boolean result = (chamber.getMonsters().size() >= 0);
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no chamber");
		}
	}
	
	@Test
	public void testGetDoors() {
		System.out.println("testGetDoors");
		ChamberShape shape = ChamberShape.selectChamberShape(1);
		shape.setNumExits();
		if (shape.getNumExits() == 5) {
			shape.setNumExits(8);
		}
		ChamberContents contents = new ChamberContents();
		contents.chooseContents(1);
		Chamber chamber = new Chamber(shape, contents);
		if(chamber != null) {
			boolean result = (chamber.getDoors().size() == shape.getNumExits());
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no chamber");
		}
	}
	
	@Test
	public void testGetTreasureList() {
		System.out.println("testGetTreasureList");
		ChamberShape shape = ChamberShape.selectChamberShape(1);
		shape.setNumExits();
		ChamberContents contents = new ChamberContents();
		contents.chooseContents(1);
		Chamber chamber = new Chamber(shape, contents);
		if(chamber != null) {
			boolean result = (chamber.getTreasureList().size() == 0);
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no chamber");
		}
	}
	
	@Test
	public void TestGetDescription() {
		System.out.println("TestGetDescription");
		ChamberShape shape = ChamberShape.selectChamberShape(1);
		shape.setNumExits();
		ChamberContents contents = new ChamberContents();
		contents.chooseContents(1);
		Chamber chamber = new Chamber(shape, contents);
		if(chamber != null) {
			boolean result = (chamber.getDescription().contains("Exit number "+shape.getNumExits()));
            boolean expResult = true;
            assertEquals(expResult, result);
		} else {
			fail("There is no chamber");
		}
	}
    /* set up similar to the sample in PassageTest.java */

    
}
