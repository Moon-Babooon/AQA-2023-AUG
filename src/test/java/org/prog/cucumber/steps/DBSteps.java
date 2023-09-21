package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.prog.dto.NameDto;
import org.prog.dto.UserDto;
import org.prog.util.BrowserType;
import org.prog.util.DataHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;

public class DBSteps {

    @Autowired
    private DataHolder dataHolder;

    public static UserDto randomUser;

    @Given("i store these users in DB")
    public void storeUsersToDB() {
        RestApiSteps.userDtos.forEach(this::storeUserToDB);
    }

    @Given("i store {string} users in DB")
    public void storeUsersToDB(String alias) {
        List<UserDto> userDtos = (List<UserDto>) dataHolder.get(alias);
        userDtos.forEach(this::storeUserToDB);
    }

    @When("i pick random user from DB")
    public void pickRandomUser() {
        randomUser = getRandomUserFromDB();
    }

    @When("i pick random user from DB as {string}")
    public void pickRandomUser(String alias) {
        dataHolder.put(alias, getRandomUserFromDB());
    }

    @SneakyThrows
    private UserDto getRandomUserFromDB() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(getSQLHost(),
                    "user", "password");

            stmt = connection.createStatement();

            String query = "SELECT * FROM Persons ORDER BY RAND() LIMIT 1";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return UserDto.builder()
                        .gender(resultSet.getString("Gender"))
                        .name(NameDto.builder()
                                .first(resultSet.getString("FirstName"))
                                .last(resultSet.getString("LastName"))
                                .title(resultSet.getString("Title"))
                                .build())
                        .build();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to init DB connection!");
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION!");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        throw new RuntimeException("No suers were picked from DB!");
    }


    @SneakyThrows
    private void storeUserToDB(UserDto dto) {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(getSQLHost(),
                    "user", "password");

            stmt = connection.createStatement();

            String queryBase = "insert into Persons (%s) VALUES (%s)";
            String columnNames = "FirstName, LastName, Title, Gender";
            String values = "'%s', '%s', '%s', '%s'";


            String query = String.format(
                    queryBase, //base string insert into ...
                    columnNames, //columns that we want to insert
                    String.format(values,
                            dto.getName().getFirst(),
                            dto.getName().getLast(),
                            dto.getName().getTitle(),
                            dto.getGender()));
            stmt.execute(query);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to init DB connection!");
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION!");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String getSQLHost() {
        String type = System.getProperty("driver.type", "DOCKER");
        BrowserType browserType = BrowserType.valueOf(type);
        if (BrowserType.DOCKER.equals(browserType)) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        }
        return "jdbc:mysql://localhost:3306/db";
    }
}
