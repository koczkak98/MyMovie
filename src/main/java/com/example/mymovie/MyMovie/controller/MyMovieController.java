package com.example.mymovie.MyMovie.controller;

import com.example.mymovie.MyMovie.model.Movie;
import com.example.mymovie.MyMovie.model.MovieInfo;
import com.example.mymovie.MyMovie.model.Rating;
import com.example.mymovie.MyMovie.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
public class MyMovieController {

    @GetMapping("/getuser")
    public String welcome (Model model)
    {
        model.addAttribute("user", new User ());
        return "mymovies.html";
    }


    @PostMapping("/getuser")
    public String getUserMovies(
            User user,
            @RequestParam("userId") int userID,
            Model model)
    {
        /** 01 */

        /** Dummy Object */
        user.addMovieID(123);
        user.addMovieID(456);
        user.addMovieID(789);


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


        return "mymovies.html";
    }

}
