package com.epam.campus.restassured;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class SampleTest {

    private Response response;

    @BeforeClass
    public void setUp(){
        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request
        response = RestAssured
                        .given()
                        .when()
                        .get("/posts/1");
    }

    @Test
    public void validateStatusCodePost(){
        // Status Code
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");
    }

    @Test
    public void validateHeaderPost(){
        // Header Validation
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8",
                "Content-Type mismatch");
    }

    @Test
    public void validateBodyPost(){
        // ---------- Body Validation ----------

        int id = response.jsonPath().getInt("id");
        int userId = response.jsonPath().getInt("userId");
        String title = response.jsonPath().getString("title");
        String body = response.jsonPath().getString("body");

        Assert.assertEquals(id, 1, "Post ID is incorrect");
        Assert.assertEquals(userId, 1, "User ID is incorrect");
        Assert.assertNotNull(title, "Title is null");
        Assert.assertNotNull(body, "Body is null");
    }

    @AfterSuite
    public void printReport(){

        // Optional: print response
        response.prettyPrint();
    }

}

