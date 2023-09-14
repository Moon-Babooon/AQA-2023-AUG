package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.prog.dto.ResultsDto;
import org.prog.dto.UserDto;
import org.prog.util.DataHolder;

import java.util.List;

public class RestApiSteps {

    public static List<UserDto> userDtos;

    @Given("i request {int} users from API")
    public void requestUsersFromApi(int amount) {
        userDtos = RestAssured.given()
                .header("Accept-Encoding", "UTF-8")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get("https://randomuser.me/api/")
                .body()
                .as(ResultsDto.class)
                .getResults();
    }

    @Given("i request {int} users from API as {string}")
    public void requestUsersFromApi(int amount, String alias) {
        DataHolder.getInstance().put(alias,
                RestAssured.given()
                        .header("Accept-Encoding", "UTF-8")
                        .queryParam("inc", "gender,name,nat")
                        .queryParam("noinfo")
                        .queryParam("results", amount)
                        .get("https://randomuser.me/api/")
                        .body()
                        .as(ResultsDto.class)
                        .getResults());
    }
}
