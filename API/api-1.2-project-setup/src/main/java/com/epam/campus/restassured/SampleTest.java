package com.epam.campus.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getPostByIdTest() {

        Response response =
                RestAssured
                        .given()
                        .when()
                        .get("/posts/1")
                        .then()
                        .extract()
                        .response();

        // Status Code Validation
        Assert.assertEquals(response.getStatusCode(), 200);

        // Header Validation
        Assert.assertEquals(response.getHeader("Content-Type"),
                "application/json; charset=utf-8");

        // Body Validation
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"));
        Assert.assertNotNull(response.jsonPath().getString("body"));
        Assert.assertNotNull(response.jsonPath().getInt("userId"));
    }
}