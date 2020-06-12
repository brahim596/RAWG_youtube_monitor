package com.example.rawg_youtube_monitor.data.repository.youtubeVideo;

import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;

import io.reactivex.Single;

public interface YoutubeVideoRepository {
    Single<YoutubeVideoGamesResponse> getVideoGamesById (String id);
}
