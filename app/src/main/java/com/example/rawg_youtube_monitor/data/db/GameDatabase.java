package com.example.rawg_youtube_monitor.data.db;

import androidx.room.Database;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;

@Database(entities = {GameEntity.class}, version = 1)
public abstract class GameDatabase {
    public abstract GameDao gameDao();
}
