package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import com.example.rawg_youtube_monitor.data.model.Clip;
import com.example.rawg_youtube_monitor.data.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GameItemViewModel {
    String id;
    String gameTitle;
    String gameRate;
    String gameImageUrl;
    int ratings_count;
    List<String> platforms;
    boolean moreDetailsOpen = false;
    private String genres;
    private Clip clip;
    private String releaseDate;

    public GameItemViewModel(String id, String gameTitle, String gameRate, String gameImageUrl,List<String> platforms,int ratings_count,Clip clip,String releaseDate) {
        this.id = id;
        this.gameTitle = gameTitle;
        this.gameRate = gameRate;
        this.gameImageUrl = gameImageUrl;
        this.platforms = platforms;
        this.ratings_count = ratings_count;
        this.clip = clip;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameRate() {
        return gameRate;
    }

    public void setGameRate(String gameRate) {
        this.gameRate = gameRate;
    }

    public String getGameImageUrl() {
        return gameImageUrl;
    }

    public void setGameImageUrl(String gameImageUrl) {
        this.gameImageUrl = gameImageUrl;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public boolean isMoreDetailsOpen() {
        return moreDetailsOpen;
    }

    public void setMoreDetailsOpen(boolean moreDetailsOpen) {
        this.moreDetailsOpen = moreDetailsOpen;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
