package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.model.SingleGame;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.Path;

public interface GamesRepository {

    Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page);

    Single<SingleGame> getGameById(String id);

    Flowable<List<Game>> getAllFavoriteGames();

    Completable addGameToFavoritesById(String id);

    Completable removeGameFromFavoritesById(String id);
}
