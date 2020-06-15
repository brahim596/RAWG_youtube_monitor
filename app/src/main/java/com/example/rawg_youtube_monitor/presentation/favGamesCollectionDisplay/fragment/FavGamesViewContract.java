package com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment;

import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.List;

public interface FavGamesViewContract {
    void displayGames(List<GameItemViewModel> gameItemViewModelList);

    void deleteGameById(String id);

    void notifyDeleteSuccess(String message);

    void notifyDeleteError(String message);

    void noGamesInFavoriteMessage();

    void disableNoGamesInFavoriteMessage();
}
