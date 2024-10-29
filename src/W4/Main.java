package W4;

public class Main {
    public static void main(String[] args) {
        Test t1 = new Test(15, 5);
        t1.Number1 = 24;
        System.out.println("The Number 1: " + t1.Number1);
        System.out.println("The Number 2: " + t1.getNumber2());
        t1.setNumber2(78);
        System.out.println("The Number 2: " + t1.getNumber2());
        System.out.println(t1.sum());
        System.out.println(t1.subtract());
        System.out.println(t1.multiply());
        System.out.println(t1.divide());
        Circle c0 = new Circle();
        Circle c1 = new Circle(1);
        System.out.println(t1.Number1);
        Circle c2 = new Circle(25);
        Circle c3 = new Circle(125);
        System.out.println("Circle 0 A: " + c0.getArea() + " P: " + c0.getPerimeter());
        System.out.println("Circle 1 A: " + c1.getArea() + " P: " + c1.getPerimeter());
        System.out.println("Circle 2 A: " + c2.getArea() + " P: " + c2.getPerimeter());
        System.out.println("Circle 3 A: " + c3.getArea() + " P: " + c3.getPerimeter());
    }

}