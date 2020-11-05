package com.example.mymovie.MyMovie.model;

import com.example.mymovie.MyMovie.db.JDBC_SQLHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class MovieInfo {

    private Integer userID;
    private String userName;
    private ArrayList<Movie> myMovies;
    private ArrayList<Rating> myMoviesRating;


    public MovieInfo(Integer userID) throws SQLException {

        this.userID = userID;
        JDBC_SQLHandler mySqlHandler = new JDBC_SQLHandler();
        this.userName = mySqlHandler.getUserById(this.userID).getUserName();
        this.myMovies = new ArrayList<Movie>();
        this.myMoviesRating = new ArrayList<Rating>();
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)  {
        this.userName = userName;
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
