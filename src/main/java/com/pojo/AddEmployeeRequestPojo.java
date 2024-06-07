package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {"jobs","firstname","lastname"})
public class AddEmployeeRequestPojo {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> jobs;
    private FavFoods favFoods;
    private List<Marks> marks;

    public AddEmployeeRequestPojo(String id, String firstname, String lastname, String email, List<String> jobs, FavFoods favFoods, List<Marks> marks) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.jobs = jobs;
        this.favFoods = favFoods;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public FavFoods getFavFoods() {
        return favFoods;
    }

    public List<Marks> getMarks() {
        return marks;
    }
}