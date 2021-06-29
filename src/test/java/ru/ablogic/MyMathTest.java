package ru.ablogic;

import org.junit.Test;

public class MyMathTest {
    // Тест проверит выбрасывание исключения.
    @Test(expected = ArithmeticException.class)
    public void checkZeroDenominationShouldZeroException(){
        MyMath.divide(1,0);
    }
}
