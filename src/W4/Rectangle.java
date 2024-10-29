package W4;

public class Rectangle {
    private double a;
    private double b;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getArea() {
        return a * b;
    }

    public double getPerimeter() {
        return (a + b) * 2;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
