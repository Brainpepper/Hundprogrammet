// Lizardo Klavebäck Martinez likl2578@SU.SE

import java.util.ArrayList;
import java.util.Comparator;

public class Owner implements Comparable<Owner> {

    private String name;
    private ArrayList<Dog> dogs = new ArrayList<Dog>();

    public Owner(String name) {

        String[] names = name.split(" ");

        for (int i = 0; i < names.length; i++) {

            if (names[i].contains("-")) {

                String[] doubleParts = names[i].split("-");

                for (int x = 0; x < doubleParts.length; x++) {
                    doubleParts[x] = doubleParts[x].substring(0, 1).toUpperCase() + doubleParts[x].substring(1).toLowerCase();
                }

                names[i] = String.join("-", doubleParts);
            } else {
                names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1).toLowerCase();
            }
        }
        this.name = String.join(" ", names);
    }

    public String getName() {
        return this.name;
    }

    private String dogsToString() {
        String[] dogsArray = new String[dogs.size()];
        for (int i = 0; i < dogs.size(); i++) {
            dogsArray[i] = dogs.get(i).getName();
        }
        String result = String.join("\n", dogsArray);
        return result;
    }

    public String toString() {
        String s = "Owner: " + this.name + "\nDogs: " + "\n" + dogsToString();
        return s;
    }

    public int compareTo(Owner other) {
        return this.name.compareTo(other.name);
    }

    public boolean addDog(Dog dog) {
        if (dog == null || dogs.contains(dog) ) {
            return false;
        }

        if (dog.getOwner() == this) {
            if (!dogs.contains(dog)) {
                dogs.add(dog);
            }
            return true;
        } else if (dog.getOwner() != null) {
            return false;
        }
    
        dogs.add(dog);
        if (dog.getOwner() != this) {
            dog.setOwner(this);
        }
        return true;
    }
    
    public boolean removeDog(Dog dog) {
        if (dog != null && dogs.remove(dog)) {
            if (dog.getOwner() == this) {
                dog.setOwner(null);
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogListCopy = new ArrayList<Dog>(dogs);

        Comparator<Dog> dogComparator = new DogNameComparator();
        DogSorter.sortDogs(dogComparator, dogListCopy);

        return dogListCopy;
    }
    
}