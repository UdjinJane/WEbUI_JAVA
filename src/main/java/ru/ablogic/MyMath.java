package ru.ablogic;

public class MyMath {
    public static double divide(int num1, int num2){
        if (num2==0)
            throw new ArithmeticException("Земля не плоская, поэтому на ноль делить нельзя!");
        return num1 / num2;
    }
}
