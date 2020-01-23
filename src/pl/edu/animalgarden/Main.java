package pl.edu.animalgarden;

import pl.edu.animalgarden.components.Garden;

public class Main {

    public static void main(String[] args) {
        Garden garden = new Garden();
        garden.load();
        garden.start();

    }
}
