package com.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MovieSeeder implements CommandLineRunner {
    private MovieRepository movieRepository;

    public MovieSeeder(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Movie movie1 = new Movie("Intouchables",
                8.64d, "Olivier Nakache",
                Arrays.asList("Omar Sy", "Anna Le Ny"), "2011");

        Movie movie2 = new Movie("Killer",
                7.7d, "Juliusz Machulski",
                Arrays.asList("Cezary Pazura", "Jerzy Stuhr"), "1999");

        Movie movie3 = new Movie("TheGreenMile",
                8.61d, "Frank Darabont",
                Arrays.asList("Tom Hanks", "David Morse"), "1999");

        Movie movie4 = new Movie("TheShawshankRedemption",
                8.75d, "Frank Darabont",
                Arrays.asList("Tim Robbins", "Morgan Freeman"), "1994");


        this.movieRepository.deleteAll();

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3, movie4);

        this.movieRepository.save(movies);

    }
}