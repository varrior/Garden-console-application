package pl.edu.animalgarden.components;

import pl.edu.animalgarden.management.Inform;
import pl.edu.animalgarden.management.Recordable;

import java.util.ArrayList;
import java.util.List;

/*
*   class creates Owner object with inherited properties firstName, sex, age and private properties
*   id, lastName, animals
*   getHeader() and showInformation() overrides interface Inform methods.
*   elementType() and elementName() overrides interface Recordable methods
*
*   dataToSave() - data about Owner who should be save in the file

*   ATTENTION:
*   getters are used for inherits methods from parent class
*/
public class Owner extends Data implements Inform, Recordable {
    private static long ownerCount;

    private long id;
    private String lastName;
    private String secondName;
    private List<Animal> animals;

    public Owner(String firstName,String secondName, String lastName, String sex, long age) {
        super(firstName, sex, age);
        this.id = ++ownerCount;
        this.secondName = secondName;
        this.lastName = lastName;
        this.animals = new ArrayList<>();
    }
    public String dataToSave(){
        String result = "OwnerInMyPrimitiveDatabase" + "\n";
        result += "Owner" + "\n";
        result += this.getId() + "\n";
        result += this.getFirstName() + "\n";
        result += this.getSecondName() + "\n";
        result += this.getLastName() + "\n";
        result += this.getSex() + "\n";
        result += this.getAge() + "\n";

        return result;
    }
    @Override
    public void getHeader() {
        System.out.println("This is owner number: " + this.id);
    }

    @Override
    public void showInformation() {
        System.out.println("   - First name: " + this.getFirstName());
        System.out.println("   - Second name: " + this.getSecondName());
        System.out.println("   - Last name: " + this.lastName);
        System.out.println("   - Sex: " + this.getSex());
        System.out.println("   - Age: " + this.getAge());
        System.out.println("   - Animals: ");
        if(this.animals.size() > 0){
            this.animals.stream()
                    .forEach(element -> System.out.println("      - " + element.getFirstName()));
        } else {
            System.out.println("      - This owner has no animals");
        }
    }

    @Override
    public String elementType() {
        return "Owner";
    }

    @Override
    public String elementName() {
        return this.getFirstName() + " " + this.getLastName();
    }


    /* **************************GETTERS AND SETTERS***************************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimal(Animal animal) {
        this.animals.add(animal);
    }


}
