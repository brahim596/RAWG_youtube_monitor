package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.local.GamesLocalDataSource;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

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



    /************* Methods for mapping  (game entity) <---> (game) **************/


    private List<GameEntity> mapListGameToListGameEntity(List<Game> games){
        List<GameEntity> gameEntities = new ArrayList<>();
        for(Game game:games)
            gameEntities.add(mapGameToGameEntity(game));

        return gameEntities;
    }

    private List<Game> mapListGameEntityToListGame(List<GameEntity> gameEntities){
        List<Game> games = new ArrayList<>();
        for(GameEntity gameEntity:gameEntities)
            games.add(mapGameEntityToGame(gameEntity));

        return games;
    }

    private GameEntity mapGameToGameEntity(Game game){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setBackground_image(game.getBackground_image());
        gameEntity.setName(game.getName());
        gameEntity.setRating(game.getRating());
        return gameEntity;
    }

    private Game mapGameEntityToGame(GameEntity gameEntity){
        Game game = new Game();
        game.setId(gameEntity.getId());
        game.setBackground_image(gameEntity.getBackground_image());
        game.setName(gameEntity.getName());
        game.setRating(gameEntity.getRating());
        return game;
    }

}
