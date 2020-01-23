package pl.edu.animalgarden.components;
/*
 *
 * Basic class with common properties for Owners and Animals
 *
 */
public abstract class Data {

    private String firstName;
    private String sex;
    private long age;

    public Data(String firstName, String sex, long age) {
        this.firstName = firstName;
        this.sex = sex;
        this.age = age;
    }

    /* ************************GETTERS AND SETTERS*****************************/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
