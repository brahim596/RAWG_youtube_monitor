package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;

import io.reactivex.Single;
import retrofit2.http.Path;

public interface GamesRepository {

    Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page);

    Single<Game> getGameById(String id);
}
