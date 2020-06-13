package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.model.SingleGame;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Path;

public interface GamesRepository {

    Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page);

    Single<SingleGame> getGameById(String id);

    Completable addGameToFavoritesById(String id);
}
