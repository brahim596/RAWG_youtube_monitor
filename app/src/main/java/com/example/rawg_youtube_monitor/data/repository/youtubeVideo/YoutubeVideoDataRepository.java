package com.example.rawg_youtube_monitor.data.repository.youtubeVideo;

import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.remote.YoutubeVideoRemoteDataSource;

import io.reactivex.Single;

public class YoutubeVideoDataRepository implements YoutubeVideoRepository {

    private YoutubeVideoRemoteDataSource youtubeVideoRemoteDataSource;

    public YoutubeVideoDataRepository(YoutubeVideoRemoteDataSource youtubeVideoRemoteDataSource) {
        this.youtubeVideoRemoteDataSource = youtubeVideoRemoteDataSource;
    }

    @Override
    public Single<YoutubeVideoGamesResponse> getVideoGamesById(String id) {
        return this.youtubeVideoRemoteDataSource.getVideoGamesById(id);
    }
}
