package com.example.mymovie.MyMovie.model;

import java.util.ArrayList;

public class User {

    private int userID;
    private String userName;
    private ArrayList<Integer> movieIDs;

    public User() {
    }

    public User(Integer userID) {
        this.userID = userID;
        this.movieIDs = new ArrayList<Integer>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void deleteMovieID(Integer movieID) {
        this.movieIDs.remove(movieID);
    }

}
