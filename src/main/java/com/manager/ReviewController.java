package com.manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReview(@PathVariable("id") String id){
        Review result = reviewService.getReviewById(id);
        if(result == null){
            return new Response("Review does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Review>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMovie(Review review){
        Review result = reviewService.getReviewById(review.getId());
        if(result == null){
            return new Response("Review does not exist", HttpStatus.NOT_FOUND);
        }
        reviewService.updateReview(review);
        return new Response("Review updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@PathVariable("id") String id){
        Review result = reviewService.getReviewById(id);
        if(result == null){
            return new Response("Review does not exist", HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReview(id);
        return new Response("Review deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMovies(){
        List<Review> result = reviewService.getAllReviews();
        if(result.isEmpty()){
            return new Response("Could not finde review", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Review>>(result, HttpStatus.OK);
    }
}