package com.example.rawg_youtube_monitor.data.repository.games.remote;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.service.SearchGamesDisplayService;

import io.reactivex.Single;

public class GamesRemoteDataSource {

    private SearchGamesDisplayService searchGamesDisplayService;

    public GamesRemoteDataSource(SearchGamesDisplayService searchGamesDisplayService) {
        this.searchGamesDisplayService = searchGamesDisplayService;
    }

    public Single<SearchGamesResponse> searchGameByName(String name, int pageSize, int page) {
        return this.searchGamesDisplayService.searchGamesByName(name, pageSize, page);
    }

    public Single<Game> getGameById(String id){
        return this.getGameById(id);
    }
}
