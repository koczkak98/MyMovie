package com.example.mymovie.MyMovie.model;

import java.util.ArrayList;
import java.util.List;

public class RatingInfo {

    private List<Rating> ratings;

    public RatingInfo() {
        this.ratings = new ArrayList<Rating>();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
