package com.example.rawg_youtube_monitor.data.model;

import java.util.Map;

public class YoutubeVideo {
    int id;
    String external_id;
    String channel_id;
    String channel_title;
    String name;
    String description;
    String created;
    int view_count;
    int comments_count;
    int like_count;
    int dislike_count;
    int favorite_count;
    Map<String,Thumbnail> thumbnails;
}
