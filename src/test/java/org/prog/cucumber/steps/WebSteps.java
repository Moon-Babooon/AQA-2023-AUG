package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.prog.dto.NameDto;
import org.prog.dto.UserDto;
import org.prog.pages.GooglePage;
import org.prog.util.DataHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Assert;

public class WebSteps {

    @Autowired
    @Qualifier("googlePage")
    private GooglePage googlePage;

    @Autowired
    private DataHolder dataHolder;

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
        NameDto randomUser = ((UserDto) dataHolder.get(alias)).getName();
        googlePage.setSearchFieldValue(randomUser.getFirst() + " " + randomUser.getLast());
        googlePage.performMainSearch(true);
    }

    @Then("i can see {string} name and last name in search result")
    public void checksearchResults(String alias) {
        NameDto randomUser = ((UserDto) dataHolder.get(alias)).getName();
        Assert.assertTrue(
                googlePage.getSearchHeaders().stream()
                        .anyMatch(header -> header.getText()
                                .contains(randomUser.getFirst() + " " + randomUser.getLast())));
    }
}
