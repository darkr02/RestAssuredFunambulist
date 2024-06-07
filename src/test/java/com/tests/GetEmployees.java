package com.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class GetEmployees {

    @Test
    public void getTest()
    {
        Response response =
        given().get("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());

    }
}
