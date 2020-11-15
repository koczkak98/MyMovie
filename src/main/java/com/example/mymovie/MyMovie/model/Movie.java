package com.example.mymovie.MyMovie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {


    @Id
    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieID;

    @Column(name = "movie_name")
    private String title;

    @Column(name = "movie_category")
    private String category;

    @Column(name = "movie_description")
    private String description;

    @Column(name = "movie_ageLimit")
    private int ageLimit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "movies_rating_mapping",
            joinColumns = @JoinColumn(name = "movieId"))
    @Column(name = "ratingId")
    private List<Integer> ratingIDs;

    @Transient
    private List<Rating> ratings;



    public Movie() {
        this.ratingIDs = new ArrayList<Integer>();
    }

    public Movie(Integer movieID) {
        super();
        this.movieID = movieID;
        this.ratingIDs = new ArrayList<Integer>();
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

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Integer> getRatingIDs() {
        return ratingIDs;
    }

    public void setRatingIDs(List<Integer> ratingIDs) {
        this.ratingIDs = ratingIDs;
    }

    public void addRating (Rating rating)
    {
        this.ratings.add(rating);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
