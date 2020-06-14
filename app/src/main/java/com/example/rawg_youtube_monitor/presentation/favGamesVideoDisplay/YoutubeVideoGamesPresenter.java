package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;

public class YoutubeVideoGamesPresenter {

    private GamesRepository gamesRepository;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;

    public YoutubeVideoGamesPresenter(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public void setYoutubeVideoGamesContract(YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
    }
}
