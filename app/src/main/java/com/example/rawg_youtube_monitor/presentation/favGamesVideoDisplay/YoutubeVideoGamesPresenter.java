package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoRepository;
import com.example.rawg_youtube_monitor.exceptions.YoutubeVideoNotFoundException;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.mapper.YoutubeGamesVideoMapper;

import java.util.ArrayList;
import java.util.List;

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
    private boolean youtubeVideoGameAvaible = false;


    public YoutubeVideoGamesPresenter(GamesRepository gamesRepository, YoutubeVideoRepository youtubeVideoRepository, YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.gamesRepository = gamesRepository;
        youtubeGamesVideoMapper = new YoutubeGamesVideoMapper();
        this.compositeDisposable = new CompositeDisposable();
        this.youtubeVideoRepository = youtubeVideoRepository;
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
        this.favoriteGames = new ArrayList<>();
        getAllFavoriteGames();
    }

    public void setYoutubeVideoGamesContract(YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
    }

    /**
     * old method which save youtube video in local DB when the game is added to it
     */
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
     * Get all favorite games from local DB and store them in a list
     * get youtube from the remote source for a page of game
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
                        if(favoriteGames.size()==0)
                            youtubeVideoGamesContract.noYoutubeVideosInFavoriteMessage();
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
     * So display one youtube video by favorite game, when the user touch 'see more'
     * more videos of the game will be load
     * handle pagination
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
                                                       if (youtubeVideoGamesResponse.getResults().size() > 0)
                                                           youtubeVideoGameAvaible = true;
                                                       youtubeVideoGamesContract.addYoutubeVideo(youtubeGamesVideoMapper.mapYoutubeGameVideoResponseToYoutubeVideoItemViewModel(youtubeVideoGamesResponse));
                                                   } catch (YoutubeVideoNotFoundException ynfe) {

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
