package com.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    public boolean saveMovie(Movie movie){
        if(movie.getTitle().length()<3 || movie.getTitle().length()>50 ||
                !(movie.getTitle().chars().allMatch(Character::isLetter))){
            return false;
        }else{
            movie.setAddedAt(sdf.format(Date.from(Instant.now())));
            movieRepository.save(movie);
            return true;
        }
    }

    public List<Movie> getMoviesSortedByRating(){
        List<Movie> result = movieRepository.findAll();
        result.sort(Comparator.comparing(Movie::getRating).reversed());
        return result;
    }
}
