Feature: User API Testing

  Scenario: Fetch valid user by ID
    When I send a GET request to "/users/1"
    Then The response should contain user details

  Scenario: Fetch non-existent user
    When I send a GET request to "/users/999"
    Then I should receive a 404 error

  Scenario: Send invalid request body
    When I send a POST request to "/users" with invalid JSON
    Then I should receive a 400 error
