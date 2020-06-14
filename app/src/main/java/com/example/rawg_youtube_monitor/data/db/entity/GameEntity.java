package com.example.rawg_youtube_monitor.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameEntity {

    @NonNull
    @PrimaryKey
    String id;
    String background_image;
    String name;
    double rating;
    int rating_count;

    @Embedded
    YoutubeVideoEntity youtubeVideoEntity;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public YoutubeVideoEntity getYoutubeVideoEntity() {
        return youtubeVideoEntity;
    }

    public void setYoutubeVideoEntity(YoutubeVideoEntity youtubeVideoEntity) {
        this.youtubeVideoEntity = youtubeVideoEntity;
    }
}
