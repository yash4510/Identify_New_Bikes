Feature: Login page Email Vaildation

  Scenario Outline: validate the email address and handle the error message
    Given The user should on the accountsgoogle page
    When the enters "<email>" into the email field
    And clicks on the next button
    Then capture the error message

    Examples: 
      | email |
      |     1 |
      |     2 |
      |     3 |
      |     4 |
      |     5 |
      |     6 |
      |     7 |
      |     8 |
      |     9 |
     

  Scenario: close the google Window
    Then close the google window
