package org.prog.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Component
@NoArgsConstructor
public class WebDriverFactory {

    @SneakyThrows
    public WebDriver getDriver() {
        String type = System.getProperty("driver.type", "CHROME");
        BrowserType browserType = BrowserType.valueOf(type);
        switch (browserType) {
            case CHROME:
                return initLocalDriver();
            case CHROME_REMOTE:
                return initRemoteDriver();
            default:
                return initDockerDriver();
        }
    }

    private WebDriver initLocalDriver() {
        WebDriver driver = new ChromeDriver(options());
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options());
    }

    private WebDriver initDockerDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://selenoid-selenoid-1:4444/wd/hub"), options());
    }

    private ChromeOptions options() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return chromeOptions;
    }

    private FirefoxOptions ffOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return firefoxOptions;
    }
}
