package com.manager;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
@EnableAsync
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
        this.reviewRepository.deleteAll();
    }


    public Review getReviewById(String id){
        return reviewRepository.findById(id);
    }

   @Async
    public void addReview(Review review){
        try {
            //Someone has to accept review.. wait a minute please
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        review.setApproved(true);
        reviewRepository.save(review);
    }

    public void updateReview(Review review){
        reviewRepository.save(review);
    }

    public void deleteReview(String id){
        reviewRepository.delete(id);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
}