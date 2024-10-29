package W7.Person;

public class Main {
    public static void main(String[] args) throws Exception {
        Person p = new Person("John", "Male", 21);
        System.out.println(p);
        Student stu1 = new Student("Martin", "Male", 32, "674345", 2300.34);
        System.out.println(stu1);
        Teacher teacher1 = new Teacher("David", "Male", 45, "73842", 3500.23);
        System.out.println(teacher1);
    }
}
