package org.prog.homework.pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected final WebDriver driver;
    private final String url;

    public AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void loadPage() {
        driver.get("about::blank");
        driver.get(url);
    }

    public void quitDriver() {
        driver.quit();
    }

}
