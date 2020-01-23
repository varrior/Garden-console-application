package pl.edu.animalgarden.components;

import pl.edu.animalgarden.readandwrite.GardenStateReader;
import pl.edu.animalgarden.readandwrite.GardenStateWriter;
import pl.edu.animalgarden.management.OwnerAndAnimalRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static pl.edu.animalgarden.Constant.*;

public class Garden {
    private OwnerAndAnimalRecord ownerRecord = new OwnerAndAnimalRecord();
    private OwnerAndAnimalRecord animalRecord = new OwnerAndAnimalRecord();

    public void load(){
        GardenStateReader gardenStateReader = new GardenStateReader();

        String readOwnersFromFile           = gardenStateReader.read("owners.txt");
        String readAnimalsFromFile          = gardenStateReader.read("animals.txt");
        String readOwnersAndAnimalsFromFile = readOwnersFromFile + readAnimalsFromFile;

        List<String> ownersStringList       = new ArrayList<>();
        List<String> animalsStringList      = new ArrayList<>();

        String[] splitOwnersAndAnimalsString = readOwnersAndAnimalsFromFile.split(" ");
        StringBuilder ownerOrAnimal;

        for(int i = 0; i < splitOwnersAndAnimalsString.length; i++) {
            ownerOrAnimal = new StringBuilder();
            if(splitOwnersAndAnimalsString[i].equals("OwnerInMyPrimitiveDatabase")){
                for(int j = i + 1; j < i + OWNER_OBJECT_LENGTH; j++){
                    ownerOrAnimal.append(splitOwnersAndAnimalsString[j]).append(" ");
                }
                ownersStringList.add(ownerOrAnimal.toString());
            } else if(splitOwnersAndAnimalsString[i].equals("AnimalInMyPrimitiveDatabase")){
                for(int j = i + 1; j < i + ANIMAL_OBJECT_LENGTH; j++){
                    ownerOrAnimal.append(splitOwnersAndAnimalsString[j]).append(" ");
                }
                animalsStringList.add(ownerOrAnimal.toString());
            }
        }

        if(ownersStringList.size() > 0){
            ownersStringList.stream()
                    .map(element -> element.split(" "))
                    .forEach(loadedOwner -> {
                        Owner owner = new Owner(loadedOwner[2], loadedOwner[3], loadedOwner[4], Long.parseLong(loadedOwner[5]));
                        owner.setId(Long.parseLong(loadedOwner[1]));
                        ownerRecord.addNewItemToList(owner);
                    });

            animalsStringList.stream()
                    .map(element -> element.split(" "))
                    .forEach(loadedAnimal -> {
                        Owner owner = ownerRecord.getRecords().stream()
                                .map(item -> (Owner) item)
                                .filter(item -> item.getId() == Long.parseLong(loadedAnimal[4]))
                                .findAny()
                                .orElse(null);
                        if (owner != null) {
                            if (loadedAnimal[0].toLowerCase().equals("Dog".toLowerCase())) {
                                Dog dog = new Dog(loadedAnimal[1], loadedAnimal[2], Long.parseLong(loadedAnimal[3]), owner, loadedAnimal[5]);
                                dog.setX(Integer.parseInt(loadedAnimal[6]));
                                dog.setY(Integer.parseInt(loadedAnimal[7]));
                                animalRecord.addNewItemToList(dog);
                            } else if (loadedAnimal[0].toLowerCase().equals("Cat".toLowerCase())) {
                                Cat cat = new Cat(loadedAnimal[1], loadedAnimal[2], Long.parseLong(loadedAnimal[3]), owner, loadedAnimal[5]);
                                cat.setX(Integer.parseInt(loadedAnimal[6]));
                                cat.setY(Integer.parseInt(loadedAnimal[7]));
                                animalRecord.addNewItemToList(cat);
                            } else if (loadedAnimal[0].toLowerCase().equals("Turtle".toLowerCase())) {
                                Turtle turtle = new Turtle(loadedAnimal[1], loadedAnimal[2], Long.parseLong(loadedAnimal[3]), owner);
                                turtle.setState(Turtle.State.valueOf(loadedAnimal[5]));
                                turtle.setX(Integer.parseInt(loadedAnimal[6]));
                                turtle.setY(Integer.parseInt(loadedAnimal[7]));
                                animalRecord.addNewItemToList(turtle);
                            }
                        } else {
                            System.out.println("No owner found");
                        }
                    });
        } else {
            System.out.println("No owners saved in the file, so no animals can be loaded");
        }
    }
    public void start(){
        boolean openProgram = true;
        Menu menu = new Menu();
        menu.showMenu();

        Scanner scanner = new Scanner(System.in);

        while(openProgram){
            String selectMenuOption = scanner.nextLine();
            switch (selectMenuOption){
                case "1": {
                    Scanner ownerScanner = new Scanner(System.in);
                    long age;
                    String firstName;
                    String lastName;
                    String sex;

                    System.out.println("What is the first name of the owner: ");
                    do{
                        firstName = ownerScanner.nextLine();
                        if(firstName.length() < MIN_FIRSTNAME_LENGTH) System.out.println("First name must be at least 3 characters");
                    } while(firstName.length() < MIN_FIRSTNAME_LENGTH);

                    System.out.println("What is the last name of the owner: ");
                    do {
                        lastName = ownerScanner.nextLine();
                        if(lastName.length() < MIN_LASTNAME_LENGTH) System.out.println("Last name must be at least 3 characters");
                    } while (lastName.length() < MIN_LASTNAME_LENGTH);

                    System.out.println("What is the sex of the owner: ");
                    do {
                        sex = ownerScanner.nextLine();
                        if(sex.length() < MIN_SEX_LENGTH) System.out.println("Sex must be at least 3 characters");
                    } while(sex.length() < MIN_SEX_LENGTH);

                    System.out.println("What is the age of the owner: ");
                    do {
                        while(!ownerScanner.hasNextLong()){
                            System.out.println("This is not a number! Don't cheat!");
                            ownerScanner.next();
                        }
                        age = ownerScanner.nextLong();
                        if(age < MIN_OWNER_AGE || age > MAX_OWNER_AGE) System.out.println("You can't be at this age");
                    } while(age < MIN_OWNER_AGE || age > MAX_OWNER_AGE);

                    ownerRecord.addNewItemToList(new Owner(firstName, lastName, sex, age));
                    break;
                }
                case "2": {
                    ownerRecord.operationOnTheItemFromTheList("remove");
                    break;
                }
                case "3": {
                    ownerRecord.showAllItems();
                    break;
                }
                case "4": {
                    ownerRecord.operationOnTheItemFromTheList("information");
                    break;
                }
                case "5": {
                    Scanner animalScanner = new Scanner(System.in);
                    int animalListNumber = Animals.values().length;
                    long selectAnimalType;
                    String firstName;
                    String sex;
                    String breed;
                    long age;
                    int ownerNumber;
                    long ownerListSize;
                    Owner owner;

                    System.out.println("Choose the animal you want to add: ");
                    for (int i = 0; i < animalListNumber; i++){
                        System.out.println( i + 1 +". " + Animals.values()[i].getAnimal());
                    }

                    do {
                        while(!animalScanner.hasNextLong()){
                            System.out.println("This is not a number! Don't cheat!");
                            animalScanner.next();
                        }
                        selectAnimalType = animalScanner.nextLong();
                        animalScanner.nextLine();
                        if(selectAnimalType < 1 || selectAnimalType > animalListNumber) System.out.println("This number is out of range");

                    } while(selectAnimalType < 1 || selectAnimalType > animalListNumber);

                    System.out.println("What is the name of the animal: ");
                    do{
                        firstName = animalScanner.nextLine();
                        if(firstName.length() < MIN_ANIMAL_NAME_LENGTH) System.out.println("Animal name must be at least 3 characters");
                    } while(firstName.length() < MIN_ANIMAL_NAME_LENGTH);

                    System.out.println("What is the sex of the animal: ");
                    do {
                        sex = animalScanner.nextLine();
                        if(sex.length() < MIN_ANIMAL_SEX_LENGTH) System.out.println("Sex must be at least 3 characters");
                    } while(sex.length() < MIN_ANIMAL_SEX_LENGTH);

                    System.out.println("What is the age of the animal: ");
                    do {
                        while(!animalScanner.hasNextLong()){
                            System.out.println("This is not a number! Don't cheat!");
                            animalScanner.next();
                        }
                        age = animalScanner.nextLong();
                        if(age < MIN_ANIMAL_AGE || age > MAX_ANIMAL_AGE ) System.out.println("Animal can't be at this age");
                    } while(age < MIN_ANIMAL_AGE || age > MAX_ANIMAL_AGE);

                    System.out.println("Who is the owner of this animal: ");
                    ownerRecord.showItemsName();
                    ownerListSize = ownerRecord.getRecords().size();

                    if(ownerListSize > 0) {
                        do {
                            while(!animalScanner.hasNextLong()){
                                System.out.println("This is not a number! Don't cheat!");
                                animalScanner.next();
                            }
                            ownerNumber = animalScanner.nextInt();
                            animalScanner.nextLine();
                            if(ownerNumber < 0 || ownerNumber > ownerListSize ) System.out.println("This number is out of range");
                        } while(ownerNumber < 0 || ownerNumber > ownerListSize);

                        owner = (Owner) ownerRecord.getRecords().get(ownerNumber-1);

                        if(selectAnimalType == 1 || selectAnimalType == 2){

                            System.out.println("What is the breed of the animal: ");
                            do{
                                breed = animalScanner.nextLine();
                                if(breed.length() < MIN_ANIMAL_BREED) System.out.println("Animal breed must be at least 3 characters");
                            } while(breed.length() < MIN_ANIMAL_BREED);

                            animalRecord.addNewItemToList(selectAnimalType ==1 ? new Dog(firstName,sex,age,owner,breed) : new Cat(firstName,sex,age,owner,breed));
                        } else if(selectAnimalType == 3){
                            animalRecord.addNewItemToList(new Turtle(firstName,sex,age,owner));
                        }
                    } else {
                        System.out.println("There is no owner! You can't add an animal");
                    }
                    break;
                } case "6": {
                    animalRecord.operationOnTheItemFromTheList("remove");
                    break;
                } case "7": {
                    animalRecord.showAllItems();
                    break;
                }
                case "8": {
                    animalRecord.operationOnTheItemFromTheList("information");
                    break;
                } case "9": {
                    Scanner moveScanner = new Scanner(System.in);

                    int animalListNumber = animalRecord.getRecords().size();
                    long moveDirection;
                    int selectAnimalToMove;
                    Animal animal;

                    System.out.println("Which animal you want to move: ");
                    animalRecord.showItemsName();

                    if(animalListNumber > 0){
                        do {
                            while(!moveScanner.hasNextLong()){
                                System.out.println("This is not a number! Don't cheat!");
                                moveScanner.next();
                            }
                            selectAnimalToMove = moveScanner.nextInt();
                            moveScanner.nextLine();
                            if(selectAnimalToMove < 1 || selectAnimalToMove > animalListNumber) System.out.println("This number is out of range");
                        } while(selectAnimalToMove < 1 || selectAnimalToMove > animalListNumber);

                        System.out.println("Choose the direction in which the animal is to go: ");
                        System.out.println("1. Up");
                        System.out.println("2. Down");
                        System.out.println("3. Right");
                        System.out.println("4. Left");

                        do {
                            moveDirection = moveScanner.nextLong();
                            if(moveDirection > 4 || moveDirection < 1) System.out.println("This direction doesn't exist!");
                        } while(moveDirection < 1 || moveDirection > 4);

                        animal = (Animal) animalRecord.getRecords().get(selectAnimalToMove-1);
                        animal.makeMove(GARDEN_SIZE, moveDirection);
                    }
                    break;
                } case "10" :{
                    Scanner stateScanner = new Scanner(System.in);
                    int turtleSelected;
                    int turtleSize;

                    List<Turtle> turtles = animalRecord.getRecords().stream()
                            .filter(element-> element instanceof Turtle)
                            .map(turtle -> (Turtle)turtle)
                            .collect(Collectors.toList());

                    turtles.forEach(item -> System.out.println(turtles.indexOf(item) + 1 + ". " + item.elementName()));

                    turtleSize = turtles.size();
                    if(turtleSize > 0) {
                        do {
                            while (!stateScanner.hasNextInt()) {
                                System.out.println("This is not a number! Don't cheat!");
                                stateScanner.next();
                            }
                            turtleSelected = stateScanner.nextInt();
                            stateScanner.nextLine();
                            if (turtleSelected < 1 || turtleSelected > turtleSize)
                                System.out.println("This number is out of range");

                        } while (turtleSelected < 1 || turtleSelected > turtleSize);

                        turtles.get(turtleSelected-1).feedTurtle();
                    } else {
                        System.out.println("There is no turtle!");
                    }
                    break;
                }

                case "11" : {
                    List<Animal> animals = animalRecord.getRecords().stream()
                            .map(element-> (Animal) element)
                            .collect(Collectors.toList());

                    System.out.print("   ");
                    for(int num = 0; num < GARDEN_SIZE; num++){
                        System.out.print("  " + num + " ");
                    }
                    for (int row = 0; row < GARDEN_SIZE; row++)
                    {

                        System.out.println("");
                        System.out.print("    ");
                        for(int num = 0; num < GARDEN_SIZE; num++){
                            System.out.print("----");
                        }
                        System.out.println("");
                        System.out.print(" " + row +" ");

                        //Named for loop
                        iterate:
                        for (int column = 0; column < GARDEN_SIZE; column++) {
                            for(Animal animal: animals){
                                if(row == animal.getY() && column == animal.getX()){
                                    if (animal instanceof Dog) System.out.print("| " + "D" + " ");
                                    else if (animal instanceof Cat) System.out.print("| " + "C" + " ");
                                    else if (animal instanceof Turtle) System.out.print("| " + "T" + " ");

                                    continue iterate;
                                }
                            }
                            System.out.print("| " + " " + " ");
                        }
                        System.out.print("|");
                    }
                    System.out.println("");
                    System.out.print("    ");
                    for(int num = 0; num < GARDEN_SIZE; num++){
                        System.out.print("----");
                    }
                    System.out.println();
                    break;
                }
                case "12": {
                    GardenStateWriter ownersWriter = new GardenStateWriter();
                    GardenStateWriter animalsWriter = new GardenStateWriter();
                    String ownerDataToSave = ownerRecord.getRecords().stream()
                            .map(element -> (Owner) element)
                            .map(Owner::dataToSave)
                            .collect(Collectors.joining());

                    String animalDataToSave = animalRecord.getRecords().stream()
                            .map(element-> (Animal) element)
                            .map(Animal::dataToSave)
                            .collect(Collectors.joining());

                    ownersWriter.save("owners.txt", ownerDataToSave);
                    animalsWriter.save("animals.txt", animalDataToSave);

                    break;
                }
                case "0":{
                    openProgram = false;
                    scanner.close();
                    System.out.println("The program has ended");
                    break;
                }
                default:{
                    System.out.println("Not in the list");
                }
            }
        }
    }
}
