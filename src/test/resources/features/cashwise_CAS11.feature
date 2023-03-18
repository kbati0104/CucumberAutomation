@test @regression
Feature: CashWise Sales menu bar

@test
  Scenario: Sales button verification
    Given User is on the cashwise login page
    When User login with valid "email" and "password"
    And User click on the Sales menu bar
    Then User should provide on the 3 different pages
