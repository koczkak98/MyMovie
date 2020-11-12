package com.example.mymovie.MyMovie.model;

import javax.persistence.*;


public class Rating {

    private Integer ratingId;

    private double scores;

    private double totalScore;

    private Integer movieId;

    private double averages;


    public Rating() {
    }

    public Rating(Integer movieId) {
        this.movieId = movieId;
    }


    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }


    public double getAverages() {
        return averages;
    }

    public void setAverages() {
        this.averages = ((this.scores / this.totalScore)) * 100;
    }


}
