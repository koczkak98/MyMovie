package com.example.mymovie.MyMovie.model;

public class Rating {


    private Integer movieId;
    private double scores;
    private double totalScore;
    private double averages;


    public Rating() {
    }

    public Rating(Integer movieId) {
        this.movieId = movieId;
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

    public void setAverages(double averages) {
        this.averages = averages;
    }

    public double getAverages() {
        return averages;
    }

    public void setAverages() {
        this.averages = ((this.scores / this.totalScore)) * 100;
    }

}
