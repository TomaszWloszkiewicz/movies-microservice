package com.movie.com.manager.service;

import com.movie.com.manager.repository.MovieRepository;
import com.movie.model.Movie;
import com.movie.com.manager.repository.MovieRepository;import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
                Arrays.asList("Omar Sy", "Anna Le Ny"),
                MovieService.getDataFormatFromNow(Instant.now()));

        Movie movie2 = new Movie("Killer",
                7.7d, "Juliusz Machulski",
                Arrays.asList("Cezary Pazura", "Jerzy Stuhr"),
                MovieService.getDataFormatFromNow(Instant.now()));

        Movie movie3 = new Movie("TheGreenMile",
                8.61d, "Frank Darabont",
                Arrays.asList("Tom Hanks", "David Morse"),
                MovieService.getDataFormatFromNow(Instant.now()));

        Movie movie4 = new Movie("TheShawshankRedemption",
                8.75d, "Frank Darabont",
                Arrays.asList("Tim Robbins", "Morgan Freeman"),
                MovieService.getDataFormatFromNow(Instant.now()));


        this.movieRepository.deleteAll();

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3, movie4);

        this.movieRepository.save(movies);

    }
}