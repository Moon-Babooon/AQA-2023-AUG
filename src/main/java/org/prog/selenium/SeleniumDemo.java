package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
            Thread.sleep(2500);
            inputField.sendKeys(Keys.ENTER);
            Thread.sleep(2500);
            List<WebElement> searchHeaders = webDriver.findElements(By.xpath("//a/h3"));
            System.out.println("Christian Bale is found: " +
                    searchHeaders.stream().anyMatch(webElement -> webElement.getText().contains("Christian Bale")));
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}
