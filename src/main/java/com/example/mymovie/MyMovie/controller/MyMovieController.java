package com.example.mymovie.MyMovie.controller;

import com.example.mymovie.MyMovie.db.MySqlHandler;
import com.example.mymovie.MyMovie.model.Movie;
import com.example.mymovie.MyMovie.model.MovieInfo;
import com.example.mymovie.MyMovie.model.Rating;
import com.example.mymovie.MyMovie.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class MyMovieController {

    @GetMapping("/getuser")
    public String welcome(Model model) {
        model.addAttribute("user", new User());
        return "input.html";
    }


    @PostMapping("/getuser")
    public String getUserMovies(
            User user,
            int userID,
            Model model) throws SQLException {

        System.out.println("************-STARTED*****************");

        System.out.println(userID);
        System.out.println(user.getUserID());
        System.out.println(user.getUserName());

        /** View */
        MySqlHandler mySqlHandler = new MySqlHandler();


        user = mySqlHandler.getUserById(userID);
        System.out.println(user.getUserID());


        RestTemplate restTemplate = new RestTemplate();

        MovieInfo mi = new MovieInfo(userID);
        ArrayList<Integer> myMovieIDs = user.getMovieIDs();


        for (int i = 0; i < myMovieIDs.size(); i++) {
            Movie movie = restTemplate.getForObject("http://localhost:8081/getmovie/" + myMovieIDs.get(i), Movie.class);
            Rating rating = restTemplate.getForObject("http://localhost:8082/getrating/" + myMovieIDs.get(i), Rating.class);


            mi.addMovie(movie);
            mi.addRating(rating);
        }


        model.addAttribute("myMovies", mi);
        model.addAttribute("movieSize", myMovieIDs.size());

        model.addAttribute("user", user);


        /** View */

        return "mymovies_user.html";
    }


    @GetMapping("/getuser/addmovie/{userId}/{movieId}")
    public String addmovie (
            @PathVariable("userId") int userId,
            @PathVariable("movieId") int movieId,
            Model model) throws SQLException {



        MySqlHandler mySqlHandler = new MySqlHandler();
        User user = mySqlHandler.getUserById(userId);

        if (user.getMovieIDs().contains(movieId) == false)
        {
            user = mySqlHandler.addMovie(userId, movieId);
        }
        else
        {
            System.out.println("Már hozzáadtad egyszer!");
            model.addAttribute("message", "Már hozzáadtad egyszer!");
        }



        return "seenmovies.html";
    }



    @GetMapping("/deletemovie")
    public String deleteMovie(
            @RequestParam("userid") int userID,
            @RequestParam("movieid") int movieID,
            Model model) throws InvalidParameterException, SQLException {
        System.out.println("Delete Started...");

        MySqlHandler mySqlHandler = new MySqlHandler();
        User user = mySqlHandler.getUserById(userID);

        if(user.getMovieIDs().contains(movieID) == false)
        {
            throw new InvalidParameterException("Can't execute");
        }
        else
        {
            mySqlHandler.deleteMovie(userID, movieID);
        }

        return "deletemovie.html";

    }

}
