// Lizardo Klavebäck Martinez likl2578@SU.SE

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class DogCollection {
    
    private ArrayList<Dog> dogs = new ArrayList<Dog>();

    public boolean addDog(Dog dog) {
        if (!this.containsDog(dog)) {
            dogs.add(dog);
            return true;
        }
        return false;
    }

    public boolean removeDog(String dogName) {
        Dog dogToRemove = this.getDog(dogName);
        
        if (dogToRemove == null) {
            return false;  
        }

        if (dogToRemove.getOwner() != null) {
            return false;
        }

        dogs.remove(dogToRemove);
        return true;
    }
    

    public boolean removeDog(Dog dog) {
        if (dog == null || !dogs.contains(dog)) {
            return false;  
        } 

        if (dog.getOwner() != null) {
            return false;
        }

            dogs.remove(dog);
            return true;

    }

    public boolean containsDog(String dogName) {
        if (this.getDog(dogName) != null) {
           return true;
       } 
         return false;
    }

    public boolean containsDog(Dog dog) {
       if (this.getDog(dog.getName()) != null) {
           return true;
       } 
         return false;
    }

    public Dog getDog(String dogName) {
        for(int i = 0; i < dogs.size(); i++) {
            if(dogs.get(i).getName().equalsIgnoreCase(dogName)) {
                return dogs.get(i);
            }
        }
        return null;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogListCopy = new ArrayList<Dog>(dogs);

        Comparator<Dog> dogComparator = new DogNameComparator();
        DogSorter.sortDogs(dogComparator, dogListCopy);

        return dogListCopy;
    }

    public ArrayList<Dog> getDogsWithTailLengthOrMore(double tailLength) {
        ArrayList<Dog> dogListCopy = new ArrayList<Dog>(dogs);

        Comparator<Dog> dogComparator = new DogTailComparator();
        DogSorter.sortDogs(dogComparator, dogListCopy);

        Iterator<Dog> iterator = dogListCopy.iterator();
        while (iterator.hasNext()) {
            Dog dog = iterator.next();
            if (dog.getTailLength() < tailLength) {
                iterator.remove();
            }
        }

        return dogListCopy;
    }
    
}