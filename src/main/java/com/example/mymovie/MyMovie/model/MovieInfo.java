package com.example.mymovie.MyMovie.model;

import com.example.mymovie.MyMovie.db.Hibernate_SQLHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieInfo {

    private Integer userID;
    private ArrayList<Movie> myMovies;
    private List<Rating> myMoviesRating;


    public MovieInfo(Integer userID) {

        this.userID = userID;
        this.myMovies = new ArrayList<Movie>();
        this.myMoviesRating = new ArrayList<Rating>();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    public void setMyMovies(ArrayList<Movie> myMovies) {
        this.myMovies = myMovies;
    }

    public void addMovie(Movie movie) {
        this.myMovies.add(movie);
    }

    public List<Rating> getMyMoviesRating() {
        return myMoviesRating;
    }

    public void setMyMoviesRating(List<Rating> myMoviesRating) {
        this.myMoviesRating = myMoviesRating;
    }

    public void addRating (Rating rating)
    {
        this.myMoviesRating.add(rating);
    }
}
