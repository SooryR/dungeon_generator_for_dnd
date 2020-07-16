package soory;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        int[] numExits = new int[5];
        int numberOfExits = 0;
        ArrayList<Chamber> chambers;
        ArrayList<Passage> passages;
        passages = new ArrayList<Passage>();
        HashMap<Door, ArrayList<Chamber>> chamberMap;
        chambers = new ArrayList<Chamber>();
        Chamber c1 = new Chamber();
        Chamber c2 = new Chamber();
        Chamber c3 = new Chamber();
        Chamber c4 = new Chamber();
        Chamber c5 = new Chamber();
        chambers.add(c1);
        chambers.add(c2);
        chambers.add(c3);
        chambers.add(c4);
        chambers.add(c5);
        int i = 0;
        int j = 0;
        for (Chamber x: chambers) {
            numExits[i] = x.getDoors().size();
            numberOfExits += numExits[i];
            i++;
        }
        for (i = 0; i < 5; i++) {
            if (numExits[i] > 0) {
                for (j = 0; j < 5; j++) {
                    if (numExits[i] == 0) {
                        break;
                    }
                    if (j == i) {
                        j++;
                    }
                    if (j > 4) {
                        break;
                    }
                    if (!checkChamberConected(chambers.get(i), chambers.get(j)) && numExits[j] > 0) {
                        conect(chambers.get(i), chambers.get(j), passages);
                        numExits[i] -= 1;
                        numExits[j] -= 1;
                    }
                }
                if (numExits[i] != 0) {
                    for (j = 0; j < 5; j++) {
                        if (numExits[i] == 0) {
                            break;
                        }
                        if (j == i) {
                            j++;
                        }
                        if (j > 4) {
                            break;
                        }
                        if (!checkChamberConected(chambers.get(i), chambers.get(j))) {
                            forceConect(chambers.get(i), chambers.get(j), passages);
                            numExits[i] -= 1;
                        }
                    }
                }
            }
        }
        for (Chamber chamber: chambers) {
            System.out.println(chamber.getDescription());
            printChamberConections(chamber, chambers);
        }
    }
    public static void printChamberConections(Chamber c1, ArrayList<Chamber> chambers) {
        System.out.println("this chamber is connected to: ");
        for (int i = 0; i < 5; i++) {
            if (checkChamberConected(c1, chambers.get(i))) {
                System.out.print("chamber " + (i + 1) + ", ");
            }
        }
        System.out.println("\n");
    }
    public static void forceConect(Chamber c1, Chamber c2, ArrayList<Passage> passages) {
        Passage passage = new Passage();
        passages.add(passage);
        c1.getDoors().get(0).addOneSpaces(passages.get(passages.size() - 1));
        c2.getDoors().get(0).addOneSpaces(passages.get(passages.size() - 1));
    }
    public static void conect(Chamber c1, Chamber c2, ArrayList<Passage> passages) {
        Passage passage = new Passage();
        PassageSection passageSection = new PassageSection("door");
        passage.addPassageSection(passageSection);
        passages.add(passage);
        int x = 0;
        int y = 0;
        for (Door doorC1: c1.getDoors()) {
            if (doorC1.getSpaces().size() == 1) {
                break;
            }
            x++;
        }
        for (Door doorC2: c2.getDoors()) {
            if (doorC2.getSpaces().size() == 1) {
                break;
            }
            y++;
        }
        if (c1.getDoors().size() != x && c2.getDoors().size() != y) {
            c1.getDoors().get(x).addOneSpaces(passages.get(passages.size() - 1));
            c2.getDoors().get(y).addOneSpaces(passages.get(passages.size() - 1));
        }
    }
    public static boolean checkChamberConected(Chamber c1, Chamber c2) {
        for (Door doorC1: c1.getDoors()) {
            if (doorC1.getSpaces().size() > 1) {
                for (Door doorC2: c2.getDoors()) {
                    for (int i = 1; i < doorC1.getSpaces().size(); i++) {
                        if (doorC1.getSpaces().get(i).checkIfConected(doorC2) && (doorC1 != doorC2)) {
                            return true;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return false;
    }
}
