Feature: DB and API integration tests

  Background:
    Given i load google page

#  Scenario: Fetch and store users using API and DB
#    Given i request 1 users from API
#    Given i store these users in DB
#    Given i load google page
#    When i pick random user from DB
#    When i google that user
#    Then i can see that users name and last name in search result

  Scenario Outline: Fetch and store users using API and DB using Data Holder
    Given i request <amount> users from API as "crowd_1"
    Given i store "crowd_1" users in DB
    When i pick random user from DB as "average_joe"
    When i google for "average_joe"
    Then i can see "average_joe" name and last name in search result
    Examples:
      | amount |
      | 2      |
      | 1      |
      | 3      |

