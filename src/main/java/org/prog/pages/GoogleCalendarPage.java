package org.prog.pages;

import org.openqa.selenium.WebDriver;

public class GoogleCalendarPage extends AbstractPage {

    private final GoogleAppsMenu googleAppsMenu;

    public GoogleCalendarPage(WebDriver driver) {
        super(driver, "https://calendar.google.com/");
        this.googleAppsMenu = new GoogleAppsMenu(driver);
    }

    public GoogleAppsMenu openAppsMenu() {
        //TODO: add google apps click
        return googleAppsMenu;
    }
}
