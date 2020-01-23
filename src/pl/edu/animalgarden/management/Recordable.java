package pl.edu.animalgarden.management;

/*
 * public interface used to return specific data to the OwnerAndAnimalRecord class.
 * These methods are override in classes that implements this interface (Owner and Animal classes)
 */

public interface Recordable{
    String elementType();
    String elementName();
}
