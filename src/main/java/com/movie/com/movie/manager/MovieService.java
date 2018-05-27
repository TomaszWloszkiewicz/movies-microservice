package com.movie.com.movie.manager;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.movie.com.movie.manager.Movie;
import com.movie.com.movie.manager.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class MovieService {
    private MovieRepository movieRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }


    public Movie getMovieById(String id){
        return movieRepository.findById(id);
    }

    public boolean addMovie(Movie movie){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        if(violations.size()>0){
            return false;
        }
        movie.setAddedAt(getDataFormatFromNow(Instant.now()));
        movieRepository.save(movie);
        return true;
    }

    public boolean updateMovie(Movie movie, String movieId){
        Movie movie1 = movieRepository.findById(movieId);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        if(violations.size()>0 || movie1 == null){
            return false;
        }
        movie.setAddedAt(getDataFormatFromNow(Instant.now()));
        movieRepository.save(movie);
        return true;
    }

    public void deleteMovie(String id){
        movieRepository.delete(id);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getMovieWithRatingGreaterThan(Double rating){
        return movieRepository.findByRatingGreaterThan(rating);
    }

    public List<Movie> getMoviesSortedByRating(){
        List<Movie> result = movieRepository.findAll();
        result.sort(Comparator.comparing(Movie::getRating).reversed());
        return result;
    }

    public static String getDataFormatFromNow(Instant now){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(Date.from(now));
    }
}
