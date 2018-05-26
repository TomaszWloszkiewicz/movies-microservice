package com.manager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "Movies")
public class Movie {
    @Id
    private String id;
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String title;
    @Min(value = 1)
    @Max(value = 10)
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
