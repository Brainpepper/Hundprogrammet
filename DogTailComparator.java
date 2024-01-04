// Lizardo Klavebäck Martinez likl2578@SU.SE

public class DogTailComparator implements java.util.Comparator<Dog> {

    public int compare(Dog dogOne, Dog dogTwo) {
        if (dogOne.getTailLength() < dogTwo.getTailLength()) {
            return -1;
        } else if (dogOne.getTailLength() > dogTwo.getTailLength()) {
            return 1;
        } else {
            return 0;
        }
    }

}
