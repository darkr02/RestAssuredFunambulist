package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;

public class AddEmployees {
    Faker faker = new Faker();

    @Test
    public void postTest()
    {
        // this way is not recommended
        Response response = given().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": \"1\",\n" +
                        "    \"firstname\": \"Sammy\",\n" +
                        "    \"lastname\": \"Das\",\n" +
                        "    \"email\": \"abc@gmail.com\"\n" +
                        "}")
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();

    }

    @Test
    public void postSecondWay()
    {
        //Pass it from an external file
        //use this only for static data

        Response response = given().contentType(ContentType.JSON)
                .body(new File(System.getProperty("user.dir")+"/src/test/resources/test.json"))
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void postThirdWay() throws IOException {
        //read request body from an external file and covert to String
        //log the request body to the console
        //change few parameters in the request

        String reqBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/test.json")));

        //Create random values and replace the parameters
        //update id
        reqBody = reqBody.replace("idvalue", String.valueOf(faker.number().numberBetween(100,1000)));
        //update FirstName
        reqBody = reqBody.replace("fname", faker.name().firstName());
        //update LastName
        reqBody = reqBody.replace("lname", faker.name().lastName());
        //update Email
        reqBody = reqBody.replace("emailid", faker.internet().emailAddress());

        Response response = given().contentType(ContentType.JSON)
                .body(reqBody)
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
    }


    @Test
    public void postFourthWay() {
        //{} -> use maps
        //[] -> use list
        //serializer - convert your language objects to - byte stream - json
        //verbose and not suitable for big json files
        //generic type like Object needs to be mentioned

        Map<String,Object> reqMap = new LinkedHashMap<>();
        reqMap.put("id",String.valueOf(faker.number().numberBetween(100,1000)));
        reqMap.put("firstname", faker.name().firstName());
        reqMap.put("lastname", faker.name().lastName());
        reqMap.put("email", faker.internet().emailAddress());

        List<String> jobsList = new ArrayList<>();
        jobsList.add("tester");
        jobsList.add("trainer");
        reqMap.put("jobs", jobsList);
        Map<String,Object> favFoodMap = new HashMap<>();
        favFoodMap.put("breakfast", "idly");
        favFoodMap.put("lunch", "rice");
        List<String> dinnerList = new ArrayList<>();
        dinnerList.add("chapati");
        dinnerList.add("milk");
        favFoodMap.put("dinner", dinnerList);
        reqMap.put("favFoods",favFoodMap);

        Response response = given().contentType(ContentType.JSON)
                .body(reqMap)
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void postFifthWay() {
        //{} -> use maps
        //[] -> use list
        //serializer - convert your language objects to - byte stream - json
        //verbose and not suitable for big json files
        //generic type like Object needs to be mentioned

        JSONObject reqMap = new JSONObject();
        reqMap.put("id",String.valueOf(faker.number().numberBetween(100,1000)));
        reqMap.put("firstname", faker.name().firstName());
        reqMap.put("lastname", faker.name().lastName());
        reqMap.put("email", faker.internet().emailAddress());
        reqMap.accumulate("email", faker.internet().emailAddress());
        //reqMap.putOnce("email","abc");//This is for Duplicate key
        reqMap.putOpt("email1",null);

        JSONArray jobsList = new JSONArray();
        jobsList.put("tester");
        jobsList.put("trainer");

        reqMap.put("jobs", jobsList);
        JSONObject favFoodMap = new JSONObject();
        favFoodMap.put("breakfast", "idly");
        favFoodMap.put("lunch", "rice");
        JSONArray dinnerList = new JSONArray();
        dinnerList.put("chapati");
        dinnerList.put("milk");
        favFoodMap.put("dinner", dinnerList);
        reqMap.put("favFoods",favFoodMap);

        Response response = given().contentType(ContentType.JSON)
                .body(reqMap.toMap())
                //.body(reqMap.toString())
                .log()
                .all()
                .post("http://localhost:3000/employees")
                .andReturn();
        response.prettyPrint();
    }

}
