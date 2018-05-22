package com.manager;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(movie.getTitle().length()<3 || movie.getTitle().length()>50){
            return false;
        }else if(!(movie.getTitle().chars().allMatch(Character::isLetter))){
            return false;
        }else if(movie.getRating()<1d || movie.getRating()>10d){
            return false;
        }else{
            movie.setAddedAt(getDataFormatFromNow(Instant.now()));
            movieRepository.save(movie);
            return true;
        }
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
        if(movie == null){
            return false;
        }
        review.setMovieId(movieId);
        return reviewService.addReview(review);
    }
}
