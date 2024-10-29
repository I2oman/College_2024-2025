package W7.Person;

public class Teacher extends Person {
    String empld;
    double salary;

    public Teacher(String empld, double salary) {
        this.empld = empld;
        this.salary = salary;
    }

    public Teacher(String name, String gender, int age, String empld, double salary) {
        super(name, gender, age);
        this.empld = empld;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " Teacher [empld=" + empld + ", salary=" + salary + "]";
    }
}
