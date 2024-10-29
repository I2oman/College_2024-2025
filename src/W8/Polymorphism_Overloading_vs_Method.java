package W8;

// Overloading example
class MathOperations {
    // Method with two parameters
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with three parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Overriding example
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    // Overriding the sound method in the Animal class
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    // Overriding the sound method in the Animal class
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Polymorphism_Overloading_vs_Method {
    public static void main(String[] args) {
        // Overloading usage example
        Animal myDog = new Dog();
        myDog.sound(); // Outputs: "Dog barks", not "Animal makes a sound"
        Animal myCat = new Cat();
        myCat.sound(); // Outputs: "Cat meows", not "Animal makes a sound"

        // Overriding usage example
        MathOperations math = new MathOperations();
        System.out.println(math.add(2, 3)); // Calls add(int, int), Outputs: 5
        System.out.println(math.add(2, 3, 4)); // Calls add(int, int, int), Outputs: 9
    }
}
