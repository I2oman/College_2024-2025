package W4;

public class Test {
    public int Number1 = 12;
    private int Number2 = 2;

    public Test() {
        this(0, 0);
    }

    public Test(int n1, int n2) {
        this.Number1 = n1;
        this.Number2 = n2;
    }

    public void setNumber2(int number2) {
        Number2 = number2;
    }

    public int getNumber2() {
        return Number2;
    }

    public int sum() {
        return Number1 + Number2;
    }

    public int subtract() {
        return Number1 - Number2;
    }

    public int multiply() {
        return Number1 * Number2;
    }

    public double divide() {
        return Double.valueOf(Number1) / Double.valueOf(Number2);
    }
}
