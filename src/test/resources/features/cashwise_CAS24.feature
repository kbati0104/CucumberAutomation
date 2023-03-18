@cashwise2 @regression
Feature:

  @cashwise2
  Scenario: Sales button verification

    Given User is on the cashwise login page
    When  User login with valid "email" and "password"
    When User click on the Products and Services option
    When User click on the delete button
    Then User should see message