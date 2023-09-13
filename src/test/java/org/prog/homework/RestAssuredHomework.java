package org.prog.homework;

import io.restassured.RestAssured;
import org.prog.dto.ResultsDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredHomework {


    //TODO: add location to UserDto and validate
    //TODO: Check city and state not null
    //TODO: Install Docker

    @Test
    public void requestUserLocation() {

        ResultsDto dto = RestAssured.given()
                .header("Accept-Encoding", "UTF-8")
                .queryParam("inc", "name,gender,nat,location")
                .queryParam("noinfo")
                .get("https://randomuser.me/api/")
                .body()
                .as(ResultsDto.class);

        dto.getResults().forEach(userDto -> {
            System.out.println(userDto.getName().getFirst()+" "+userDto.getName().getLast()+"\n"
                    +userDto.getGender().toUpperCase()+"\n"
                    +userDto.getLocation().getCountry().toUpperCase()+"\n"
                    +"STATE: "+userDto.getLocation().getState()+" "
                    +"| CITY: "+userDto.getLocation().getCity());
        });

        dto.getResults().forEach(locationDto -> {
            Assert.assertTrue(locationDto.getLocation().getCity() != null && locationDto.getLocation().getState() != null) ;
        });

    }

}
