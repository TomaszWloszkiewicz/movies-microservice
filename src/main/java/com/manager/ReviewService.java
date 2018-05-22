package com.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    public Review getReviewById(String id){
        return reviewRepository.findById(id);
    }

    public boolean addReview(Review review){
        if (review.getReviewContent().length() < 10
                || review.getReviewContent().length() > 250) {
            return false;
        }else if(review.getRating()<1d || review.getRating() > 10d){
            return false;
        }else if(review.getUserName().length() < 1
                || review.getUserName().length() > 15){
            return false;
        }
        review.setApproved(true);
        reviewRepository.save(review);
        return review.isApproved();
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