package com.example.mymovie.MyMovie.model;

import java.util.ArrayList;

public class User {

    private int userID;
    private ArrayList<Integer> movieIDs;

    public User() {
        this.movieIDs = new ArrayList<Integer>();
    }


    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }


    public ArrayList<Integer> getMovieIDs() {
        return movieIDs;
    }


    public void setMovieIDs(ArrayList<Integer> movieIDs) {
        this.movieIDs = movieIDs;
    }

    public void addMovieID(Integer movieID) {
        this.movieIDs.add(movieID);
    }

}
