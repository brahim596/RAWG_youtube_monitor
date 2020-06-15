package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.Clip;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.Genre;
import com.example.rawg_youtube_monitor.data.model.Platform;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.model.SingleGame;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideo;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.data.repository.games.local.GamesLocalDataSource;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoDataRepository;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GamesDataRepository implements GamesRepository {

    private GamesRemoteDataSource gamesRemoteDataSource;
    private GamesLocalDataSource gamesLocalDataSource;
    private CompositeDisposable compositeDisposable;
    private YoutubeVideoRepository youtubeVideoRepository;

    public GamesDataRepository(GamesRemoteDataSource gamesRemoteDataSource, GamesLocalDataSource gamesLocalDataSource, YoutubeVideoRepository youtubeVideoRepository) {
        this.gamesRemoteDataSource = gamesRemoteDataSource;
        this.gamesLocalDataSource = gamesLocalDataSource;
        this.compositeDisposable = new CompositeDisposable();
        this.youtubeVideoRepository = youtubeVideoRepository;
    }

    @Override
    public Single<SearchGamesResponse> searchGamesByName(String name, int pageSize, int page) {
        return this.gamesRemoteDataSource.searchGameByName(name, pageSize, page);
    }

    @Override
    public Single<SingleGame> getGameById(String id) {
        return this.gamesRemoteDataSource.getGameById(id);
    }

    @Override
    public Flowable<List<Game>> getAllFavoriteGames() {
        return this.gamesLocalDataSource.getAllFavGames().map(new Function<List<GameEntity>, List<Game>>() {
            @Override
            public List<Game> apply(List<GameEntity> gameEntities) throws Exception {
                List<Game> games = new ArrayList<>();
                for (GameEntity ge : gameEntities)
                    games.add(mapGameEntityToGame(ge));
                return games;
            }
        });

    }


    public Completable addGameToFavoritesById(final String id) {
        return gamesRemoteDataSource.getGameById(id).flatMapCompletable(new Function<SingleGame, CompletableSource>() {
            @Override
            public CompletableSource apply(final SingleGame singleGame) throws Exception {

                /**
                 * old method which save youtube video to local DB
                 */
               /* return youtubeVideoRepository.getVideoGamesById(id).flatMapCompletable(new Function<YoutubeVideoGamesResponse, CompletableSource>() {
                    @Override
                    public CompletableSource apply(YoutubeVideoGamesResponse youtubeVideoGamesResponse) throws Exception {
                        Game newGame = new Game();
                        newGame.copyGame(singleGame);
                        return gamesLocalDataSource.insertGameInFavorites(mapGameToGameEntity(newGame, youtubeVideoGamesResponse));
                    }
                });*/

                Game newGame = new Game();
                newGame.copyGame(singleGame);
                return gamesLocalDataSource.insertGameInFavorites(mapGameToGameEntity(newGame, null));
            }
        });
    }

    public Completable removeGameFromFavoritesById(String id) {
        return this.gamesLocalDataSource.deleteGameById(id);
    }

    public Flowable<List<YoutubeVideoEntity>> getAllFavoriteGamesYoutubeVideo() {
        return this.gamesLocalDataSource.getAllFavGames().map(new Function<List<GameEntity>, List<YoutubeVideoEntity>>() {
            @Override
            public List<YoutubeVideoEntity> apply(List<GameEntity> gameEntities) throws Exception {
                List<YoutubeVideoEntity> youtubeVideoEntities = new ArrayList<>();
                for (GameEntity ge : gameEntities)
                    youtubeVideoEntities.add(ge.getYoutubeVideoEntity());
                return youtubeVideoEntities;
            }
        });

    }


    /************* Methods for mapping  (game entity) <---> (game) **************/


    private List<GameEntity> mapListGameToListGameEntity(List<Game> games) {
        List<GameEntity> gameEntities = new ArrayList<>();
        for (Game game : games)
            gameEntities.add(mapGameToGameEntity(game, null));

        return gameEntities;
    }

    private List<Game> mapListGameEntityToListGame(List<GameEntity> gameEntities) {
        List<Game> games = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities)
            games.add(mapGameEntityToGame(gameEntity));

        return games;
    }

    private GameEntity mapGameToGameEntity(Game game, YoutubeVideoGamesResponse youtubeVideoGamesResponse) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setBackground_image(game.getBackground_image());
        gameEntity.setName(game.getName());
        gameEntity.setRating(game.getRating());
        gameEntity.setRating_count(game.getRatings_count());

        gameEntity.setGenres(extractGenresFormListToString(game.getGenres()));
        if(game.getClip()!=null)
            gameEntity.setClip(game.getClip().getClip());
        gameEntity.setReleaseDate(game.getReleased());

        if (youtubeVideoGamesResponse != null)
            gameEntity.setYoutubeVideoEntity(mapYoutubeResponseToYoutubeVideoEntity(youtubeVideoGamesResponse));

        gameEntity.getPlatforms().addAll(extractPlatformsNameFromGame(game));
        return gameEntity;
    }

    private Game mapGameEntityToGame(GameEntity gameEntity) {
        Game game = new Game();
        game.setId(gameEntity.getId());
        game.setBackground_image(gameEntity.getBackground_image());
        game.setName(gameEntity.getName());
        game.setRating(gameEntity.getRating());
        game.setRatings_count(gameEntity.getRating_count());
        game.getPlatforms_label().addAll(gameEntity.getPlatforms());
        game.setDisplayGenres(gameEntity.getGenres());
        game.setClip(new Clip(gameEntity.getClip()));
        game.setReleased(gameEntity.getReleaseDate());
        return game;
    }

    private YoutubeVideoEntity mapYoutubeResponseToYoutubeVideoEntity(YoutubeVideoGamesResponse youtubeVideoGamesResponse) {
        YoutubeVideoEntity youtubeVideoEntity = new YoutubeVideoEntity();
        YoutubeVideo youtubeVideo = youtubeVideoGamesResponse.getResults().get(0);

        youtubeVideoEntity.setYoutube_id(youtubeVideo.getId() + "");
        youtubeVideoEntity.setChannel_title(youtubeVideo.getChannel_title());
        youtubeVideoEntity.setTitle(youtubeVideo.getName());
        youtubeVideoEntity.setCreated(youtubeVideo.getCreated());
        youtubeVideoEntity.setDescription(youtubeVideo.getDescription());
        youtubeVideoEntity.setDislike_count(youtubeVideo.getDislike_count());
        youtubeVideoEntity.setLike_count(youtubeVideo.getLike_count());
        youtubeVideoEntity.setView_count(youtubeVideo.getView_count());

        if (youtubeVideo.getThumbnails() != null && youtubeVideo.getThumbnails().get("medium") != null)
            youtubeVideoEntity.setThumbnail(youtubeVideo.getThumbnails().get("medium").getUrl());

        return youtubeVideoEntity;
    }


    private List<String> extractPlatformsNameFromGame(Game game) {
        List<String> platforms = new ArrayList<>();

        if (game.getPlatforms() != null)
            for (Map<String, Platform> map : game.getPlatforms())
                if (map.containsKey("platform")) platforms.add(map.get("platform").getSlug());

        return platforms;
    }

    private String extractGenresFormListToString(List<Genre> genres){
        String s = "";
        for (Genre genre: genres)
            s+=genre.getSlug()+", ";
        return s;
    }

}
