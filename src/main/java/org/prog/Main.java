package org.prog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");
        List<WebElement> elements = driver.findElements(By.tagName("h1"));
        System.out.println(elements.size());
        WebElement iFrame = driver.findElement(By.name("iframeResult"));
        driver.switchTo().frame(iFrame);
        //tryhome
        List<WebElement> homeIcon = driver.findElements(By.id("tryhome"));
        WebElement iFrameInner = driver.findElement(By.xpath("//body/iframe"));
        List<WebElement> logo = driver.findElements(By.id("sn-back"));
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();

        iFrameInner.getShadowRoot().findElement(By.name("asdasd"));
    }
}
