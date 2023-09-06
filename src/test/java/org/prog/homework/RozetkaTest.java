package org.prog.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.prog.homework.pages.RozetkaPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class RozetkaTest {

    private RozetkaPage rozetkaPage;
    private WebDriver driver;
    private String browser = "chrome";

    @BeforeSuite
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
        rozetkaPage = new RozetkaPage(driver);
    }

    @BeforeTest
    public void loadRozetkaPage() {
        rozetkaPage.loadPage();
    }

    @Test
    public void searchTest() {

        driver.manage().window().maximize();
        // Search for iPhone
        rozetkaPage.enterSearchValue("iPhone");
        // Confirm the page has products
        boolean successfulResult = rozetkaPage.numberOfResultsToBeMoreThan(10);
        Assert.assertTrue(successfulResult, "Search results do not meet the expectations");
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
