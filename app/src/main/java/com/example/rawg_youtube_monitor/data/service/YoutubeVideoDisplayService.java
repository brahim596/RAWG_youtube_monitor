package com.example.rawg_youtube_monitor.data.service;


import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface YoutubeVideoDisplayService {

    @GET("games/{id}/youtube")
    Single<YoutubeVideoGamesResponse> getVideoGamesById(@Path("id") String id);
}
