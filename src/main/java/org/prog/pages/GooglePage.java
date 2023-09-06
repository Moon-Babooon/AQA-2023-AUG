package org.prog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage extends AbstractPage {

    private final static int GOOGLE_COOKIES_FORM_BUTTONS_COUNT = 5;
    private final static int GOOGLE_COOKIES_FORM_ACCEPT_BTN_INDEX = 3;
    private final static int MAIN_SEARCH_BTN_INDEX = 1;
    private final static int DROP_DOWN_SEARCH_BTN_INDEX = 0;

    private final static By SEARCH_INPUT_LOCATOR = By.name("q");
    private final static By GOOGLE_SEARCH_BUTTON_LOCATOR = By.name("btnK");
    private final static By GOOGLE_SEARCH_RESULTS_HEADER_LOCATOR = By.xpath("//a/h3");
    private final static By GOOGLE_SEARCH_SUGGESTION_DROP_DOWN_LOCATOR =
            By.xpath("//ul[@role='listbox']");
    private final static By GOOGLE_COOKIES_BUTTONS_LOCATOR = By.tagName("button");
    private final GoogleAppsMenu googleAppsMenu;

    public GooglePage(WebDriver driver) {
        super(driver, "https://google.com/");
        this.googleAppsMenu = new GoogleAppsMenu(driver);
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> buttons = driver.findElements(GOOGLE_COOKIES_BUTTONS_LOCATOR);
        if (buttons.size() == GOOGLE_COOKIES_FORM_BUTTONS_COUNT) {
            buttons.get(GOOGLE_COOKIES_FORM_ACCEPT_BTN_INDEX).click();
        }
    }

    public void setSearchFieldValue(String value) {
        WebElement inputField = driver.findElement(SEARCH_INPUT_LOCATOR);
        inputField.clear();
        // clear text if selenium clear is not working
//        for (int i = 0; i < inputField.getText().length(); i++) {
//            inputField.sendKeys(Keys.BACK_SPACE);
//        }
        inputField.sendKeys(value);
    }

    public boolean isSearchSuggestionDisplayed() {
        WebElement inputField = driver.findElement(SEARCH_INPUT_LOCATOR);
        inputField.click();
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.attributeToBe(inputField, "aria-expanded", "true"));
        List<WebElement> searchDropDown = new WebDriverWait(driver, Duration.ofSeconds(30L))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        GOOGLE_SEARCH_SUGGESTION_DROP_DOWN_LOCATOR));
        return searchDropDown.size() > 1;
    }

    public void performDropDownSearch(boolean usingEnter) {
        executeSearch(usingEnter, DROP_DOWN_SEARCH_BTN_INDEX);
    }

    public void performMainSearch(boolean usingEnter) {
        executeSearch(usingEnter, MAIN_SEARCH_BTN_INDEX);
    }

    public List<WebElement> getSearchHeaders() {
        return driver.findElements(GOOGLE_SEARCH_RESULTS_HEADER_LOCATOR);
    }

    public GoogleAppsMenu openAppsMenu(){
        //TODO: add google apps click
        return googleAppsMenu;
    }

    private void executeSearch(boolean usingEnter, int buttonIndex) {
        if (usingEnter) {
            driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(Keys.ENTER);
        } else {
            List<WebElement> searchButtons = driver.findElements(GOOGLE_SEARCH_BUTTON_LOCATOR);
            searchButtons.get(buttonIndex).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(30L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        GOOGLE_SEARCH_RESULTS_HEADER_LOCATOR, 6));
    }
}
