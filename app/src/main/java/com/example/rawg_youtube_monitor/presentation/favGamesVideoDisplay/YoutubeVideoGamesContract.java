package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideoItemViewModel;

import java.util.List;

public interface YoutubeVideoGamesContract {
    void displayAllVideo(List<YoutubeVideoItemViewModel> youtubeVideoItemViewModels);

    void addYoutubeVideo(YoutubeVideoItemViewModel youtubeVideoItemViewModel);
}
