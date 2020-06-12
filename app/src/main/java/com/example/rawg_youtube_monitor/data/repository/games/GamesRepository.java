package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;

import io.reactivex.Single;

public interface GamesRepository {

    Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page);
}
