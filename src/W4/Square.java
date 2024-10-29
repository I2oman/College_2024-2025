package W4;

public class Square {
    private double a;

    public Square() {
        this(0);
    }

    public Square(double a) {
        this.a = a;
    }

    public double getArea() {
        return Math.pow(a, 2);
    }

    public double getPerimeter() {
        return a * 4;
    }

    public double getA() {
        return a;
    }

}
