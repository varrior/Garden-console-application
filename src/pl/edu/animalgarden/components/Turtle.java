package pl.edu.animalgarden.components;
import pl.edu.animalgarden.management.Inform;

/*
 *  Turtle class inherits data from Animal and Data classes and adds specific for this class properties state and turtleId
 *  isHungry() - checks if turtle is active or hidden
 *  feedTurtle() - changes turtle's state to active
 *  hideTurtle() - changes turtle's state to hidden
 *  makeMove(long size, long direction) - overrides method from Animal class, checks if the turtle is active and hides the turtle after the move
 *  getHeader() and showInformation() overrides interface Inform methods.
 *  elementType() and elementName() overrides interface Recordable methods
 *  dataToSave() - data about Turtle which should be save in the file
 *
 *  ATTENTION:
 *  getters are used for inherits methods from parent class
 *
 */

public class Turtle extends Animal implements Inform {

    public enum State {
        ACTIVE("Active"),
        HIDE("Hidden");

        private String state;

        State(String state) {
            this.state = state;
        }

        /* **********************ENUM GETTERS AND SETTERS*****************************/

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    private static long turtleCount = 0;
    private State state;
    private long turtleId;

    public Turtle(String firstName, String sex, long age, Owner owner) {
        super(firstName, sex, age, owner);
        this.state = State.HIDE;
        this.turtleId = ++turtleCount;
    }

    public boolean isHungry(){
        if(this.state.getState().equals("Active")){
            return false;
        } else {
            return true;
        }
    }

    public void feedTurtle(){
        this.setState(State.ACTIVE);
        System.out.println("The turtle is active");
    }

    public void hideTurtle(){
        this.setState(State.HIDE);
        System.out.println("The turtle is hidden in the shell");
    }

    @Override
    public String dataToSave() {
        String result = "AnimalInMyPrimitiveDatabase" + "\n";
        result += "Turtle" + "\n";
        result += super.dataToSave();
        result += this.state + "\n";
        result += this.getX() + "\n";
        result += this.getY() + "\n";

        return result;
    }

    @Override
    public void makeMove(long size, long direction) {
        if(!this.isHungry()){
            if (this.isMovable(size, direction)) {
                this.changePosition(direction);
                this.hideTurtle();
            } else {
                System.out.println("This move is not allowed");
            }
        } else {
            System.out.println("The turtle is in a shell, if you want to move, you must feed him");
        }
    }

    @Override
    public void getHeader() {
        System.out.println("- This is turtle number " + this.turtleId + ", but Animal is number " + this.getAnimalId());
    }

    @Override
    public void showInformation() {
        this.animalInformation();
        System.out.println("   - State: " + this.state.getState());
        this.ownerInformation();
    }

    @Override
    public String elementType() {
        return "Turtle";
    }

    @Override
    public String elementName() {
        return "Turtle " + this.getFirstName();
    }

    /* *****************************GETTERS AND SETTERS*************************/

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getTurtleId() {
        return turtleId;
    }

}
