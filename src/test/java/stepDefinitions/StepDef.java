package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDef extends Utils {

    TestDataBuild data = new TestDataBuild();
    ResponseSpecification respSpec;
    RequestSpecification mainRequest;
    Response response;
    static String placeID;

    @Given("Add place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        mainRequest = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println("++++++++++++++     " + resourceAPI.getResource());

        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();


        if(method.equalsIgnoreCase("POST"))
            response = mainRequest.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = mainRequest.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            response = mainRequest.when().delete(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("PUT"))
            response = mainRequest.when().put(resourceAPI.getResource());
    }

    @Then("User gets response status code {int}")
    public void userGetsResponseStatusCode(int arg0) {

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {

        Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);

    }

    @And("Verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedName, String resource) throws IOException {

        placeID = getJsonPath(response, "place_id");
        mainRequest = given().spec(requestSpecification()).queryParam("place_id", placeID);
        userCallsWithHttpRequest(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);

    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {

        mainRequest = RestAssured.given().spec(requestSpecification()).body(data.deletePlacePayLoad(placeID));
    }
}
