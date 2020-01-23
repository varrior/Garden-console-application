package pl.edu.animalgarden.components;

/*
 * Dog class inherits data from Animal and Data classes and adds specific for this class properties
 * breed and dogId
 * getHeader() and showInformation() overrides interface Inform methods.
 * elementType() and elementName() overrides interface Recordable methods
 *
 *  dataToSave() - data about Dog which should be save in the file
 *
 *  ATTENTION:
 *  getters are used for inherits methods from parent class
 */

public class Dog extends Animal {
    private static long dogCount = 0;

    private String breed;
    private long dogId;

    public Dog(String firstName, String sex, long age, Owner owner, String breed) {
        super(firstName, sex, age, owner);
        this.dogId = ++dogCount;
        this.breed = breed;
    }

    @Override
    public String dataToSave() {
        String result = "AnimalInMyPrimitiveDatabase" + "\n";
        result += "Dog" + "\n";
        result += super.dataToSave();
        result += this.breed + "\n";
        result += this.getX() + "\n";
        result += this.getY() + "\n";

        return result;
    }

    @Override
    public void getHeader() {
        System.out.println("- This is dog number " + this.dogId + ", but Animal is number " + this.getAnimalId());
    }

    @Override
    public void showInformation() {
        this.animalInformation();
        System.out.println("   - Breed: " + this.breed);
        this.ownerInformation();
    }

    @Override
    public String elementType() {
        return "Dog";
    }

    @Override
    public String elementName() {
        return "Dog " + this.getFirstName();
    }

    /* ***************************GETTERS AND SETTERS*****************************/

    public long getDogId() {
        return dogId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
