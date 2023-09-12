package org.prog;

import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.sql.*;

public class DBTests {

    @Test
    public void readFromDb() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db",
                    "user", "password");

            stmt = connection.createStatement();

            String query = "select * from Persons";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("PersonID"));
                System.out.println(resultSet.getString("FirstName"));
                System.out.println(resultSet.getString("LastName"));
                System.out.println(resultSet.getString("Gender"));
                System.out.println(resultSet.getString("Title"));
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
    }
}
