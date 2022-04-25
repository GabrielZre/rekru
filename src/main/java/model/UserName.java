package model;

public class UserName {
    String firstname;
    String lastname;

    @Override
    public String toString() {
        return "UserName{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserName() {}

    public UserName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
