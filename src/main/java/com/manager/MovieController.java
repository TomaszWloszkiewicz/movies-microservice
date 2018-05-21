package com.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    public MovieController(MovieRepository movieRepository, MovieService movieService){
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Movie movie){
        Boolean addedFlag = movieService.saveMovie(movie);
        if(addedFlag){
           return new ResponseEntity("Movie added", HttpStatus.OK);
        }
        return new ResponseEntity("Title must have at least 3 characters up to 50 " +
                "and can contain only letters", HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie read(@RequestParam(name = "id") String id){
        Movie movie = movieRepository.findOne(id);
        return movieRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Movie movie){
        movieRepository.save(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        movieRepository.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @RequestMapping(value = "/ratingGreaterThan/{rating}", method = RequestMethod.GET)
    public List<Movie> getMoviesRating(@PathVariable("rating") Double rating){
        return movieRepository.findByRatingGreaterThan(rating);
    }

    @RequestMapping(value = "/all/sortedByRating", method = RequestMethod.GET)
    public List<Movie> getMoviesSortedByRating(){
        return movieService.getMoviesSortedByRating();
    }
}
