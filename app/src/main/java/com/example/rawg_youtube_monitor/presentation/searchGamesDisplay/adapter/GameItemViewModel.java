package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import java.util.List;

public class GameItemViewModel {
    String id;
    String gameTitle;
    String gameRate;
    String gameImageUrl;
    List<String> platforms;

    public GameItemViewModel(String id, String gameTitle, String gameRate, String gameImageUrl,List<String> platforms) {
        this.id = id;
        this.gameTitle = gameTitle;
        this.gameRate = gameRate;
        this.gameImageUrl = gameImageUrl;
        this.platforms = platforms;
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
}
