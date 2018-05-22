package com.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){

        this.movieService = movieService;
    }



    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMovie(@RequestBody Movie movie){
        Boolean addedFlag = movieService.addMovie(movie);
        if(!addedFlag){
            return new Response("Title must have at least 3 characters up to 50 and can contain only letters. "
                    + "Rating should be between 1 and 10.", HttpStatus.BAD_REQUEST);
        }
        return new Response("Movie added", HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMovie(@PathVariable("id") String id){
        Movie result = movieService.getMovieById(id);
        if(result == null){
            return new Response("Movie does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movie>(result, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMovie(Movie movie){
        Movie result = movieService.getMovieById(movie.getId());
        if(result == null){
            return new Response("Movie does not exist", HttpStatus.NOT_FOUND);
        }
        movieService.updateMovie(movie);
        return new Response("Movie updated", HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable("id") String id){
        Movie movie = movieService.getMovieById(id);
        if(movie == null){
            return new Response("Movie does not exist", HttpStatus.NOT_FOUND);
        }
        movieService.deleteMovie(id);
        return new Response("Movie deleted", HttpStatus.OK);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMovies(){
        List<Movie> result = movieService.getAllMovies();
        if(result.isEmpty()){
            return new Response("Could not finde movie", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/ratingGreaterThan/{rating}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoviesByRating(@PathVariable("rating") Double rating){
        List<Movie> result = movieService.getMovieWithRatingGreaterThan(rating);
        if(result.isEmpty()){
            return new Response("Could not finde movie", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/all/sortedByRating", method = RequestMethod.GET)
    public ResponseEntity<?> getMoviesSortedByRating(){
        List<Movie> result = movieService.getMoviesSortedByRating();
        if(result.isEmpty()){
            return new Response("Could not finde movie", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/addreview", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForMovie(@PathVariable("id") String id,
                                                  @RequestBody Review review){
        boolean addedFlag = movieService.addReviewForMovie(review, id);
        if(addedFlag == false){
            return new Response("Could not add review", HttpStatus.NOT_FOUND);
        }
        return new Response("Review added", HttpStatus.OK);
    }
}
