package homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RozetkaTest {

    private WebDriver driver;
    private String browser = "chrome";

    @BeforeTest
    public void browserSetUp() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.err.println("Choose the correct browser");
        }
    }

    @Test
    public void searchTest() {



        driver.manage().window().maximize();
        // Open the page
        driver.get("https://rozetka.com.ua");
        // Search for iPhone
        WebElement inputField = driver.findElement(By.cssSelector("input[name='search']"));
        inputField.sendKeys("iPhone");
        // Click on Search btn
        WebElement searchBtn = driver.findElement(By.cssSelector("button[class='button button_color_green button_size_medium search-form__submit']"));
        searchBtn.click();
        // Wait until page is fully loaded
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Apple iPhone']")));
        List<WebElement> productIcons = driver.findElements(By.cssSelector("a.goods-tile__picture"));
        // Confirm the page has products
        Assert.assertTrue(productIcons.size() >= 1, "Product icons not found");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
