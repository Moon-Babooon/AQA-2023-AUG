package org.prog.homework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class RozetkaPage extends AbstractPage{
    private final By INPUT_FIELD_LOCATOR = By.cssSelector("input[name='search']");
    private final By SEARCH_BUTTON_LOCATOR = By.cssSelector("button.button_color_green");
    private final By PRODUCTS_LOCATOR = By.cssSelector("a.goods-tile__picture");

    public RozetkaPage(WebDriver driver) {
        super(driver, "https://rozetka.com.ua");
    }

    public void enterSearchValue(String searchFor) {
        WebElement inputField = driver.findElement(INPUT_FIELD_LOCATOR);
        inputField.sendKeys(searchFor);
        WebElement searchButton = driver.findElement(SEARCH_BUTTON_LOCATOR);
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
    }

    public boolean numberOfResultsToBeMoreThan(int productCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS_LOCATOR));
        List<WebElement> productIcons = driver.findElements(PRODUCTS_LOCATOR);
        boolean resultsAreVisible = productIcons.size() > productCount;
        return resultsAreVisible;
    }

}
