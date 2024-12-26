import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Webform {
    WebDriver driver;

    @BeforeAll

    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headedless");
        driver = new ChromeDriver(ops);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void submitForm() throws InterruptedException {

        Faker faker = new Faker();


        String randomName = faker.name().firstName();

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        List<WebElement> textbox = driver.findElements(By.className("form-control"));
        textbox.get(0).sendKeys(randomName);
       Thread.sleep(500);
        textbox.get(1).sendKeys("01707724711");
        Thread.sleep(500);

        textbox.get(2).sendKeys("12/25/2024");
        Thread.sleep(500);

        textbox.get(3).sendKeys("anik@gmail.com");
       Thread.sleep(500);

      Uitls.scroll(driver,400);

        textbox.get(4).sendKeys("A detail-oriented SQA Engineer Anik Kumar Das seeking new opportunities to leverage testing expertise and contribute to high-quality software development.");
        Thread.sleep(2000);


        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys("D:\\bug2.png");


        WebElement checkBox = driver.findElement(By.id("edit-age"));
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox).click().perform();



        WebElement submit = driver.findElement(By.id("edit-submit"));
        Actions submitAction = new Actions(driver);
        submitAction.moveToElement(submit).click().perform();
        driver.switchTo().alert().accept();

        Thread.sleep(4000);


        WebElement resultElement = driver.findElement(By.tagName("h1"));

       String TittleExpected = "Thank you for your submission!";
       String TittleActual = resultElement.getText();
       Assertions.assertTrue(TittleActual.contains(TittleExpected));

       //Thread.sleep(1000);



    }

    @AfterAll

    public void closeDriver() {
        driver.close();
    }


}
