package com.manager;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private ReviewService reviewService;


    @Autowired
    public MovieService(MovieRepository movieRepository, ReviewService reviewService){
        this.movieRepository = movieRepository;
        this.reviewService = reviewService;
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

    public void updateMovie(Movie movie){
        movieRepository.save(movie);
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

    public boolean addReviewForMovie(Review review, String movieId){
        Movie movie = movieRepository.findById(movieId);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Review>> violations = validator.validate(review);
        if(movie == null || violations.size() > 0){
            return false;
        }
        review.setMovieId(movieId);
        reviewService.addReview(review);
        return true;
    }
}
