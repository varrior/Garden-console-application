package pl.edu.animalgarden.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * class used to manage owners and animals records
 * addNewItemToList(Recordable record) - method used to add new owner or animal to the array, as a parameter requires object Recordable type (classes that implements Recordable interface)
 * showItemsName() - displays all items in list by index and first name
 * operationOnTheItemFromTheList(String operation) - remove item or display information about selected item. As a parameter, the function takes a string with the command which operation should be executed
 * showAllItems() - displays all animals or owner from the list, used Inform interface to shows information
 */

public class OwnerAndAnimalRecord {

    private List<Recordable> records = new ArrayList<>();
    private Information info = new Information();

    public void addNewItemToList(Recordable record){
        this.records.add(record);
        System.out.println("New " + record.elementType() + " has been added to the list");
    }
    public void showItemsName(){
        if(this.records.size() > 0){
            this.records.stream()
                    .forEach(item->
                            System.out.println(this.records.indexOf(item) + 1 + ". " + item.elementName())
                    );
        } else {
            System.out.println("   - No items to show!");
        }

    }
    public void operationOnTheItemFromTheList(String operation){
        int arrIndex;
        System.out.println("Choose who you want to remove: ");
        if(this.records.size() > 0) {
            this.showItemsName();

            Scanner scanner = new Scanner(System.in);
            arrIndex = scanner.nextInt() - 1;

            if(arrIndex > this.records.size() - 1 || arrIndex < 0){
                System.out.println("This number is out of range");
            } else if (operation.toLowerCase() == "remove"){
                this.records.remove(arrIndex);
                System.out.println("Element has been removed successfully");
            } else if(operation.toLowerCase() == "information"){
                info.logInfo((Inform) this.records.get(arrIndex));
            }
        } else {
            System.out.println("   - No items to show");
        }
    }

    public void showAllItems(){
        if(this.records.size() > 0) {
            this.records.stream()
                    .forEach(record ->
                            info.logInfo((Inform) record)
                    );
        } else {
            System.out.println("No items to show");
        }
    }

    /* *************************************GETTERS AND SETTERS ********************************************/

    public List<Recordable> getRecords() {
        return records;
    }
}
