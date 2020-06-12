package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;

import io.reactivex.Single;

public class GamesDataRepository implements GamesRepository {

    private GamesRemoteDataSource gamesRemoteDataSource;

    public GamesDataRepository(GamesRemoteDataSource gamesRemoteDataSource) {
        this.gamesRemoteDataSource = gamesRemoteDataSource;
    }

    @Override
    public Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page) {
        return this.gamesRemoteDataSource.searchGameByName(name,pageSize,page);
    }
}
