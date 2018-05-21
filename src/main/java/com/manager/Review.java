package com.manager;

public class Review {
    private String userName;
    private int rating;
    private String review;
    private boolean approved;

    protected Review() {
    }

    public Review(String userName, int rating, String review, boolean approved) {
        this.userName = userName;
        this.review = review;
        this.rating = rating;
        this.approved = approved;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public String getReview(){ return review; }

    public boolean isApproved() {
        return approved;
    }
}
