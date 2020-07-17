package soory;
import dnd.die.Die;
import dnd.models.Stairs;
import dnd.models.Monster;
/* Represents a 10 ft section of passageway */
/*gen a 2 sec passage*/
public class PassageSection {
    private boolean isdoor;
    private Door thedoor;
    private Monster theMonster;
    private boolean deadEnd;
    private String descriptionP;
    private Stairs pStairs;
public PassageSection() {
    isdoor = false;
    thedoor = null;
    theMonster = null;
    deadEnd = false;
    descriptionP = null;
    //sets up the 10 foot section with default settings
}
public PassageSection(String description) {
    descriptionP = new String();
    descriptionP = description;
    setDescriptionMonster(descriptionP);
    setDescriptionDeadEnd(descriptionP);
    setDescriptionDoor(descriptionP);
    setDescriptionStairs(descriptionP);
    //sets up a specific passage based on the values sent in from
    //modified table 1
}
public Door getDoor() {
    return thedoor;
    //returns the door that is in the passage section, if there is one
}
public Monster getMonster() {
    return theMonster;
    //returns the monster that is in the passage section, if there is one
}
public String getDescription() {
    return descriptionP;
}
public void setMonster(Monster inputMon) {
    if (inputMon == null) {
        theMonster.setType(Die.percentile());
    } else {
        theMonster = inputMon;
    }
}
private void createDoor() {
    thedoor = new Door();
}
private void setDeadEnd(boolean flag) {
    deadEnd = flag;
}
private void createStairsType() {
    pStairs.setType(Die.d20());
}
public Stairs getStairs() {
    return pStairs;
}
private void setDescriptionMonster(String des) {
    if (des.contains("Monster")) {
		Monster newMonster = new Monster();
        setMonster(newMonster);
    }
}
private void setDescriptionDeadEnd(String des) {
    if (des.contains("Dead End")) {
        deadEnd = true;
    }
}
private void setDescriptionDoor(String des) {
    if (des.contains("Door") || des.contains("door")) {
        isdoor = true;
        createDoor();
        if (des.contains("archway")) {
            thedoor.setArchway(true);
        }
    }
}
private void setDescriptionStairs(String des) {
    if (des.contains("Stairs")) {
        pStairs = new Stairs();
        createStairsType();
    }
}
}
