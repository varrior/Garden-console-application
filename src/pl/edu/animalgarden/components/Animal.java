package pl.edu.animalgarden.components;

import pl.edu.animalgarden.management.Inform;
import pl.edu.animalgarden.management.Recordable;

/*
 * Animal class inherits the common properties from the Data class and adds new properties,
 * which are mandatory for Animals. Animal class is the parent for more specific classes Dog, Cat and Turtle.
 *
 * setAnimalToOwner() - set newly created Animal object to the owner object
 * isMovable(long size, long direction) - checks if animal can move, doesn't cross the garden size (x and y), requires garden size and move direction
 * changePosition(long direction) - changes the current coordinate of the animal object depending on move direction
 * makeMove(long size, long direction) - checks isMovable condition and make a move otherwise logs a message
 * animalInformation() - basic information about animal, this method is used in child classes
 * ownerInformation() - information about owner of the animal
 * dataToSave() - data about animals which should be save in file
 *
 * animalId - number of all animals objects
 * x and y - current coordination of the animal
 * owner - owner of the animal
 *
 * ATTENTION:
 * getters are used for inherits methods from parent class
 *
 */
public abstract class Animal extends Data implements Inform, Recordable {
    private static long animalCount = 0;

    private long animalId;
    private Owner owner;
    private int x;
    private int y;

    public Animal(String firstName, String sex, long age, Owner owner) {
        super(firstName, sex, age);
        this.animalId = ++animalCount;
        this.owner = owner;
        this.x = 0;
        this.y = 0;

        this.setAnimalToOwner();
    }

    public void setAnimalToOwner() {
        this.owner.setAnimal(this);
    }

    public boolean isMovable(long size, long direction) {
        boolean isMovable = false;

        if(direction == 1 && this.y < size){
            isMovable = true;
        } else if(direction == 2 && this.y > 0){
            isMovable = true;
        } else if(direction == 3 && this.x < size){
            isMovable = true;
        } else if(direction == 4 && this.x > 0){
            isMovable = true;
        }

        return isMovable;
    }

    public void changePosition(long direction){
        int currentY = this.y;
        int currentX = this.x;

        if(direction > 0 && direction < 4){
            if(direction == 1){
                this.setY(currentY + 1);
            } else if(direction == 2){
                this.setY(currentY - 1);
            } else if(direction == 3){
                this.setX(currentX + 1);
            } else if(direction == 4){
                this.setX(currentX - 1);
            }
            System.out.println(this.getFirstName() + " moved from: " + "x: " + currentX + ", y: " + currentY + ", to x: " + this.x + ", y: " + this.y);
        } else {
            System.out.println("This direction doesn't exist");
        }
    }

    public void makeMove(long size, long direction) {
        if(this.isMovable(size, direction)){
            this.changePosition(direction);
        } else {
            System.out.println("This move is not allowed");
        }
    }

    public void animalInformation(){
        System.out.println("   - Name: " + this.getFirstName());
        System.out.println("   - Sex: " + this.getSex());
        System.out.println("   - Age: " + this.getAge());
        System.out.println("   - Current coordinates: " + "x: " + this.x + ", " + "y: " + this.y);
    }

    public void ownerInformation(){
        System.out.println("   - Owner: ");
        System.out.println("      - First name: " + this.owner.getFirstName());
        System.out.println("      - Second name: " + this.owner.getSecondName());
        System.out.println("      - Last name: " + this.owner.getLastName());
        System.out.println("      - Sex: " + this.owner.getSex());
        System.out.println("      - Age: " + this.owner.getAge());
    }

    public String dataToSave(){
        String result = "";
        result += this.getFirstName() + "\n";
        result += this.getSex() + "\n";
        result += this.getAge() + "\n";
        result += this.owner.getId() + "\n";

        return result;
    }
    /* ****************************GETTERS AND SETTERS**********************************/

    public long getAnimalId() {
        return animalId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
