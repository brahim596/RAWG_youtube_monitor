package com.example.rawg_youtube_monitor.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface GameDao {

    @Insert
    public Completable insertFavoriteGame(GameEntity gameEntity);

    @Query("DELETE FROM gameentity where id = :id")
    public Completable deleteGameFromFavoritesById(String id);

    @Query("Select * from gameentity")
    public Flowable<List<GameEntity>> getAllFavoritesGames();
}
