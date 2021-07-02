package HW_03;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MaineTest {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String PROJECT_PAGE_URL = "https://crm.geekbrains.space/project/create/";
    private static final String STUDENT_LOGIN = "user";
    private static final String STUDENT_PASSWORD = "1234";
    private static final String PROJECTS_NAME = "PROJECT SAMPLE";
    private static final WebDriver driver;


    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Неявное ожидание 3 секунды.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {

        login();
        addValue();

       sleep(5000);
       driver.quit();

    }
        private static void  login() throws InterruptedException {

            // Открыавем старницу логина.
            driver.get(LOGIN_PAGE_URL);
            // Везде используем xpath
            driver.findElement(By.xpath("//input[@name='_username']")).sendKeys(STUDENT_LOGIN);
            driver.findElement(By.xpath("//input[@name='_password']")).sendKeys(STUDENT_PASSWORD);
            driver.findElement(By.xpath("//button[@name='_submit']")).click();

        }

        private static void addValue() throws InterruptedException{

            // Открыавем старницу проекта.
            driver.get(PROJECT_PAGE_URL);
            // Заполняем поля.
            // Наименование проекта.
            driver.findElement(By.xpath("//input[@data-ftid='crm_project_name']")).sendKeys(PROJECTS_NAME);


            // DIFF #2 ---------------------------------------------------------
            // WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 5);

            // Код ниже генерирует ошибку. Не может импортировать функцию. Нужно смотреть POM файл?
            // waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='company-container']/div/a/span[1]"))));
            // -----------------------------------------------------------------
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
            // //div[@class='controls']/div/div/label
            // Select crm_projectRadio = new Select(driver.findElement(By.xpath("////div/input[@name=\"crm_project[type]\"]")));
            // crm_projectRadio.selectByValue("1");
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

            //Подразделение по индексу
            //crm_project[businessUnit]

            Select businessUnitDropDown = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
            businessUnitDropDown.selectByIndex(1);
            System.out.println("businessUnit: " +  businessUnitDropDown.getFirstSelectedOption().getText());

            //Куратор проекта
            //crm_project[curator]

            Select curatorDropDown = new Select(driver.findElement(By.name("crm_project[curator]")));
            curatorDropDown.selectByIndex(1);
            System.out.println("curator: " + curatorDropDown.getFirstSelectedOption().getText());


            //Руководитель проекта
            // crm_project[rp]

            Select rpDropDown = new Select(driver.findElement(By.name("crm_project[rp]")));
            rpDropDown.selectByIndex(3);
            System.out.println("rp: " + rpDropDown.getFirstSelectedOption().getText());

            //Менеджер
            // crm_project[manager]

            Select managerDropDown = new Select(driver.findElement(By.name("crm_project[manager]")));
            managerDropDown.selectByIndex(3);
            System.out.println("manager: " + managerDropDown.getFirstSelectedOption().getText());

        }
}
