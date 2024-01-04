// Lizardo Klavebäck Martinez likl2578@SU.SE

public class DogRegister {

    private static final String REGISTER_OWNER = "register new owner";
    private static final String REGISTER_DOG = "register new dog";
    private static final String GIVE_DOG_TO_OWNER = "give dog to owner";
    private static final String EXIT_COMMAND = "exit";
    private static final String LIST_OWNERS = "list owners";

    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();

    // private Scanner input = new Scanner(System.in);
    private HandleUserInput input = new HandleUserInput();
    private boolean running = true;

    private void start() {
        initialize();
        runCommandLoop();
    }

    private void initialize() {
        System.out.println("Initializing program");
    }

    private void runCommandLoop() {
        System.out.println("Running command loop");

        while (running) {
            String command = readCommand().toLowerCase();
            handleCommand(command);
        }

        shutdown();
    }

    private String readCommand() {
        System.out.println("Enter command>");
        return input.readText("Command").toLowerCase();
    }

    private void handleCommand(String command) {
        switch(command) {
            case REGISTER_OWNER:
                registerOwnerMethod();
                break;
            case REGISTER_DOG:
                registerDogMethod();
                break;
            case GIVE_DOG_TO_OWNER:
                giveDogToOwnerMethod();
                break;
            case LIST_OWNERS:
                listOwnersMethod();
                break;
            case EXIT_COMMAND:
                running = false;
                return;
            default:    
                System.out.println("Error: Wrong command!");
        }
    }

    private void registerOwnerMethod() {
        System.out.println("Name?>");
        String name = input.readText("Name");
        ownerCollection.addOwner(new Owner(name));
        System.out.println(name + " is added");
    }

    private void registerDogMethod() {
        System.out.println("Name?>");
        String name = input.readText("Name");
        System.out.println("Breed?>");
        String breed = input.readText("Breed");
        System.out.println("Age?>");
        int age = input.readInteger("Age");
        System.out.println("Weight?>");
        int weight = input.readInteger("Weight");

        dogCollection.addDog(new Dog(name, breed, age, weight));
    }

    private void giveDogToOwnerMethod() {
        System.out.println("Name of dog?>");
        String dogName = input.readText("Name of dog");
        System.out.println("Name of owner?>");
        String ownerName = input.readText("Name of owner");

        Dog dog = dogCollection.getDog(dogName);
        Owner owner = ownerCollection.getOwner(ownerName);

        if (dog == null) {
            System.out.println("No such dog!");
        } else if (owner == null) {
            System.out.println("No such owner!");
        } else {
            owner.addDog(dog);
            System.out.println(ownerName + " now owns " + dogName);
        }
    }

    private void listOwnersMethod() {
        System.out.println("test");
    }

    private void shutdown() {
        System.out.println("Shutting down");
        // if (input != null) {
        //     input.close();
        // }
    }
    
    public static void main(String[] args) {
        new DogRegister().start();
    }
}
