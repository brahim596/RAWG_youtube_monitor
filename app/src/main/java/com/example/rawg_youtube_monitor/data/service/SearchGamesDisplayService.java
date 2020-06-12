package com.example.rawg_youtube_monitor.data.service;

import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchGamesDisplayService {

    @GET("games?search={name}&page_size={pageSize}&page={page}")
    Single<SearchGamesResponse> searchGamesByName(@Path("name") String name, @Path("pageSize") int pageSize, @Path("page") int page);
}
