package soory;
public abstract class Space {
    public abstract String getDescription();
    public abstract void setDoor(Door theDoor);
    public abstract boolean checkIfConected(Door theDoor);
}
