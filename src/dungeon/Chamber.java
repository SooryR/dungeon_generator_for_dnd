package soory;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Exit;
import dnd.models.Treasure;
import dnd.die.Die;
import java.util.ArrayList;
public class Chamber extends Space {
    private ChamberContents myContents;
    private ChamberShape mySize;
    private String desChamber;
    private ArrayList<Monster> myMonsters;
    private ArrayList<Treasure> myTreasure;
    private ArrayList<Door> doorList;
public Chamber() {
    myContents = new ChamberContents();
    doorList = new ArrayList<Door>();
    myTreasure = new ArrayList<Treasure>();
    myMonsters = new ArrayList<Monster>();
    mySize = ChamberShape.selectChamberShape(Die.d20());
    mySize.setNumExits();
    setContants();
    genDoors();
}
public Chamber(ChamberShape theShape, ChamberContents theContents) {
    myContents = theContents;
    mySize = theShape;
    doorList = new ArrayList<Door>();
    myTreasure = new ArrayList<Treasure>();
    myMonsters = new ArrayList<Monster>();
    genDoors();
}
public void setShape(ChamberShape shape) {
    mySize = shape;
    genDoors();
}
public ArrayList<Door> getDoors() {
    return doorList;
}
private void addMonster(Monster theMonster) {
    myMonsters.add(theMonster);
}
public ArrayList<Monster> getMonsters() {
    return myMonsters;
}
private void addTreasure(Treasure theTreasure) {
    myTreasure.add(theTreasure);
}
public ArrayList<Treasure> getTreasureList() {
    return myTreasure;
}
@Override
public String getDescription() {
    desChamber = new String();
    desChamber = desChamber.concat("->The area of the room is : " + mySize.getArea() + "\n->The shape of the room is : " + mySize.getShape());
    desChamber = desChamber.concat("\n");
    addChamberContentsDes();
    addChamberMonsterDes();
    addChamberTreasureDes();
    addChamberExitDes();
    return desChamber;
}
private void addChamberContentsDes() {
    desChamber = desChamber.concat("->The contents of this chamber: ");
    desChamber = desChamber.concat(myContents.getDescription());
    desChamber = desChamber.concat("\n");
}
private void addChamberMonsterDes() {
    if (myMonsters.size() > 0) {
        desChamber = desChamber.concat("->The chamber is protected by: " + myMonsters.get(0).getDescription() + "\n");
        desChamber = desChamber.concat("->The number of monster are between " + myMonsters.get(0).getMinNum() + "-" + myMonsters.get(0).getMaxNum() + "\n");
    }
}
private void addChamberTreasureDes() {
    if (myTreasure.size() > 0) {
        desChamber = desChamber.concat("->The description of the treasure : " + myTreasure.get(0).getDescription() + "\n");
        try {
            desChamber = desChamber.concat("->The treasure is guarded by: " + myTreasure.get(0).getProtection() + "\n");
        } catch (Exception e) {
            desChamber = desChamber.concat("->The treasure is not guarded\n");
        }
    }
}
private void addChamberExitDes() {
    desChamber = desChamber.concat("->The number of exits in the room : " + mySize.getNumExits() + "\n");
    int i = 1;
    for (Exit x: mySize.getExits()) {
        desChamber = desChamber.concat("->Exit number " + i + " is at the " + x.getLocation() + " in the direction " + x.getDirection() + "\n");
        desChamber = desChamber.concat("  Exit Door" + i + ":\n" + doorList.get(i - 1).getDescription());
        i++;
    }
}
@Override
public void setDoor(Door newDoor) {
    if (!newDoor.checkIfConected(this)) {
        doorList.add(newDoor);
        newDoor.addOneSpaces(this);
    }
}
private void setContants() {
    myContents.chooseContents(Die.d20());
}
private void genDoors() {
    Door exitDoor;
    doorList = new ArrayList<Door>();
    if (mySize.getNumExits() == 5) {
        mySize.setNumExits(8);
    }
    for (int x = 0; x < mySize.getNumExits(); x++) {
        exitDoor = new Door();
        setDoor(exitDoor);
    }
}
@Override
public boolean checkIfConected(Door thedoor) {
    for (Door door : doorList) {
        if (door == thedoor) {
            return true;
        }
    }
    return false;
}
}
