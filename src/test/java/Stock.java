import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Stock {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headless"); // Corrected typo from "--headedless"
        driver = new ChromeDriver(ops);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void scrapWebTableData() throws IOException, InterruptedException {

        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");



        List<WebElement> data = driver.findElements(By.className("table"));

        WebElement table = data.get(1);


        List<WebElement> tablerowsdata = table.findElements(By.tagName("tr"));




        FileWriter writer = new FileWriter("./src/test/resources/test.txt");


        for (WebElement row : tablerowsdata) {

            List<WebElement> cells = row.findElements(By.tagName("td"));


            for (WebElement cell : cells) {
                String cellText = cell.getText();

                System.out.print(cellText + "\t");
                writer.write(cellText + "\t");
            }
            System.out.println();

            writer.write("\n");
        }


        writer.close();
    }

    @AfterAll
    public void closeDriver() {
        driver.close();
    }
}

