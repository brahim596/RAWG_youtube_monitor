package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment;

import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.List;

public interface FavGamesViewContract {
    void displayGames(List<GameItemViewModel> gameItemViewModelList);
}
