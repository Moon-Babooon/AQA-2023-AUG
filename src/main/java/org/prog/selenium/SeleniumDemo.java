package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumDemo {


    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = null;
        try {
            webDriver = new ChromeDriver();
            webDriver.get("https://google.com/");
            List<WebElement> buttons = webDriver.findElements(By.tagName("button"));
            if (buttons.size() == 5) {
                buttons.get(3).click();
            }
            //work with google.com
            WebElement inputField = webDriver.findElement(By.name("q"));
            inputField.sendKeys("Christian Bale");
            inputField.click();

            new WebDriverWait(webDriver, Duration.ofSeconds(5L))
                    .until(ExpectedConditions.attributeToBe(inputField, "aria-expanded", "true"));
            List<WebElement> searchDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(30L))
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']")));
            if (searchDropDown.size() < 2) {
                throw new RuntimeException("This is not working");
            }
            inputField.sendKeys(Keys.ENTER);
            Boolean searchOK = new WebDriverWait(webDriver, Duration.ofSeconds(30L))
                    .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//a/h3"),
                            "Christian Bale"));
            System.out.println("Christian Bale is found: " + searchOK);
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}
