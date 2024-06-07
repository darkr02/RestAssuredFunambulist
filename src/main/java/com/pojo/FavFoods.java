package com.pojo;

import java.util.List;

public class FavFoods {
    private String breakfast;
    private String lunch;
    private List<String> dinner;

    //Parameterized Constructor
    public FavFoods(String breakfast, String lunch, List<String> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    //getters
    public String getBreakfast() {
        return breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public List<String> getDinner() {
        return dinner;
    }




}

