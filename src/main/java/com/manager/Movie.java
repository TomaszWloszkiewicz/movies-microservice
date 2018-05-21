package com.manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Komp on 2018-05-18.
 */

@Document(collection = "Movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private Double rating;
    private String director;
    private List<String> actors;
    private String addedAt;

    public Movie(){

    }

    public Movie(String title, Double rating, String director,
                 List<String> actors, String addedAt) {
        this.title = title;
        this.rating = rating;
        this.director = director;
        this.actors = actors;
        this.addedAt = addedAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }
}
