package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay;

import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.Platform;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchGamesPresenter {

    GamesRepository gamesRepository;
    CompositeDisposable compositeDisposable;
    List<GameItemViewModel> gameItemViewModelList;
    SearchGamesViewContract searchGamesViewContract;

    public void setSearchGamesViewContract(SearchGamesViewContract searchGamesViewContract) {
        this.searchGamesViewContract = searchGamesViewContract;
    }

    public SearchGamesPresenter(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.gameItemViewModelList = new ArrayList<>();
    }

    public void getGamesByName(String searchField){
        this.compositeDisposable.add(
        this.gamesRepository.searchGamesByName(searchField,20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SearchGamesResponse>(){

                    @Override
                    public void onSuccess(SearchGamesResponse searchGamesResponse) {
                        gameItemViewModelList.clear();
                        gameItemViewModelList.addAll(mapGamesToGamesItemsViewModel(searchGamesResponse.getResults()));
                        searchGamesViewContract.displayGames(gameItemViewModelList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("TEST");
                    }
                }
        ));
    }


    private List<GameItemViewModel> mapGamesToGamesItemsViewModel(List<Game> games){
        List<GameItemViewModel> gameItemViewModels = new ArrayList<>();

        for (Game game : games)
            gameItemViewModels.add(mapGameToGameItemViewModel(game));

        return gameItemViewModels;
    }

    private GameItemViewModel mapGameToGameItemViewModel(Game game){
        return new GameItemViewModel(game.getId(),game.getName(),""+game.getRating(),game.getBackground_image(),extractPlatformsNameFromGame(game),game.getRatings_count());
    }

    private List<String> extractPlatformsNameFromGame(Game game){
        List<String> platforms = new ArrayList<>();

        if(game.getPlatforms()!=null)
        for(Map<String, Platform> map : game.getPlatforms())
            if(map.containsKey("platform")) platforms.add(map.get("platform").getSlug());

            return platforms;
    }



}
