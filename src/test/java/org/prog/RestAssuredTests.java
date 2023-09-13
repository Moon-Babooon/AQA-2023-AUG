package org.prog;

import io.restassured.RestAssured;
import org.prog.dto.ResultsDto;
import org.testng.annotations.Test;

public class RestAssuredTests {


    //TODO: add location to UserDto and validate
    //TODO: Check city and state not null
    //TODO: Install Docker
    @Test
    public void requestUser() {
        ResultsDto dto = RestAssured.given()
                .header("Accept-Encoding", "UTF-8")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", "10")
                .get("https://randomuser.me/api/")
                .body()
                .as(ResultsDto.class);

        dto.getResults().get(4).getName().setTitle("ABC");

        dto.getResults().forEach(userDto -> {
            System.out.println(
                    userDto.getName().getTitle() + " " +
                            userDto.getName().getFirst() + " " +
                            userDto.getName().getLast() + " from " +
                            userDto.getNat()
            );
        });

//        RestAssured.given().body(dto).post("https://some.service.com/update/users");
    }


}
