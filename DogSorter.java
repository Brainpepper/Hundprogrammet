// Lizardo Klavebäck Martinez likl2578@SU.SE

import java.util.*;

public class DogSorter {
    
    private static void swapDogs(ArrayList<Dog> dogs, int i, int j) {
        Dog temp = dogs.get(i);
        dogs.set(i, dogs.get(j));
        dogs.set(j, temp);
    }
    
    private static int nextDog(Comparator<Dog> dogComparator, ArrayList<Dog> dogs, int startIndex) {
        int minIndex = startIndex;
        for (int j = startIndex + 1; j < dogs.size(); j++) {
            if (dogComparator.compare(dogs.get(j), dogs.get(minIndex)) < 0) {
                minIndex = j;
            }
        }
        return minIndex;
    }

    public static int sortDogs(Comparator<Dog> dogComparator, ArrayList<Dog> dogs) {
        int swapCount = 0;
        for (int i = 0; i < dogs.size() - 1; i++) {
            int nextDogIndex = nextDog(dogComparator, dogs, i);
            if (nextDogIndex != i) {
                swapDogs(dogs, i, nextDogIndex);
                swapCount++;
            }
        }
        return swapCount;
    }
    
}
