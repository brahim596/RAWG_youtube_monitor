package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay;

import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.mapper.YoutubeGamesVideoMapper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class YoutubeVideoGamesPresenter {

    private GamesRepository gamesRepository;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;
    private CompositeDisposable compositeDisposable;
    private YoutubeGamesVideoMapper youtubeGamesVideoMapper;


    public YoutubeVideoGamesPresenter(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
        youtubeGamesVideoMapper = new YoutubeGamesVideoMapper();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void setYoutubeVideoGamesContract(YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
    }

    public void getAllFavoriteGames() {
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
}
