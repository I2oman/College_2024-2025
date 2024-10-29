package W7.Animal;

public class Dog extends Animal {
    public Dog(String animalName) {
        super(animalName);
    }

    private void bark() {
        System.out.println("The dog " + getAnimalName() + " bark");
    }

    @Override
    public void sound() {
        bark();
    }
}
