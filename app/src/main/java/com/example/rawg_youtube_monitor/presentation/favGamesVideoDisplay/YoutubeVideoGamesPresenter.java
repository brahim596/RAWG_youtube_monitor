package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideo;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoRepository;
import com.example.rawg_youtube_monitor.exceptions.YoutubeVideoNotFoundException;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideoItemViewModel;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.mapper.YoutubeGamesVideoMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class YoutubeVideoGamesPresenter {

    private GamesRepository gamesRepository;
    private YoutubeVideoRepository youtubeVideoRepository;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;
    private CompositeDisposable compositeDisposable;
    private YoutubeGamesVideoMapper youtubeGamesVideoMapper;

    private List<Game> favoriteGames;
    private int size_page = 10;
    private int page = 0;


    public YoutubeVideoGamesPresenter(GamesRepository gamesRepository, YoutubeVideoRepository youtubeVideoRepository) {
        this.gamesRepository = gamesRepository;
        youtubeGamesVideoMapper = new YoutubeGamesVideoMapper();
        this.compositeDisposable = new CompositeDisposable();
        this.youtubeVideoRepository = youtubeVideoRepository;
        this.favoriteGames = new ArrayList<>();
        getAllFavoriteGames();
    }

    public void setYoutubeVideoGamesContract(YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
    }

    public void getAllFavoriteGamesYoutubeVideo() {
        compositeDisposable.add(gamesRepository.getAllFavoriteGamesYoutubeVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<YoutubeVideoEntity>>() {
                    @Override
                    public void onNext(List<YoutubeVideoEntity> videos) {
                        youtubeVideoGamesContract.displayAllVideo(youtubeGamesVideoMapper.mapListYoutubeEntitiesToListYoutubeVideoItemViewModel(videos));
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

    /**
     * Get all favorite games from local DB
     */
    public void getAllFavoriteGames() {
        compositeDisposable.add(gamesRepository.getAllFavoriteGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Game>>() {
                    @Override
                    public void onNext(List<Game> games) {
                        favoriteGames.addAll(games);
                        getYoutubeVideoGamePage();
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

    /**
     * Load primary youtube video of favorite games list
     * Load just a sublist of favorite games for performance
     * calculate like (index from : page * pageSize , index to : (page+1) * pageSize
     */
    public void getYoutubeVideoGamePage() {
        if (!(page * size_page > favoriteGames.size()) && favoriteGames.size() != 0) {
            List<Game> loadYoutubeVideo = favoriteGames.subList(page * size_page, favoriteGames.size());
            for (int i = 0; i < size_page; i++) {
                if (i < loadYoutubeVideo.size()) {
                    compositeDisposable.add(youtubeVideoRepository.getVideoGamesById(loadYoutubeVideo.get(i).getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<YoutubeVideoGamesResponse>() {
                                               @Override
                                               public void onSuccess(YoutubeVideoGamesResponse youtubeVideoGamesResponse) {
                                                   try {
                                                       youtubeVideoGamesContract.addYoutubeVideo(youtubeGamesVideoMapper.mapYoutubeGameVideoResponseToYoutubeVideoItemViewModel(youtubeVideoGamesResponse));
                                                   }catch (YoutubeVideoNotFoundException ynfe){

                                                   }
                                               }

                                               @Override
                                               public void onError(Throwable e) {

                                               }
                                           }
                            ));
                }
            }
            page++;
        }
    }
}
