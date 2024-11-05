package W9;

public class Member {
    private String name;
    private String surname;
    private double miles;

    public Member() {
        name = "";
        surname = "";
        miles = 0;
    }

    public Member(String name, String surname, double miles) {
        this.name = name;
        this.surname = surname;
        this.miles = miles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "Member [name=" + name + ", surname=" + surname + ", miles=" + miles + "]";
    }
}
