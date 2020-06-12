package com.example.rawg_youtube_monitor.data.repository.youtubeVideo.remote;

import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.service.GamesVideoDisplayService;

import io.reactivex.Single;

public class YoutubeVideoRemoteDataSource {

    private GamesVideoDisplayService gamesVideoDisplayService;

    public YoutubeVideoRemoteDataSource(GamesVideoDisplayService gamesVideoDisplayService) {
        this.gamesVideoDisplayService = gamesVideoDisplayService;
    }

    public Single<YoutubeVideoGamesResponse> getVideoGamesById(String id){
        return this.gamesVideoDisplayService.getVideoGamesById(id);
    }
}
