package com.tests;

import com.pojo.AddEmployeeRequestPojo;
import com.pojo.FavFoods;
import com.pojo.Marks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
public class AddEmployeesUsingPojo {

    @Test
    public void postWithPojo() {

        List<String> jobs = new ArrayList<>();
        jobs.add("Tester");
        jobs.add("Trainer");

        /*List<String> dinner = new ArrayList<>();
        dinner.add("paratha");
        dinner.add("chicken curry");
        */
        List<String> dinner = Arrays.asList("Paratha", "Chicken Curry");
        FavFoods favFood = new FavFoods("Sandwitch", "Mixed Fried Rice", dinner);
        Marks marks = new Marks(80,75);
        Marks marks1 = new Marks(67,78);
        List<Marks> mark = Arrays.asList(marks,marks1);
        AddEmployeeRequestPojo requestPojo = new AddEmployeeRequestPojo("456", "Naresh", "Mandula", "naresh@gmail.com", jobs, favFood, mark);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestPojo)
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),201,"Response status Code check");
        Assert.assertEquals(response.jsonPath().getString("firstname"),"Naresh1");

    }
}