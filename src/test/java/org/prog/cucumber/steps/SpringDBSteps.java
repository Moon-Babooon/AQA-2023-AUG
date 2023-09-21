package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.prog.db.Persons;
import org.prog.db.PersonsJpa;
import org.prog.dto.UserDto;
import org.prog.util.DataHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpringDBSteps {

    @Autowired
    private PersonsJpa personsJpa;

    @Autowired
    private DataHolder dataHolder;

    public static UserDto randomUser;

    @Given("SPRING i store these users in DB")
    public void storeUsersToDB() {
        RestApiSteps.userDtos.stream().map(Persons::fromDto)
                .forEach(personsJpa::save);
    }

    @Given("SPRING i store {string} users in DB")
    public void storeUsersToDB(String alias) {
        List<UserDto> userDtos = (List<UserDto>) dataHolder.get(alias);
        userDtos.stream().map(Persons::fromDto).forEach(personsJpa::save);
//        for (UserDto dto : userDtos){
//            Persons persons = Persons.builder()
//                    .firstName(dto.getName().getFirst())
//                    .lastName(dto.getName().getLast())
//                    .gender(dto.getName().getTitle())
//                    .gender(dto.getGender())
//                    .build();
//            personsJpa.save(persons);
//        }
    }

    @When("SPRING i pick random user from DB")
    public void pickRandomUser() {
        randomUser = UserDto.fromDB(personsJpa.getRandomEntry());
    }

    @When("SPRING i pick random user from DB as {string}")
    public void pickRandomUser(String alias) {
        dataHolder.put(alias, UserDto.fromDB(personsJpa.getRandomEntry()));
    }
}
