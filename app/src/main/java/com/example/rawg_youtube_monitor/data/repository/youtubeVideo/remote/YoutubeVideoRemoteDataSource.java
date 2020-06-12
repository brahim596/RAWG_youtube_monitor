package com.example.rawg_youtube_monitor.data.repository.youtubeVideo.remote;

import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.service.YoutubeVideoDisplayService;

import io.reactivex.Single;

public class YoutubeVideoRemoteDataSource {

    private YoutubeVideoDisplayService youtubeVideoDisplayService;

    public YoutubeVideoRemoteDataSource(YoutubeVideoDisplayService youtubeVideoDisplayService) {
        this.youtubeVideoDisplayService = youtubeVideoDisplayService;
    }

    public Single<YoutubeVideoGamesResponse> getVideoGamesById(String id){
        return this.youtubeVideoDisplayService.getVideoGamesById(id);
    }
}
