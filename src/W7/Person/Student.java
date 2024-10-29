package W7.Person;

public class Student extends Person {
    String studentID;
    double fees;

    public Student(String studentID, double fees) {
        this.studentID = studentID;
        this.fees = fees;
    }

    public Student(String name, String gender, int age, String studentID, double fees) {
        super(name, gender, age);
        this.studentID = studentID;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return super.toString() + " Student [studentID=" + studentID + ", fees=" + fees + "]";
    }
}
