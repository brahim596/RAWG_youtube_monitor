package com.example.rawg_youtube_monitor.data.service;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchGamesDisplayService {

    @GET("games")
    Single<SearchGamesResponse> searchGamesByName(@Query("search") String name, @Query("page_size") int pageSize, @Query("page") int page);

    @GET("games/{id}")
    Single<Game> getGameById(@Path("id") String id);


}
