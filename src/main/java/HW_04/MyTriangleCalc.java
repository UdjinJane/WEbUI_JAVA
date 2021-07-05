package HW_04;

public class MyTriangleCalc {
   private int a,b,c;
   private double s,area;

   public double calcArea(int a, int b, int c) {
       if ((a<0) || (b<0) || (c<0))
           throw new ArithmeticException("Error! Negative arguments!");
       s =(a+b+c)/2;
       return Math.sqrt(s*(s-a)*(s-b)*(s-c));
   }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
