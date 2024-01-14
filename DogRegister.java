// Lizardo Klavebäck Martinez likl2578@SU.SE

import java.util.ArrayList;

public class DogRegister {

    private static final String REGISTER_OWNER = "register new owner";
    private static final String REMOVE_OWNER = "remove owner";
    private static final String REGISTER_DOG = "register new dog";
    private static final String REMOVE_DOG = "remove dog";
    private static final String LIST_DOGS = "list dogs";
    private static final String LIST_OWNERS = "list owners";
    private static final String INCREASE_AGE = "increase age";
    private static final String GIVE_DOG_TO_OWNER = "give dog to owner";
    private static final String REMOVE_DOG_FROM_OWNER = "remove dog from owner";
    private static final String EXIT_COMMAND = "exit";
    

    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();

    private InputReader input = new InputReader();
    private boolean running = true;

    private void start() {
        initialize();
        runCommandLoop();
        shutdown();
    }

    private void initialize() {
        System.out.println("Initializing program");
    }

    private void runCommandLoop() {
        while (running) {
            String command = readCommand().toLowerCase();
            handleCommand(command);
        }
    }

    private String readCommand() {
        return input.readText("Enter command").toLowerCase();
    }

    private void handleCommand(String command) {
        switch(command) {
            case REGISTER_OWNER:
                registerOwnerMethod();
                break;
            case REMOVE_OWNER:
                removeOwnerMethod();
                break;
            case REGISTER_DOG:
                registerDogMethod();
                break;
            case REMOVE_DOG:
                removeDogMethod();
                break;
            case LIST_DOGS:
                listDogsMethod();
                break;
            case LIST_OWNERS:
                listOwnersMethod();
                break;
            case INCREASE_AGE:
                increaseAgeMethod();
                break;
            case GIVE_DOG_TO_OWNER:
                giveDogToOwnerMethod();
                break;
            case REMOVE_DOG_FROM_OWNER:
                removeDogFromOwnerMethod();
                break;
            case EXIT_COMMAND:
                running = false;
                return;
            default:    
                System.out.println("Error: Wrong command!");
        }
    }

    private void registerOwnerMethod() {
        String name = input.readText("Name");

        name = handleBlankString(name, "Name");

        if (ownerCollection.containsOwner(name)) {
            System.out.println("Error: The owner " + ownerCollection.getOwner(name).getName() + " already exists");
            return;
        }

        Owner newOwner = new Owner(name);     
        ownerCollection.addOwner(newOwner);
        System.out.println("The owner " + newOwner.getName() + " has been added to the register");
    }

    private void removeOwnerMethod() {
        if (ownerCollection.getOwners().size() == 0) {
            System.out.println("Error: No owners in register");
            return;
        }
        
        String name = input.readText("Name");
        
        name = handleBlankString(name, "Name");

        if (!ownerCollection.containsOwner(name)) {
            System.out.println("Error: The owner " + name + " does not exist");
            return;
        }

        Owner owner = ownerCollection.getOwner(name);

        for (Dog dog : owner.getDogs()) {
            dog.setOwner(null);
            dogCollection.removeDog(dog);
        }

        ownerCollection.removeOwner(owner);
        System.out.println("The owner " + owner.getName() + " has been removed from the register");
    }

    private void registerDogMethod() {
        String name = input.readText("Name");

        name = handleBlankString(name, "Name");

        if (dogCollection.containsDog(name)) {
            System.out.println("Error: The dog " + dogCollection.getDog(name).getName()  + " already exists");
            return;
        }
        
        String breed = input.readText("Breed");

        breed = handleBlankString(breed, "Breed");

        int age = input.readInteger("Age");
        int weight = input.readInteger("Weight");

        Dog newDog = new Dog(name, breed, age, weight);
        dogCollection.addDog(newDog);
        System.out.println("The dog " + newDog.getName() + " has been added to the register");
    }

    private void removeDogMethod() {
        if (dogCollection.getDogs().size() == 0) {
            System.out.println("Error: No dogs in register");
            return;
        }

        String name = input.readText("Name");

        name = handleBlankString(name, "Name");

        if (!dogCollection.containsDog(name)) {
            System.out.println("Error: The dog " + name + " does not exist");
            return;
        }

        Dog dog = dogCollection.getDog(name);

        if (dog.getOwner() != null) {
            dog.getOwner().removeDog(dog);
        }

        dogCollection.removeDog(dog);
        System.out.println("The dog " + dog.getName() + " has been removed from the register");
    }

    private void listDogsMethod() {
        ArrayList<Dog> dogs = dogCollection.getDogs();

        if (dogs.isEmpty()) {
            System.out.println("Error: No dogs in register");
            return;
        }

        double tailLength = input.readDouble("Enter minimum tail length");

        if (tailLength < 0) {
            System.out.println("Error: The tail length must be a positive number");
            return;
        }

        dogs = dogCollection.getDogsWithTailLengthOrMore(tailLength);

        System.out.printf("%-10s %-10s %-5s %-5s %-5s %-10s%n", "Namn", "Ras", "Ålder", "Vikt", "Svans", "Ägare");
        System.out.println("=============================================");

        for (Dog dog : dogs) {
            String ownerName = (dog.getOwner() != null) ? dog.getOwner().getName() : "";
            System.out.printf("%-10s %-10s %-5d %-5d %-5.1f %-10s%n",
                    dog.getName(),
                    dog.getBreed(),
                    dog.getAge(),
                    dog.getWeight(),
                    dog.getTailLength(),
                    ownerName);
        }
    }

    private void listOwnersMethod() {
        ArrayList<Owner> owners = ownerCollection.getOwners();

        if (owners.isEmpty()) {
            System.out.println("Error: No owners in register");
            return;
        }
    
        System.out.printf("%-10s %-10s%n", "Namn", "Hundar");
        System.out.println("========================================");
    
        for (Owner owner : owners) {
            StringBuilder dogNames = new StringBuilder();
            for (Dog dog : owner.getDogs()) {
                if (dogNames.length() > 0) {
                    dogNames.append(", ");
                }
                dogNames.append(dog.getName());
            }
    
            System.out.printf("%-10s %-10s%n",
            owner.getName(),
            dogNames.toString());
        }
    }
    
    private void giveDogToOwnerMethod() {
        if (dogCollection.getDogs().size() == 0) {
            System.out.println("Error: No dogs in register");
            return;
        } 
        
        if (ownerCollection.getOwners().size() == 0) {
            System.out.println("Error: No owners in register");
            return;
        }

        String dogName = input.readText("Name of dog");

        dogName = handleBlankString(dogName, "Name of dog");

        if (!dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " does not exist");
            return;
        }

        if (dogCollection.getDog(dogName).getOwner() != null) {
            System.out.println("Error: The dog " + dogName + " already has an owner");
            return;
        }

        String ownerName = input.readText("Name of owner");

        if (ownerCollection.getOwners().size() == 0) {
            System.out.println("Error: No owners in register");
            return;
        }

        ownerName = handleBlankString(ownerName, "Name of owner");

        if (!ownerCollection.containsOwner(ownerName)) {
            System.out.println("Error: The owner " + ownerName + " does not exist");
            return;
        }

        Dog dog = dogCollection.getDog(dogName);
        Owner owner = ownerCollection.getOwner(ownerName);

        owner.addDog(dog);
        dog.setOwner(owner);
        System.out.println("The dog " + dog.getName() + " is now owned by " + owner.getName());
    }

    private void removeDogFromOwnerMethod() {

        if (dogCollection.getDogs().size() == 0) {
            System.out.println("Error: No dogs in register");
            return;
        } 
        
        if (ownerCollection.getOwners().size() == 0) {
            System.out.println("Error: No owners in register");
            return;
        }

        String dogName = input.readText("Name of dog");

        dogName = handleBlankString(dogName, "Name of dog");

        if (!dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " does not exist");
            return;
        }

        if (dogCollection.getDog(dogName).getOwner() == null) {
            System.out.println("Error: The dog " + dogName + " does not have an owner");
            return;
        }

        Dog dog = dogCollection.getDog(dogName);
        Owner owner = ownerCollection.getOwner(dog.getOwner().getName());

        owner.removeDog(dog);
        dog.setOwner(null);
        System.out.println("The dog " + dog.getName() + " now have no owner");
    }

    private void increaseAgeMethod() {
        if (dogCollection.getDogs().size() == 0) {
            System.out.println("Error: No dogs in register");
            return;
        }

        String dogName = input.readText("Name of dog");

        dogName = handleBlankString(dogName, "Name of dog");

        if (!dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " does not exist");
            return;
        }

        Dog dog = dogCollection.getDog(dogName);

        dog.incrementAge(1);
        System.out.println("The dog " + dog.getName() + " is now one year older");
    }

    private String handleBlankString(String string, String prompt) {
        while (string.isBlank()) {
            System.out.println("Error: a blank string is not allowed, try again");
            string = input.readText(prompt);
        }
        return string;
    }
    
    private void shutdown() {
        System.out.println("Dog register shut down");
    }
    
    public static void main(String[] args) {
        new DogRegister().start();
    }
    
}