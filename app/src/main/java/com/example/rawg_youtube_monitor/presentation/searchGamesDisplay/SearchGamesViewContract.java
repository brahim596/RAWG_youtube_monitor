package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay;

import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.List;

public interface SearchGamesViewContract {
    void displayGames(List<GameItemViewModel> gameItemViewModelList,boolean newSearch);

    void stopLoadingSpiner();

    void addGameToFavorite(String id);

    void notifyGameAddedToFavorite(String message);

    void notifyErrorServeur(String message);
}
