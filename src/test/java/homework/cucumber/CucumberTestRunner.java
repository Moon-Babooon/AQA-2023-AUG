package homework.cucumber;

import dataholder.DataHolder;
import driver.WebDriverFactory;
import homework.cucumber.steps.WebSteps;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.prog.homework.pages.RozetkaPage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html"
        })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = WebDriverFactory.getDriver();

        WebSteps.rozetkaPage = new RozetkaPage(driver);
    }
    @BeforeMethod
    public void clearHolder() {
        DataHolder.getInstance().reset();
    }


    @AfterSuite
    public static void tearDown() {
        WebSteps.rozetkaPage.quitDriver();
    }

}
