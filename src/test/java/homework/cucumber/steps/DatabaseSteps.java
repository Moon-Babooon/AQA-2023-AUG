package homework.cucumber.steps;

import dataholder.DataHolder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;

import java.sql.*;

public class DatabaseSteps {

    @When("save the {string} and the {string} in database")
    public void storeProductToDB(String titleKey, String priceKey) {
        storingData(titleKey, priceKey);
    }


    @And("get the {string} and the {string} from database")
    public void getProductData(String titleKey, String priceKey) {
        getData(titleKey, priceKey);
    }

    @SneakyThrows
    @And("clear the {string} from database")
    public void clearDB(String productName) {
        System.out.println("Clearing DB...");
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProductDB", "testUsername", "testPassword");
            statement = connection.createStatement();
            String queryBase = "DELETE FROM goods WHERE product_name='%s';";
            String query = String.format(queryBase, DataHolder.getInstance().get(productName));
            statement.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        } finally {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        }
    }

    @SneakyThrows
    public void storingData(String title, String price) {
        System.out.println("Storing data in DB...");
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProductDB", "testUsername", "testPassword");
            statement = connection.createStatement();
            title = DataHolder.getInstance().get(title).toString();
            int price_value = Integer.parseInt(DataHolder.getInstance().get(price).toString());
            String queryBase = "INSERT INTO goods (%s) VALUES (%s);";
            String columns = "product_name, price";
            String values = "'%s', '%s'";
            String query = String.format(
                    queryBase,
                    columns,
                    String.format(values,
                            title, price_value));
            statement.execute(query);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        }
    }

    @SneakyThrows
    public void getData(String title_value, String price_value) {
        System.out.println("Getting the data...");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProductDB", "testUsername", "testPassword");
            statement = connection.createStatement();
            String productName = DataHolder.getInstance().get(title_value).toString();
            String queryBase = "SELECT price FROM goods WHERE product_name='%s';";
            String query = String.format(
                    queryBase,
                    productName);
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String price = resultSet.getString("price");
                DataHolder.getInstance().put(price_value, price);
            }
        } catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException();
        } finally {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
    }

    @SneakyThrows
    public void updateData(String new_price, String title) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProductDB", "testUsername", "testPassword");
            statement = connection.createStatement();
            String queryBase = "UPDATE goods SET price='%s' WHERE product_name='%s';";
            String product = DataHolder.getInstance().get(title).toString();
            String query = String.format(queryBase, new_price, product);
            statement.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        } finally {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        }
    }

}
