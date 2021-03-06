package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay;

import android.widget.Toast;

import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.mapper.GamesMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchGamesPresenter {

    GamesRepository gamesRepository;
    CompositeDisposable compositeDisposable;
    List<GameItemViewModel> gameItemViewModelList;
    SearchGamesViewContract searchGamesViewContract;
    GamesMapper gamesMapper;

    //the current page of search result
    int page;
    // total items avaible in the API
    int totalCountItem;
    // user serching field
    String searchField;

    public void setSearchGamesViewContract(SearchGamesViewContract searchGamesViewContract) {
        this.searchGamesViewContract = searchGamesViewContract;
        this.page = 1;
    }

    public SearchGamesPresenter(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
        this.gamesMapper = new GamesMapper();
        this.compositeDisposable = new CompositeDisposable();
        this.gameItemViewModelList = new ArrayList<>();
    }

    /**
     * Get the first page of search result
     * @param searchField
     */
    public void getGamesByName(String searchField) {

        if (searchField != this.searchField) {
            this.searchField = searchField;
            this.page = 1;
        }
        this.compositeDisposable.add(
                this.gamesRepository.searchGamesByName(searchField, 20, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<SearchGamesResponse>() {

                                           @Override
                                           public void onSuccess(SearchGamesResponse searchGamesResponse) {
                                               totalCountItem = searchGamesResponse.getCount();
                                               gameItemViewModelList.clear();
                                               gameItemViewModelList.addAll(gamesMapper.mapGamesToGamesItemsViewModel(searchGamesResponse.getResults()));
                                               searchGamesViewContract.displayGames(gameItemViewModelList,true);
                                           }

                                           @Override
                                           public void onError(Throwable e) {
                                               searchGamesViewContract.stopLoadingSpiner();
                                           }
                                       }
                        ));
    }

    /**
     * When the user scroll at the end of recyclerview
     * this method is called to load the next page of results
     */
    public void loadNextPage() {
        //check if we already have all results
        if (page * 20 < totalCountItem) {
            this.page++;
            this.compositeDisposable.add(
                    this.gamesRepository.searchGamesByName(searchField, 20, page)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<SearchGamesResponse>() {

                                               @Override
                                               public void onSuccess(SearchGamesResponse searchGamesResponse) {
                                                   gameItemViewModelList.addAll(gamesMapper.mapGamesToGamesItemsViewModel(searchGamesResponse.getResults()));
                                                   searchGamesViewContract.displayGames(gameItemViewModelList,false);
                                               }

                                               @Override
                                               public void onError(Throwable e) {
                                                   searchGamesViewContract.stopLoadingSpiner();
                                               }
                                           }
                            ));
        } else {
            searchGamesViewContract.stopLoadingSpiner();
        }
    }

    /**
     * Add game by id in the favorite games user list
     * in local DB
     * @param id
     */
    public void addGameToFavorite(String id) {
       compositeDisposable.add( gamesRepository.addGameToFavoritesById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        searchGamesViewContract.notifyGameAddedToFavorite("Jeu ajouté à vos favoris !");
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchGamesViewContract.notifyErrorServeur("Vous avez déjà ajouté ce jeu");
                    }
                }));
    }



}
