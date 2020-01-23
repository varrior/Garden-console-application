package pl.edu.animalgarden.components;

import pl.edu.animalgarden.management.Inform;

/*
 *  Cat class inherits data from Animal and Data classes and adds specific for this class properties
 *  breed and catId
 *  getHeader() and showInformation() overrides interface Inform methods.
 *  elementType() and elementName() overrides interface Recordable methods
 *  dataToSave() - data about Cat which should be save in the file
 *
 *  ATTENTION:
 *  getters are used for inherits methods from parent class
 */
public class Cat extends Animal implements Inform {
    private static long catCount = 0;

    private String breed;
    private long catId;

    public Cat(String firstName, String sex, long age, Owner owner, String breed) {
        super(firstName, sex, age, owner);
        this.breed = breed;
        this.catId = ++catCount;
    }
    @Override
    public String dataToSave() {
        String result = "AnimalInMyPrimitiveDatabase" + "\n";
        result += "Cat" + "\n";
        result += super.dataToSave();
        result += this.breed + "\n";
        result += this.getX() + "\n";
        result += this.getY() + "\n";

        return result;
    }
    @Override
    public void getHeader() {
        System.out.println("- This is cat number " + this.catId + ", but Animal is number " + this.getAnimalId());
    }

    @Override
    public void showInformation() {
        this.animalInformation();
        System.out.println("   - Breed: " + this.breed);
        this.ownerInformation();
    }

    @Override
    public String elementType() {
        return "Cat";
    }

    @Override
    public String elementName() {
        return "Cat " + this.getFirstName();
    }

    /* ************************GETTER AND SETTER***************************/

    public long getCatId() {
        return catId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

}
