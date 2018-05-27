package com.movie.com.movie.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

@RestController
@RequestMapping("/movies")
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


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMovie(@PathVariable("id") String movieId, @RequestBody Movie movie){
        Movie result = movieService.getMovieById(movieId);
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


    @RequestMapping(method = RequestMethod.GET)
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


    @RequestMapping(value = "/sortedByRating", method = RequestMethod.GET)
    public ResponseEntity<?> getMoviesSortedByRating(){
        List<Movie> result = movieService.getMoviesSortedByRating();
        return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/review", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReviewForMovie(@PathVariable("id") String movieId,
                                                  @RequestBody Review review) {
        if(movieService.getMovieById(movieId) == null){
            return new Response("Could not add review. Movie does not exist.",
                    HttpStatus.NOT_FOUND);
        }

        Map<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("reviewContent", review.getReviewContent());
        uriVariables.put("userName", review.getUserName());
        uriVariables.put("movieId", movieId);
        uriVariables.put("rating", review.getRating().toString().replace(".", "p"));

        new AsyncRestTemplate().getForEntity("http://localhost:8081/reviews/add/{movieId}/{reviewContent}/{userName}/{rating}",
                Review.class, uriVariables);


        return new Response("System received the request. It will process as fast as possible.",
                HttpStatus.OK);
    }
}
