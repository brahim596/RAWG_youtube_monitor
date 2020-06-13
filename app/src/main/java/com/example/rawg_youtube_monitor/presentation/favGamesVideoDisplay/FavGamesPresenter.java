package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.FavGamesViewContract;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.mapper.GamesMapper;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavGamesPresenter {

    GamesRepository gamesRepository;
    CompositeDisposable compositeDisposable;
    List<GameItemViewModel> gameItemViewModelList;
    FavGamesViewContract favGamesViewContract;
    GamesMapper gamesMapper;

    public FavGamesPresenter(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.gamesMapper = new GamesMapper();
    }

    public void setFavGamesViewContract(FavGamesViewContract favGamesViewContract) {
        this.favGamesViewContract = favGamesViewContract;
    }

    public void getAllFavoriteGames() {
        compositeDisposable.add(gamesRepository.getAllFavoriteGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Game>>() {
                    @Override
                    public void onNext(List<Game> games) {
                        favGamesViewContract.displayGames(gamesMapper.mapGamesToGamesItemsViewModel(games));
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }
}
