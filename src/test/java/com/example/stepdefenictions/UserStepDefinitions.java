package com.example.stepdefenictions;



import com.example.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class UserStepDefinitions {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserService userServiceMock;

    private ResponseEntity<String> response;
    private final String baseUrl;

    public UserStepDefinitions() {
        MockitoAnnotations.openMocks(this);
        this.baseUrl = ConfigReader.getProperty("base.url");
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        String fullUrl = baseUrl + endpoint;

        // Mock API responses
        when(userService.getUserById(1)).thenReturn(userService.getUserById(1));
        when(userService.getUserById(999)).thenReturn(null);

        // Extract ID from URL and call the service
        int userId = Integer.parseInt(endpoint.split("/")[2]);
        String userResponse = String.valueOf(userServiceMock.getUserById(userId));
        response = userResponse != null ? new ResponseEntity<>(userResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Then("The response should contain user details")
    public void the_response_should_contain_user_details() {
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertTrue(response.getBody().contains("John Doe"));
    }

    @Then("I should receive a 404 error")
    public void i_should_receive_a_404_error() {
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
}

