package com.example.mymovie.MyMovie.controller;

import com.example.mymovie.MyMovie.db.Hibernate_SQLHandler;
import com.example.mymovie.MyMovie.model.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        /** JDBC */
        /**
        JDBC_SQLHandler mySqlHandler = new JDBC_SQLHandler();


        user = mySqlHandler.getUserById(userID);
         */

        /** Hibernate */
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();

        hibernate_sqlHandler.open();
        user = hibernate_sqlHandler.getUserById(user.getUserID());
        hibernate_sqlHandler.close();

        System.out.println("After use HibernateSQLHandler: " + user.getUserID());




        MovieInfo mi = new MovieInfo(userID);
        List<Integer> myMovieIDs = user.getMovieIDs();

        RestTemplate restTemplate = new RestTemplate();
        for (int idx = 0; idx < myMovieIDs.size(); idx++) {
            Movie movie = restTemplate.getForObject("http://localhost:8081/getmovie/" + myMovieIDs.get(idx), Movie.class);
            RatingInfo rating = restTemplate.getForObject("http://localhost:8082/getrating/" + myMovieIDs.get(idx), RatingInfo.class);


            mi.addMovie(movie);


            for (int i = 0; i < rating.getRatings().size(); i++)
            {
                mi.addRating(rating.getRatings().get(i));
            }

        }

        System.out.println(mi.getMyMoviesRating());


        /** VIEW */
        model.addAttribute("myMovies", mi);
        model.addAttribute("movieSize", myMovieIDs.size());

        model.addAttribute("user", user);


        /** View */

        return "mymovies_user.html";
    }

    /**

    @GetMapping("/getuser/addmovie/{userId}/{movieId}")
    public String addmovie (
            @PathVariable("userId") int userId,
            @PathVariable("movieId") int movieId,
            Model model) throws SQLException {



        JDBC_SQLHandler mySqlHandler = new JDBC_SQLHandler();
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

        JDBC_SQLHandler mySqlHandler = new JDBC_SQLHandler();
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
            */

}
