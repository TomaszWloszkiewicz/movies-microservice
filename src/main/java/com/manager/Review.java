package com.manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Reviews")
public class Review {
    @Id
    private String id;
    private String movieId;
    private String reviewContent;
    private Double rating;
    private String userName;
    @JsonIgnore
    private boolean approved;

    public Review() {
    }

    public Review(String id, String movieId, String reviewContent, Double rating, String userName, boolean approved) {
        this.id = id;
        this.movieId = movieId;
        this.reviewContent = reviewContent;
        this.rating = rating;
        this.userName = userName;
        this.approved = approved;
    }


    public String getId() {
        return id;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Double getRating() {
        return rating;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

