// Lizardo Klavebäck Martinez likl2578@SU.SE

import java.util.ArrayList;

public class OwnerCollection {
    
    private Owner[] owners = new Owner[0];
    private int size;

    public boolean addOwner(Owner owner) {

        if (this.containsOwner(owner)) {
            return false;
        }

        if (size == owners.length) {
            this.increaseArrayByOne();
        }

        owners[size] = owner;
        size++;
        return true;

    }

    public boolean removeOwner(String ownerName) {
        Owner owner = this.getOwner(ownerName);

        if (owner == null) {
            return false;  
        }

        if (owner.getDogs().size() > 0) {
            return false;  
        }

        if (this.containsOwner(ownerName)) {
            for (int i = 0; i < owners.length; i++) {
                if (owners[i].getName().equalsIgnoreCase(ownerName)) {
                    owners[i] = null;
                    this.shortenArrayByOne();
                    return true;
                }
            }
        }
        return false;

    }

    public boolean removeOwner(Owner owner) {
        if (owner.getDogs().size() > 0) {
            return false;
        }

        if (this.containsOwner(owner)) {
            for (int i = 0; i < owners.length; i++) {
                if (owners[i].equals(owner)) {
                    owners[i] = null;
                    this.shortenArrayByOne();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsOwner(String ownerName) {
        if (this.getOwner(ownerName) != null) {
            return true;
        } 
          return false;
    }

    public boolean containsOwner(Owner owner) {
        if (this.getOwner(owner.getName()) != null) {
            return true;
        } 
          return false;
    }

    public Owner getOwner(String ownerName) {
        for(int i = 0; i < owners.length; i++) {
            if(owners[i].getName().equalsIgnoreCase(ownerName)) {
                return owners[i];
            }
        }
        return null;
    }

    public ArrayList<Owner> getOwners() {
        ArrayList<Owner> ownerList = new ArrayList<Owner>();
        for (int i = 0; i < owners.length; i++) {
            ownerList.add(owners[i]);
        }
        return ownerList;
    }

    private void increaseArrayByOne() {
        Owner[] temp = new Owner[owners.length + 1];
            for (int i = 0; i < owners.length; i++) {
                temp[i] = owners[i];
            }
            owners = temp;
    }

    private void shortenArrayByOne() {
        Owner[] temp = new Owner[owners.length - 1];
        int y = 0;
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] != null) {
            temp[y] = owners[i];
            y += 1;
            } 
        }
        owners = temp;
    }
}
