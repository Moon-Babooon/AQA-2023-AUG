package org.prog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.pages.GoogleCalendarPage;
import org.prog.pages.GooglePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GoogleTests {

    private GooglePage googlePage;
    private GoogleCalendarPage googleCalendarPage;

    @BeforeSuite
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        googlePage = new GooglePage(driver);
        googleCalendarPage = new GoogleCalendarPage(driver);
    }

    @BeforeMethod
    public void loadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookiesIfPresent();
    }

    @Test
    public void testsGoogleApps(){
        googlePage.openAppsMenu().selectApp("Calendar");
        googleCalendarPage.openAppsMenu();
    }

    @Test(dataProvider = "celebrityNames")
    public void testGoogleSearch(String name, boolean usingEnter) {
        googlePage.setSearchFieldValue(name);
        Assert.assertTrue(googlePage.isSearchSuggestionDisplayed(),
                "Search suggestions not displayed!");
        googlePage.performDropDownSearch(usingEnter);

        List<WebElement> searchResultHeaders = googlePage.getSearchHeaders();

        Assert.assertTrue(searchResultHeaders
                        .stream()
                        .map(we -> we.getText())
                        .anyMatch(text -> text.contains(name)),
                "Celebrity name was not present in search headers!");
    }

    @AfterSuite
    public void tearDown() {
        googlePage.quitDriver();
    }

    @DataProvider(name = "celebrityNames")
    public Object[][] celebrityNames() {
        return new Object[][]{
                {"Christian Bale", true},
                {"Brad Pitt", false},
                {"Madonna", true},
                {"Joe Biden", false}
        };
    }
}
