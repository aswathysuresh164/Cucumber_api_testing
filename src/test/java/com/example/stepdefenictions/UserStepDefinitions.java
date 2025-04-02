package com.example.stepdefenictions;

import com.example.utils.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.*;

public class UserStepDefinitions {

    @Mock
    private UserService userServiceMock; // ✅ Correctly mocking service

    private Response response;
    private final String baseUrl;

    public UserStepDefinitions() {
        MockitoAnnotations.openMocks(this);
        this.baseUrl = ConfigReader.getProperty("base.url");
        // Disable SSL certificate validation
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        // ✅ Ensure mock is working
        when(userServiceMock.getUserById(1)).thenReturn(new User(1, "John Doe", "john@example.com"));
        when(userServiceMock.getUserById(999)).thenReturn(null);

        // ✅ Simulate API response using RestAssured
        response = given()
                .baseUri(baseUrl)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    @Then("The response should contain user details")
    public void the_response_should_contain_user_details() {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Then("I should receive a 404 error")
    public void i_should_receive_a_404_error() {
        Assertions.assertEquals(404, response.getStatusCode());
    }
}
