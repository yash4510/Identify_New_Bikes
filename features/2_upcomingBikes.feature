Feature: Upcoming Bike Page Interaction

  Scenario: Select a specific brand from the brand dropdown
    Given the user is on the Upcoming Bikes page
    When the user selects the "Honda" brand from the dropdown
    Then only "Honda" bikes should be displayed on the list

  Scenario: Load more bikes
    When the user clicks on the "Load More" button
    Then All bikes should be displayed on the page
   

  Scenario: Fetch bike details under a certain price
    When the user views the list of bikes
    Then bikes priced under "4.00" lakhs should be fetched
    And the details should include the name and price

  Scenario: Navigate to the Used Cars section
    When the user navigates to the Used Cars menu
    And selects "Used Cars in Chennai" from the options
    Then the user should be redirected to the Used Cars page
