package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import androidx.annotation.NonNull;

public class YoutubeVideoItemViewModel {

    String youtube_id;
    String channel_title;
    String title;
    String description;
    String created;
    int view_count;
    int like_count;
    int dislike_count;
    String thumbnail;


    public YoutubeVideoItemViewModel(String youtube_id, String channel_title, String title, String description, String created, int view_count, int like_count, int dislike_count, String thumbnail) {
        this.youtube_id = youtube_id;
        this.channel_title = channel_title;
        this.title = title;
        this.description = description;
        this.created = created;
        this.view_count = view_count;
        this.like_count = like_count;
        this.dislike_count = dislike_count;
        this.thumbnail = thumbnail;
    }

    @NonNull
    public String getYoutube_id() {
        return youtube_id;
    }

    public void setYoutube_id(@NonNull String youtube_id) {
        this.youtube_id = youtube_id;
    }

    public String getChannel_title() {
        return channel_title;
    }

    public void setChannel_title(String channel_title) {
        this.channel_title = channel_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getDislike_count() {
        return dislike_count;
    }

    public void setDislike_count(int dislike_count) {
        this.dislike_count = dislike_count;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
