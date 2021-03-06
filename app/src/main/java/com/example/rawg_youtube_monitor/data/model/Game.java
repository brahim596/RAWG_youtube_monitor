package com.example.rawg_youtube_monitor.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    String id;
    String slug;
    String name;
    String released;
    String background_image;
    boolean tba;
    double rating;
    int rating_top;
    int ratings_count;
    private List<Map<String, Platform>> platforms;
    private List<String> platforms_label;
    private List<Genre> genres;
    private Clip clip;

    private String displayGenres;


    public Game() {
        platforms = new ArrayList<>();
        platforms_label = new ArrayList<>();
        genres = new ArrayList<>();
    }

    public void copyGame(SingleGame singleGame) {
        platforms = new ArrayList<>();
        platforms_label = new ArrayList<>();
        id = singleGame.getId();
        slug = singleGame.getSlug();
        name = singleGame.getName();
        released = singleGame.getReleased();
        background_image = singleGame.getBackground_image();
        tba = singleGame.isTba();
        rating = singleGame.getRating();
        rating_top = singleGame.getRating_top();
        ratings_count = singleGame.getRatings_count();
        if (singleGame.getClip() != null && singleGame.getClip().getClip() != null && !singleGame.getClip().getClip().equals(""))
            clip = new Clip(singleGame.getClip().getClip());
        genres.addAll(singleGame.getGenres());
        platforms.addAll(singleGame.getPlatforms());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public boolean isTba() {
        return tba;
    }

    public void setTba(boolean tba) {
        this.tba = tba;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRating_top() {
        return rating_top;
    }

    public void setRating_top(int rating_top) {
        this.rating_top = rating_top;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<Map<String, Platform>> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Map<String, Platform>> platforms) {
        this.platforms = platforms;
    }

    public List<String> getPlatforms_label() {
        return platforms_label;
    }

    public void setPlatforms_label(List<String> platforms_label) {
        this.platforms_label = platforms_label;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getDisplayGenres() {
        return displayGenres;
    }

    public void setDisplayGenres(String displayGenres) {
        this.displayGenres = displayGenres;
    }
}
