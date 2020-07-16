package soory;
import dnd.models.Exit;
import dnd.models.Trap;
import dnd.die.Die;
import java.util.ArrayList;
import java.util.Random;
public class Door {
    /**
     * if the door is an exit door, saves the exit in here.
     */
    private Exit myExit;
    /**
     * is the door traped.
     */
    private boolean traped;
    /**
     * the doors traps.
     */
    private Trap doorTrap;
    /**
     * doors descriptions.
     */
    private String doorDes;
    /**
     * is the door opened.
     */
    private boolean opened;
    /**
     * is the door locked.
     */
    private boolean locked;
    /**
     * is the door an archway.
     */
    private boolean isArch;
    /**
     * the doors conections.
     */
    private ArrayList<Space> myConections;  //this is the conector
    /**
     * random generator.
     */
    private Random numGen = new Random();
    /**
     * The Constructor for the door.
     */
    public Door() {
        //needs to set defaults
        setDefaults();
        rollArchway();
        if (isArch) {
            setLocked();
            if (Die.d20() == 1) {
                setTrapped(true);
            }
            createOpened();
        }
    }
    /**
     * The Constructor for the door.
     @param theExit sets the myExit to theExit
     */
    public Door(Exit theExit) {
        //sets up the door based on the Exit from the tables
        myExit = theExit;
        rollArchway();
        if (isArch) {
            setLocked();
            if (Die.d20() == 1) {
                setTrapped(true);
            }
            createOpened();
        }
    }
    /* note:  Some of these methods would normally be protected or private, but because we
    don't want to dictate how you set up your packages we need them to be public
    for the purposes of running an automated test suite (junit) on your code.  */
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to set up the trap of the room.
     @param flag is used to set the trap to exist or not.
     @param roll to set the trap to a specfied description or not.
     */
    public void setTrapped(boolean flag, int... roll) {
        // true == trapped.  Trap must be rolled if no integer is given
        traped = flag;
        if (flag) {
            doorTrap = new Trap();
            if (roll.length > 0) {
                doorTrap.chooseTrap(roll[0]);
            } else {
                doorTrap.chooseTrap(Die.d20());
            }
        }
    }
    private void setOpen(boolean flag) {
        //true == open
        if (!isArch) {
            opened = flag;
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to set if the door is an archway or not.
     @param flag is used to set the archway is true or not.
     */
    public void setArchway(boolean flag) {
        //true == is archway
        isArch = flag;
        if (flag) {
            locked = false;
            opened = true;
            traped = false;
        }
    }
    private void setDefaults() {
        traped = false;
        opened = false;
        locked = false;
        isArch = false;
        myConections = new ArrayList<Space>();
    }
    private void rollArchway() {
        if (Die.d10() == 1) {
            setArchway(true);
        } else {
            setArchway(false);
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to get if the door is trapped or not.
     @return true if the door is trapped else false.
     */
    public boolean isTrapped() {
        return traped;
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to get if the door is Open or not.
     @return true if the door is open else false.
     */
    public boolean isOpen() {
        return opened;
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to get if the door is an ArchWay or not.
     @return true if the door is an archway else false.
     */
    public boolean isArchway() {
        return isArch;
    }
    private String getTrapDescription() {
        if (traped) {
            return doorTrap.getDescription();
        } else {
            return null;
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to get the spaces conected to this door.
     @return the arraylist of spaces that this door conectes to.
     */
    public ArrayList<Space> getSpaces() {
        //returns the two spaces that are connected by the door
        return myConections;
    }
    private void setSpaces(Space spaceOne, Space spaceTwo) {
        myConections.add(spaceOne);
        myConections.add(spaceTwo);
        //identifies the two spaces with the door
        // this method should also call the addDoor method from Space
        if (!(spaceOne.checkIfConected(this))) {
            spaceOne.setDoor(this);
        }
        if (!(spaceTwo.checkIfConected(this))) {
            spaceTwo.setDoor(this);
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to get the description of the door.
     @return the descreption.
     */
    public String getDescription() {
        doorDes = new String();
        if (isArch) {
            doorDes = doorDes.concat("  ->the door is a archway\n");
        }
        if (traped) {
            doorDes = doorDes.concat("  ->there is a trap and it is: " + getTrapDescription() + "\n");
        }
        if (locked && !isArch) {
            doorDes = doorDes.concat("  ->the door is closed and locked\n");
        }
        if (opened && !locked) {
            doorDes = doorDes.concat("  ->the door is open\n");
        } else if (!locked && !isArch) {
            doorDes = doorDes.concat("  ->the door is closed\n");
        }
        return doorDes;
    }
/***********
You can write your own methods too, you aren't limited to the required ones.
*************/
    //sets if it is locked
    private void setLocked() {
        if (Die.d6() == 1) {
            locked = true;
        } else {
            locked = false;
        }
    }
    private void createOpened() {
        if (numGen.nextInt() % 2 == 1 && !locked) {
            setOpen(true);
        } else {
            setOpen(false);
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to conect one space to this door.
     @param spaceOne is the space that is going to connect to this door.
     */
    public void addOneSpaces(Space spaceOne) {
        myConections.add(spaceOne);
        if  (!(spaceOne.checkIfConected(this))) {
            spaceOne.setDoor(this);
        }
    }
    /*I made this public to it can be tested else it will be protected so that my classes will use it*/
    /**
     * Used to check is the space the connected to this door.
     @param space is the space that is going to be checked.
     @return true if the door is connected to the space else false.
     */
    public boolean checkIfConected(Space space) {
        for (Space connection : myConections) {
            if (connection == space) {
                return true;
            }
        }
        return false;
    }
}
