package com.example.rawg_youtube_monitor.data.repository.games.local;

import com.example.rawg_youtube_monitor.data.db.GameDatabase;
import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class GamesLocalDataSource {

    private GameDatabase gameDatabase;

    public GamesLocalDataSource(GameDatabase gameDatabase) {
        this.gameDatabase = gameDatabase;
    }

    public Flowable<List<GameEntity>> getAllFavGames(){
        return this.gameDatabase.gameDao().getAllFavoritesGames();
    }

    public Completable deleteGameById(String id){
        return this.gameDatabase.gameDao().deleteGameFromFavoritesById(id);
    }

    public Completable insertGameInFavorites(GameEntity gameEntity){
        return this.gameDatabase.gameDao().insertFavoriteGame(gameEntity);
    }

}
