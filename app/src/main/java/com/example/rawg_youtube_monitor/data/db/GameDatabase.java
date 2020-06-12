package com.example.rawg_youtube_monitor.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;

@Database(entities = {GameEntity.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase {
    public abstract GameDao gameDao();
}
