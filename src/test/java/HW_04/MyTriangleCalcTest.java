package HW_04;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
//важный импорт, указать, что в импорте должен быть именно он
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("Различные тесты к домашнему заданию №4")

public class MyTriangleCalcTest {
    MyTriangleCalc myTriangleCalc =new MyTriangleCalc();

    // Логгирование.
    private static Logger logger = LoggerFactory.getLogger(MyTriangleCalcTest.class);
    @BeforeAll
    static void beforeAllTests() {
        logger.info("Start test");
    }
    @BeforeEach
    public void setUp(){
        logger.info("Test begins!");
    }
    @AfterAll
    static void afterAllTests() {
        logger.info("End Tests!");
    }
    @Nested // Раздел позитивных тестов.
    @DisplayName("Позитивные тесты")

    class GetPositiveInit {
        // Проверка правильной иницииализации a, b, c.
        @Test
        public void newDataA(){
            int resp = myTriangleCalc.getA();
            Assertions.assertEquals(0,resp);

        }
        @Test
        public void newDataB(){
            int resp = myTriangleCalc.getB();
            Assertions.assertEquals(0,resp);

        }
        @Test
        public void newDataC(){
            int resp = myTriangleCalc.getC();
            Assertions.assertEquals(0,resp);

        }
        // Тест проверит выбрасывание исключения.
            @Nested // Просто считаем.
            @DisplayName("Вложенные позитивные тесты")
            class CalcArea {
            @ParameterizedTest
            @ValueSource( ints = {5})
            // @Test
            public void resultShouldBeValue(int a){
                Assertions.assertEquals(14.696938456699069, myTriangleCalc.calcArea(a,6,7));
            }
        }

    }
    @Nested // Раздел негативных тестов.
    @DisplayName("Негативные тесты")
    class GetNeagativeArg {
        @Test
        public void checkNagativeException(){

            Exception exception = assertThrows(
                    ArithmeticException.class,
                    () -> myTriangleCalc.calcArea(-1, -1, -1));

            assertEquals("Error! Negative arguments!", exception.getMessage());
            assertTrue(exception.getMessage().contains("Negative"));

        }
        // Негативный результат, с задействованием AssertJ.
        // Тест бесполезный, просто щупаю возможности библиотеки.
        @Test
        void testErrorResultOfCalc(){
            double result = myTriangleCalc.calcArea(5,6,7);
            assertThat(result).isNotEqualTo(14.69);

        }

    }

}
