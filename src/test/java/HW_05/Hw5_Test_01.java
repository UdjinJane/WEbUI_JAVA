package HW_05;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Hw5_Test_01 {
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String PROJECT_PAGE_URL = "https://crm.geekbrains.space/project/create/";
    private static final String PROJECT_PAGE_UPDATED_URL="https://crm.geekbrains.space/project/update/1775";
    private static final String STUDENT_LOGIN = "user";
    private static final String STUDENT_PASSWORD = "1234";
    private static final String PROJECTS_NAME = "PROJECT SAMPLE";
    private static final String COOKIE_NAME = "BAPID";
    private static WebDriver driver;
    private static String BAPID_VALUE;
    private static Cookie cookie;

    @DisplayName("Набор тестов к домашнему заданию №5")
    @BeforeAll
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup(); // Перед всеми тестами иницииализируем драйвер хрома.
    }

    @BeforeEach
    public void setupTest() {
        initChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
           driver.quit();
        }
    }

    @Nested // Раздел тестов логина.
    @DisplayName("Тесты логина для получения кук и вход с кукой внутрь CRM")

    class myLoginTests  {

         @Test
        public void loginTestWithSaveCookies() throws InterruptedException {
            goToAddress(LOGIN_PAGE_URL);
            // Плучить и сохранить Куку
            getLoginCookie();
            // Переход на страницу CRM.
            goToAddress(PROJECT_PAGE_URL);
            // Чистим куки и устанавливаем их заново.
            driver.manage().deleteAllCookies();
            driver.manage().addCookie(cookie);
        }

         @Test
        public void goToAddressWithSaveCookies() throws InterruptedException {

            // Чистим куки и устанавливаем их заново.
            driver.manage().deleteAllCookies();
            goToAddress(PROJECT_PAGE_URL);
            // А вот и сохраненные куки пригодились.
            setCookie();
            //driver.manage().addCookie(new Cookie(COOKIE_NAME, BAPID_VALUE));
            // Обновить страницу
            goToAddress(PROJECT_PAGE_URL);
            // Thread.sleep(5000);
        }

        // Логин с пустым именем.
         @Test
        public void goToAddressWithoutName() throws InterruptedException{
            goToAddress(LOGIN_PAGE_URL);
            driver.findElement(By.xpath("//button[@name='_submit']")).click();

            // Код демострирует, что модальное сообщение сгенерированное Бутстрап не прехватывается.
            List<String> windowHandles = new ArrayList(driver.getWindowHandles());
            int a = windowHandles.size();
            for (int b = 0; b<a;) {
                System.out.println(windowHandles.get(b));
                b++;
            }

        }

        @Nested // Добавление данных.
        @DisplayName("Тесты внутри CRM. Добавление данных")

        class addValueTest {
            @Test
                    public void addValue() throws InterruptedException{
                    goToAddress(LOGIN_PAGE_URL);
                    getLoginCookie();
                    goToAddress(PROJECT_PAGE_URL);
                    setCookie();
                    // Эта переменная пригодится в ассертах.
                    String findName = PROJECTS_NAME +  UUID.randomUUID().toString();
                // Заполняем поля.
                // Наименование проекта.
                driver.findElement(By.xpath("//input[@data-ftid='crm_project_name']")).sendKeys(findName);
                // Выбор организации с ожиданим элемента.
                WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 3);
                waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='company-container']/div/a/span[1]"))));
                 driver.findElement(By.xpath("//div[@class='company-container']/div/a/span[1]")).click();
                 driver.findElement(By.xpath("//div[@id=\"select2-drop\"]/ul[2]/li[5]/div")).click();

                // Комментарий к проекту.
                driver.findElement(By.xpath("//textarea[@data-name='field__unique-description']")).sendKeys(PROJECTS_NAME);

                // Выбор поля "Контактное лицо".

                driver.findElement(By.xpath("//div[@class=\"select2-container select2\"]/a")).click();
                driver.findElement(By.xpath("//div[@id=\"select2-drop\"]/ul[2]/li[3]/div")).click();

                // Селекты.

                // Радиокнопки. Сделал через вебэлемент. Т.к. черех селект не получилось.
                // crm_project[type]

                driver.findElement(By.xpath("//div[@class='controls']/div/div/label")).click();

                // Приоритет по значению.
                Select crm_projectDropDown = new Select(driver.findElement(By.name("crm_project[priority]")));
                crm_projectDropDown.selectByValue("3");
                System.out.println("crm_projectDropDown: " + crm_projectDropDown.getFirstSelectedOption().getText());

                //Финансирование
                // Приоритет по значению 2.
                //crm_project[financeSource]

                Select financeSourceDropDown = new Select(driver.findElement(By.name("crm_project[priority]")));
                financeSourceDropDown.selectByValue("2");
                System.out.println("financeSource: " + financeSourceDropDown.getFirstSelectedOption().getText());

                //Подразделение по индексу.
                //crm_project[businessUnit]

                Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
                businessUnitDropDown.selectByIndex(1);
                System.out.println("businessUnit: " +  businessUnitDropDown.getFirstSelectedOption().getText());

                //Куратор проекта.
                //crm_project[curator]

                Select curatorDropDown = new Select(driver.findElement(By.name("crm_project[curator]")));
                curatorDropDown.selectByIndex(1);
                System.out.println("curator: " + curatorDropDown.getFirstSelectedOption().getText());


                //Руководитель проекта.
                // crm_project[rp]

                Select rpDropDown = new Select(driver.findElement(By.name("crm_project[rp]")));
                rpDropDown.selectByIndex(3);
                System.out.println("rp: " + rpDropDown.getFirstSelectedOption().getText());

                //Менеджер.
                // crm_project[manager]

                Select managerDropDown = new Select(driver.findElement(By.name("crm_project[manager]")));
                managerDropDown.selectByIndex(3);
                System.out.println("manager: " + managerDropDown.getFirstSelectedOption().getText());

                // Кликаем на первую кнопочку.
                // //button[@class="btn btn-success main-group action-button"][1]

                driver.findElement(By.xpath(".//button[@class=\"btn btn-success main-group action-button\"][1]")).click();

                // Поиск всплывающего сообщения-подтверждения, добавление Assert
                String message = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                        "div[class='message']"))).getText();
                System.out.println("Message: " + message);
                // Assertions.assertTrue(message.contains("Заявка на расход сохранена"));
                Assertions.assertTrue(message.contains("Проект сохранен"));

                //h1[@class="user-name"]
                // driver.get(PROJECT_PAGE_UPDATED_URL);
                String projName = driver.findElement(By.xpath(".//h1[@class=\"user-name\"]")).getText().toString();
                System.out.println("Ожидается название проекта: " + findName);
                System.out.println("Получено название проекта: " + projName);
                Assertions.assertTrue(projName.contains(findName));

            }

        }

    }


    // Процедура инициализации и настройки драйвера.
    private void initChromeDriver() {
        ChromeOptions options = new ChromeOptions(); // Настраиваем драйвер Хрома.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER); // СТратегия загрузки.
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  // Неявное ожидание 3 секунды.
        driver.manage().window().maximize();
    }

    // Процедура логина по адресу.
    private void goToAddress(String address) throws InterruptedException {
        // Открыавем старницу логина.
        driver.get(address);
    }

    // Получение кук.

    private void  getLoginCookie(){
        // Везде используем xpath
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys(STUDENT_LOGIN);
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys(STUDENT_PASSWORD);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        // Сохранить Куку
        cookie = driver.manage().getCookieNamed(COOKIE_NAME);
        // Сохраняем куку как переменную. Пусть болтается, потом придумаю как ее заюзать.
        BAPID_VALUE = cookie.getValue();
        System.out.println("CH: " + cookie.getName() + ": [" + BAPID_VALUE + "]" );

    }

    // Установка кук для сессии.
    private void setCookie() {
        driver.manage().addCookie(new Cookie(COOKIE_NAME, BAPID_VALUE));
    }



}
