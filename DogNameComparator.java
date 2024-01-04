// Lizardo Klavebäck Martinez likl2578@SU.SE

public class DogNameComparator  implements java.util.Comparator<Dog>{
    
    public int compare(Dog dogOne, Dog dogTwo) {
        return dogOne.getName().compareTo(dogTwo.getName());
    }
}
