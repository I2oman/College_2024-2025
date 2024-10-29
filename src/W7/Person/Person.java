package W7.Person;

public class Person {
    String name;
    String gender;
    int age;

    public Person() {
        this.name = "name";
        this.gender = "gender";
        this.age = 0;
    }

    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
    }
}
