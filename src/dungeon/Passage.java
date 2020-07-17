package soory;
import dnd.models.Monster;
import java.util.ArrayList;
import java.util.HashMap;

public class Passage extends Space {
    private String theDes;
    private ArrayList<PassageSection> thePassage;
    private ArrayList<Door> theDoors;
    private HashMap<Door, PassageSection> doorMap;
public Passage() {
    thePassage = new ArrayList<PassageSection>();
    theDoors = new ArrayList<Door>();
    doorMap = new HashMap<Door, PassageSection>();
    theDes = new String();
}
private ArrayList<Door> getDoors() {
    return theDoors;
//gets all of the doors in the entire passage
}
public Door getDoor(int i) {
    //returns the door in section 'i'. If there is no door, returns null
    if (theDoors.size() <= i) {
        return null;
    }
    return theDoors.get(i);
}
private void addMonster(Monster theMonster, int i) {
    // adds a monster to section 'i' of the passage
    if (thePassage.size() >= i) {
        thePassage.get(i).setMonster(theMonster);
    }
}
public Monster getMonster(int i) {
    if (thePassage.size() < i) {
        return null;
    }
    return thePassage.get(i).getMonster();
    //returns Monster door in section 'i'. If there is no Monster, returns null
}
public void addPassageSection(PassageSection toAdd) {
    thePassage.add(toAdd);
    //adds the passage section to the passageway
}
@Override
public void setDoor(Door newDoor) {
    theDoors.add(newDoor);
    //add a door connection to the current Passage Section
}
@Override
public String getDescription() {
    for (PassageSection x: thePassage) {
        theDes = theDes.concat("->" + x.getDescription() + "\n");
        addMonsterDes(x);
        addDoorDes(x);
        addStairsDes(x);
    }
    return theDes;
}
private void addMonsterDes(PassageSection x) {
    if (x.getMonster() != null) {
        theDes = theDes.concat("  ->" + x.getMonster().getDescription() + "\n");
    }
}
private void addDoorDes(PassageSection x) {
    if (x.getDoor() != null) {
        theDes = theDes.concat("" + x.getDoor().getDescription() + "\n");
    }
}
private void addStairsDes(PassageSection x) {
    if (x.getStairs() != null) {
        theDes = theDes.concat("  ->" + x.getStairs().getDescription() + "\n");
    }
}

@Override
public boolean checkIfConected(Door thedoor) {
    for (Door door : theDoors) {
        if (door == thedoor) {
            return true;
        }
    }
    return false;
}
}
