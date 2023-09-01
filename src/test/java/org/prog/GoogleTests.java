package org.prog;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class GoogleTests {

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void loadGooglePage() {
        driver.get("https://google.com/");
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        if (buttons.size() == 5) {
            buttons.get(3).click();
        }
    }

    @Test(dataProvider = "celebrityNames")
    public void testGoogleSearch(String name) {
        WebElement inputField = driver.findElement(By.name("q"));
        inputField.sendKeys(name);
        inputField.click();

        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.attributeToBe(inputField, "aria-expanded", "true"));
        List<WebElement> searchDropDown = new WebDriverWait(driver, Duration.ofSeconds(30L))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']")));
        if (searchDropDown.size() < 2) {
            Assert.fail("Google search drop-down is not displayed!");
        }
        inputField.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, Duration.ofSeconds(30L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a/h3"), 10));

        Boolean searchOK = driver.findElements(By.xpath("//a/h3"))
                .stream()
                .map(we -> we.getText())
                .anyMatch(text -> text.contains(name));

        Assert.assertTrue(searchOK, "Celebrity name was not present in search headers!");
    }

    @DataProvider(name = "celebrityNames")
    public Object[][] celebrityNames() {
        return new Object[][]{
                {"Christian Bale"},
                {"Brad Pitt"},
                {"Madonna"},
                {"Joe Biden"}
        };
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
