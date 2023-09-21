package org.prog.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.prog.util.WebDriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Bean(name = "googlePage")
    public GooglePage googlePage() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver = webDriverFactory.getDriver();
        return new GooglePage(driver);
    }
}
