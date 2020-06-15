package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.mapper;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.Platform;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GamesMapper {

    public List<GameItemViewModel> mapGamesToGamesItemsViewModel(List<Game> games) {
        List<GameItemViewModel> gameItemViewModels = new ArrayList<>();

        for (Game game : games)
            gameItemViewModels.add(mapGameToGameItemViewModel(game));

        return gameItemViewModels;
    }

    public GameItemViewModel mapGameToGameItemViewModel(Game game) {
        GameItemViewModel gameItemViewModel = new GameItemViewModel(game.getId(), game.getName(), "" + game.getRating(), game.getBackground_image(), extractPlatformsNameFromGame(game), game.getRatings_count());

        if ((gameItemViewModel.getPlatforms() == null || gameItemViewModel.getPlatforms().isEmpty()) && game.getPlatforms_label() != null)
            gameItemViewModel.getPlatforms().addAll(game.getPlatforms_label());

        return gameItemViewModel;
    }

    public List<String> extractPlatformsNameFromGame(Game game) {
        List<String> platforms = new ArrayList<>();

        if (game.getPlatforms() != null)
            for (Map<String, Platform> map : game.getPlatforms())
                if (map.containsKey("platform")) platforms.add(map.get("platform").getSlug());

        return platforms;
    }

}
