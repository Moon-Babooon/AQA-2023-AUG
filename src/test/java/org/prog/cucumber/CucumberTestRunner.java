package org.prog.cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.prog.cucumber.steps.WebSteps;
import org.prog.pages.GooglePage;
import org.prog.util.DataHolder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(features = "src/test/resources/features",
        glue = "org.prog",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html"
        })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);

        WebSteps.googlePage = new GooglePage(driver);
    }

    @BeforeMethod
    public void resetHolder() {
        DataHolder.getInstance().reset();
    }

    @AfterSuite
    public void tearDown() {
        WebSteps.googlePage.quitDriver();
    }
}
