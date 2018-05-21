package com.manager;

import com.manager.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>,
        QueryDslPredicateExecutor<Movie> {
    Movie findById(String id);

    List<Movie> findByRatingGreaterThan(Double rating);

}
