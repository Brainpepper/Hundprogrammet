// Lizardo Klavebäck Martinez likl2578@SU.SE

public class Dog {

    private static final double TAX_TAIL_LENGTH = 3.7;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = normalizeString(name);
        this.breed = normalizeString(breed);
        this.age = age;
        this.weight = weight;
        this.tailLength = setTailLength();
        
    }

    private String normalizeString(String string) {
        String[] names = string.split(" ");

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
        return String.join(" ", names);
    }

    private double setTailLength() {
        if (this.breed.matches("Tax|Dachshund|Mäyräkoira|Teckel")) {
            return TAX_TAIL_LENGTH ;
        } else {
            return this.age * (this.weight / 10.0);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    public int getWeight() {
        return this.weight;
    }

    public double getTailLength() {
        return this.tailLength;
    }

    public String toString() {
        String s = "owner: " + this.owner + "\nName: " + this.name + "\nBreed: " + this.breed + "\nAge: " + this.age + "\nWeight: " + this.weight + "\nTail length: " + (float)this.tailLength;
        return s;
    }

    public void incrementAge(int years) {
        if (years < 1) {
            System.out.println("Ålder kan inte vara noll eller ett negativt tal.");
            return;
        } else if (years > 0 && Integer.MAX_VALUE - years < this.age) {
            this.age = Integer.MAX_VALUE;
        } else {
            this.age += years;
        }
        this.tailLength = setTailLength();
    }

    public boolean setOwner(Owner newOwner) {
        if (newOwner == null) {
            if (this.owner != null) {
                this.owner.removeDog(this);
            }
            this.owner = null;
            return true;
        } else {
            // setting The Owner Of An Already Owned Dog Does Nothing
            if (this.owner != null) {
                return false;
            }
            if (this.owner != null && this.owner != newOwner) {
                this.owner.removeDog(this);
            }
            this.owner = newOwner;
            if (!newOwner.getDogs().contains(this)) {
                newOwner.addDog(this);
            }
            return true;
        }
    }

    public Owner getOwner() {
        return this.owner;
    }
    
}