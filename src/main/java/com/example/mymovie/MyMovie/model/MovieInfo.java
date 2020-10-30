package com.example.mymovie.MyMovie.model;

import java.util.ArrayList;

public class MovieInfo {

    private Integer userID;
    private ArrayList<Movie> myMovies;
    private ArrayList<Rating> myMoviesRating;


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

    public ArrayList<Rating> getMyMoviesRating() {
        return myMoviesRating;
    }

    public void setMyMoviesRating(ArrayList<Rating> myMoviesRating) {
        this.myMoviesRating = myMoviesRating;
    }

    public void addMovie(Movie movie) {
        this.myMovies.add(movie);
    }

    public void addRating(Rating rating){
        this.myMoviesRating.add(rating);
    }
}
