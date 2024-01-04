import java.util.*;


public class DogTailComparatorTestProgram {

    public static void main(String[] args) {
        Dog dog1 = new Dog("Carl", "Tax", 5, 4);
        Dog dog2 = new Dog("Ha-ris", "Pug", 10, 2);
        Dog dog3 = new Dog("Hulda", "Schäfer", 6, 5);
        Dog dog4 = new Dog("Sven-Ivar", "Golden Retriever", 8, 3);

        List<Dog> dogs = Arrays.asList(dog1, dog2, dog3, dog4);

        Comparator<Dog> DogTailComparator = new DogTailComparator();

        dogs.sort(DogTailComparator);

        for (Dog dog : dogs) {
            System.out.println(dog.getName() + ": " + dog.getTailLength());
        }
    }
}
