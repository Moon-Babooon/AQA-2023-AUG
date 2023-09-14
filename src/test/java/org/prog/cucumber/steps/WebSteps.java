package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.prog.dto.NameDto;
import org.prog.dto.UserDto;
import org.prog.pages.GooglePage;
import org.prog.util.DataHolder;
import org.testng.Assert;

public class WebSteps {

    public static GooglePage googlePage;

    @Given("i load google page")
    public void loadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookiesIfPresent();
    }

    @When("i google that user")
    public void googleUser() {
        NameDto randomUser = DBSteps.randomUser.getName();
        googlePage.setSearchFieldValue(randomUser.getFirst() + " " + randomUser.getLast());
        googlePage.performMainSearch(true);
    }

    @When("i google for {string}")
    public void googleUser(String alias) {
        NameDto randomUser = ((UserDto) DataHolder.getInstance().get(alias)).getName();
        googlePage.setSearchFieldValue(randomUser.getFirst() + " " + randomUser.getLast());
        googlePage.performMainSearch(true);
    }

    @Then("i can see {string} name and last name in search result")
    public void checksearchResults(String alias) {
        NameDto randomUser = ((UserDto) DataHolder.getInstance().get(alias)).getName();
        Assert.assertTrue(
                googlePage.getSearchHeaders().stream()
                        .anyMatch(header -> header.getText()
                                .contains(randomUser.getFirst() + " " + randomUser.getLast())));
    }
}
