package com.manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Reviews")
public class Review {
    @Id
    private String id;
    private String movieId;
    @Size(min = 10, max = 250)
    private String reviewContent;
    @Min(value = 1)
    @Max(value = 10)
    private Double rating;
    @Size(min = 3, max = 15)
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

