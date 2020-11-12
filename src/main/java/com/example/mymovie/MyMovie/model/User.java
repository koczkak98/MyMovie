package com.example.mymovie.MyMovie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "name")
    private String userName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_movie_mapping",
            joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "movieid")
    private List<Integer> movieIDs;



    public User() {
        this.movieIDs = new ArrayList<Integer>();
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

    public List<Integer> getMovieIDs() {
        return movieIDs;
    }

    public void setMovieIDs(List<Integer> movieIDs) {
        this.movieIDs = movieIDs;
    }

    public void addMovieID(Integer movieID) {
        this.movieIDs.add(movieID);
    }

    public void deleteMovieID(Integer movieID) {
        this.movieIDs.remove(movieID);
    }

}
