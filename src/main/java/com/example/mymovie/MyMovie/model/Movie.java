package com.example.mymovie.MyMovie.model;

public class Movie {


    private Integer movieID;
    private String title;
    private String category;
    private String description;


    public Movie() {
        super();
    }

    public Movie(Integer movieID) {
        super();
        this.movieID = movieID;
    }


    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
