
package lab.mvc.model;

public class Person {

    private String firstName;
    private String lastName;
    private String birthDay;
    private String sex;

    // Constructor
    public Person(String firstName, String lastName, String birthDay, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.sex = sex;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for birthDay
    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    // Getter and Setter for sex
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
