package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.local.GamesLocalDataSource;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;

import io.reactivex.Single;

public class GamesDataRepository implements GamesRepository {

    private GamesRemoteDataSource gamesRemoteDataSource;
    private GamesLocalDataSource gamesLocalDataSource;

    public GamesDataRepository(GamesRemoteDataSource gamesRemoteDataSource, GamesLocalDataSource gamesLocalDataSource) {
        this.gamesRemoteDataSource = gamesRemoteDataSource;
    }

    @Override
    public Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page) {
        return this.gamesRemoteDataSource.searchGameByName(name, pageSize, page);
    }
}
