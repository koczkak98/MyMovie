package com.example.mymovie.MyMovie.controller;

import com.example.mymovie.MyMovie.db.Hibernate_SQLHandler;
import com.example.mymovie.MyMovie.model.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            String userName,
            Model model) throws SQLException {

        System.out.println("************-STARTED*****************");

        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        String conditional = hibernate_sqlHandler.checkUserByName(userID);
        hibernate_sqlHandler.close();
        if(!user.getUserName().equals(conditional))
        {
            return "error.html";
        }


        /** Hibernate */
        hibernate_sqlHandler.open();
        user = hibernate_sqlHandler.getUserByIdAndName(user.getUserID(), user.getUserName());
        hibernate_sqlHandler.close();

        System.out.println("After use HibernateSQLHandler: " + user.getUserID());

        MovieInfo mi = new MovieInfo(userID);
        List<Integer> myMovieIDs = user.getMovieIDs();

        RestTemplate restTemplate = new RestTemplate();
        for (int idx = 0; idx < myMovieIDs.size(); idx++) {
            Movie movie = restTemplate.getForObject("http://localhost:8081/getmovie/" + myMovieIDs.get(idx), Movie.class);

            mi.addMovie(movie);
            System.out.println(movie.getRatingIDs());
            System.out.println(movie.getRatings());

            for (int i = 0; i < movie.getRatings().size(); i++) {
                mi.addRating(movie.getRatings().get(i));
            }
        }
        /** VIEW */
        model.addAttribute("myMovies", mi);
        model.addAttribute("movieSize", myMovieIDs.size());

        model.addAttribute("user", user);

        return "mymovies_user.html";
        }


    @GetMapping("/getuser/ratingbyuserid/{userid}")
    public String getRatingByUserId(@PathVariable("userid") int userId,
                                    Model model)
    {
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();

        hibernate_sqlHandler.open();
        User user = hibernate_sqlHandler.getUserById(userId);
        hibernate_sqlHandler.close();

        MovieInfo mi = new MovieInfo(userId);
        List<Integer> myMovieIDs = user.getMovieIDs();


        RestTemplate restTemplate = new RestTemplate();
        for (int idx = 0; idx < myMovieIDs.size(); idx++) {
            Movie movie = restTemplate.getForObject("http://localhost:8081/getmovie/" + myMovieIDs.get(idx), Movie.class);
            System.out.println(movie);

            for (int e = 0; e < movie.getRatings().size(); e++) {
                System.out.println("UserId: " + userId + "Score: " + movie.getRatings().get(e).getScores());

                if (movie.getRatings().get(e).getScores() == userId) {
                    mi.getMyMovies().clear();
                    mi.getMyMoviesRating().clear();
                    mi.addMovie(movie);
                    mi.addRating(movie.getRatings().get(e));
                    System.out.println("UserId: " + userId + "Score: " + movie.getRatings().get(e).getScores());
                }
            }
        }
        model.addAttribute("myMovies", mi);
        model.addAttribute("user", user);

        return "ratingbyuserid.html";
    }

    @GetMapping("/deletemovie/userid/{userID}/movieid/{movieID}")
    public String deleteMovieByuserID (@PathVariable("userID") int userID,
                                       @PathVariable("movieID") int movieID,
                                       Model model)
    {
        Hibernate_SQLHandler hibernate_sqlHandler = new Hibernate_SQLHandler();
        hibernate_sqlHandler.open();
        User user = hibernate_sqlHandler.getUserById(userID);
        hibernate_sqlHandler.close();

        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject("http://localhost:8081/deletemovie/"+movieID, Movie.class);

        model.addAttribute("message", "Delete the movie: " + movie.getTitle());

        return "deletemovie.html";
    }
}
