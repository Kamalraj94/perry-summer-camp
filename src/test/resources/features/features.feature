Feature: Validation of message model API's for perrys summer vacation goods and services


  @SmokeTest
Scenario: To verify send and receive message between two users are working as expected using message mode api
   Given I send a POST request for creating user 1
   When I get the details of the created user using Id
   Given I send a POST request for creating user 2
   When I get the details of the created user using Id
   And I send a Message from user 1 to user 2 "Text message is sent"
   Then I get the details of the message sent "Text message is sent"
    When I update the message details for the sent message "Text message is updated"
    And I get the details of the updated message "Text message is updated"
    When I delete the message that was created
    Then I should not receive the message details for the UUID