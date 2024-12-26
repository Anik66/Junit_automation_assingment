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

public class UserRegistration {

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
    public void Submit() throws InterruptedException {

        Faker faker = new Faker();


        String randomName = faker.name().firstName();
        String randomLastName = faker.name().lastName();
        String randomEmail = faker.internet().emailAddress();
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        List<WebElement> textbox = driver.findElements(By.className("input-text"));
        List<WebElement> Checkbox = driver.findElements(By.cssSelector("[type=radio]"));
        textbox.get(0).sendKeys(randomName);
        Thread.sleep(300);

        textbox.get(1).sendKeys(randomEmail);
        Thread.sleep(300);

        Actions actions = new Actions(driver);
        actions.moveToElement(Checkbox.get(0)).click().perform();
        Thread.sleep(200);

        textbox.get(2).sendKeys("P%w12!D3$RbC9^");
        Thread.sleep(300);

        textbox.get(3).sendKeys(randomLastName );
        Thread.sleep(300);

        String year = "2001";
        String month = "May";
        String date = "10";


        WebElement dateInput = driver.findElement(By.cssSelector("input[data-label='Date of Birth']"));
        dateInput.click();


        while (true) {

            WebElement monthDropdown = driver.findElement(By.xpath("//select[@aria-label='Month']"));
            WebElement yearInput = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));


            String currentMonth = monthDropdown.findElement(By.cssSelector("option:checked")).getText();
            String currentYear = yearInput.getAttribute("value");


            if (currentMonth.equalsIgnoreCase(month) && currentYear.equals(year)) {
                break;
            }


            WebElement prevButton = driver.findElement(By.xpath("//span[@class='flatpickr-prev-month']"));
            prevButton.click();
            //Thread.sleep(10);
        }


        WebElement desiredDate = driver.findElement(By.xpath("//span[@class='flatpickr-day ' and text()='" + date + "']"));
        desiredDate.click();
      //  Thread.sleep(10);




        textbox.get(5).sendKeys("Bangladeshi");
        Thread.sleep(300);

        List<WebElement> elements = driver.findElements(By.cssSelector("input[type='text']"));
        elements.get(4).sendKeys("(123) 456-7890");
        Thread.sleep(300);

        Select options = new Select(driver.findElement(By.id("country_1665629257")));
        options.selectByVisibleText("Bangladesh");
        options.selectByValue("BD");
        Thread.sleep(3000);

        elements.get(5).sendKeys("(123) 456-7867");

        textbox.get(7).sendKeys("3");
        Thread.sleep(300);


//        textbox.get(8).sendKeys("101");
//        Thread.sleep(300);

//        textbox.get(9).sendKeys("Student of AIUB");
//        Thread.sleep(200);
//        actions.moveToElement(Checkbox.get(4)).click().perform();
//        Thread.sleep(200);
//        actions.moveToElement(Checkbox.get(5)).click().perform();
//        Thread.sleep(200);
//        actions.moveToElement(Checkbox.get(13)).click().perform();
//        Thread.sleep(200);

//        Select options1 = new Select(driver.findElement(By.id("select_1665628361")));
//        options1.selectByVisibleText("Town Hall");
//        options1.selectByValue("Town Hall");
//        Thread.sleep(100);

        Uitls.scroll(driver, 500);
        WebElement checkBox = driver.findElement(By.id("privacy_policy_1665633140"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(checkBox).click().perform();

        WebElement submitButton = driver.findElement(By.cssSelector("button.btn.button.ur-submit-button"));


        submitButton.click();

        WebElement successMessage = driver.findElement(By.xpath("//ul[normalize-space()='User successfully registered.']"));

        String TittleExpected = "User successfully registered.";
        String TittleActual = successMessage.getText();

        Assertions.assertTrue(TittleActual.contains(TittleExpected));




    }

    @AfterAll

    public void closeDriver() {
        driver.close();
    }












}










