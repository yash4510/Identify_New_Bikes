Feature: Validate the Home Page Functionalites

  Scenario: verify the ablity to dismiss the push notfication
    Given user on the Home page
    When user checks for any visbile push notifications
    Then the user should able to dismiss the notification sucessfully

  Scenario: Ensure the Navigating to upcoming Bikes section is functional
    When the user hovers the bike menu
    And the users selects the upciming bikes option from menu
    Then the user should redirect to the upcoming bikes section
