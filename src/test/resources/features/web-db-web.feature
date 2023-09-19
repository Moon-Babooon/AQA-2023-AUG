Feature: Web and DB integration test
  Scenario: Search for the product, save it in the database then get the product data from db and verify the price
    Given load Rozetka page
    Given search for "MacBook Pro"
    And get the "product" and the "price" of the product
    When save the "product" and the "price" in database
    And get the "product" and the "price" from database
    When search for the same "product" on Rozetka from DB
    Then compare the "price" of the "product" from database and web page and update the price in case of a failure
    And clear the "product" from database