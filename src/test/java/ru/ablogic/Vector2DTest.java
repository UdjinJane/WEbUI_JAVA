package ru.ablogic;

import org.junit.*;

public class Vector2DTest {
    private final double DELTA=1e-9;
    private static Vector2D vector2D;
    // Готовим окружение.
    // И этот метод вызовестя перед каждым методом.
    @BeforeClass
    public static void createVector2D(){
        vector2D = new Vector2D(); // Action. Действие.
    }


// Тестируем инициализацию вектора. При создании длина вектора = 0.
    @Test
    public void zeroVectorLengthInit(){
                    // Assertion.
        Assert.assertEquals(0,vector2D.length(), DELTA);

    }
    // Демонстрация негативного результата теста.
    @Test
    public void badZeroVectorLength(){
        // Тут тест должен завалиться.
        // С проказательной аннотацией причины падения.
        Assert.assertEquals(0,vector2D.badLength(), DELTA);

    }
    // Проверка правильной иницииализации координат x & y,
    @Test
    public void newVectorInitZeroXY(){

        double summary = vector2D.getX() + vector2D.getY();
        Assert.assertEquals(0,summary,DELTA);
    }

}
