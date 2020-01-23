package pl.edu.animalgarden.management;
/*
 * logInfo(Inform informObject) - method used to display information about specific animals and owners
 *                                as a parameter requires object Inform type (classes that implements Inform interface)
 */

public class Information {
    void logInfo(Inform informObject){
        informObject.getHeader();
        informObject.showInformation();
    }
}
