Feature: Car Page Interaction

  Scenario: Display the popular cars
    Given the user is on the Used Cars Page
    When the user clicks on "Read More"
    Then the popular cars should be displayed

  Scenario: Log in as a registered user
    When the user clicks on the user icon to log in
    Then the sign-in pop-up option will appear
    And the user should click on the Google sign-in
