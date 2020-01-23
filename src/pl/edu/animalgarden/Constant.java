package pl.edu.animalgarden;

public interface Constant {
    int GARDEN_SIZE = 10;

    int MIN_FIRSTNAME_LENGTH = 3;
    int MIN_LASTNAME_LENGTH = 3;
    int MIN_SEX_LENGTH = 3;
    int MIN_OWNER_AGE = 0;
    int MAX_OWNER_AGE = 120;

    int MIN_ANIMAL_NAME_LENGTH = 3;
    int MIN_ANIMAL_SEX_LENGTH = 3;
    int MIN_ANIMAL_AGE = 0;
    int MAX_ANIMAL_AGE = 50;

    int MIN_ANIMAL_BREED = 3;

    int ANIMAL_OBJECT_LENGTH = 9;
    int OWNER_OBJECT_LENGTH = 7;

    enum Animals {
        DOG("Dog"),
        CAT("Cat"),
        TURTLE("Turtle");

        private String animal;

        Animals(String animal) {
            this.animal = animal;
        }

        /* **********************ENUM GETTERS AND SETTERS*****************************/

        public String getAnimal() {
            return animal;
        }
    }
}
