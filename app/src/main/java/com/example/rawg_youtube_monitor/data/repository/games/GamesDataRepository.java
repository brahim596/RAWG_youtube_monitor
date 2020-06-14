package com.example.rawg_youtube_monitor.data.repository.games;

import com.example.rawg_youtube_monitor.data.db.entity.GameEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.data.model.SearchGamesResponse;
import com.example.rawg_youtube_monitor.data.model.SingleGame;
import com.example.rawg_youtube_monitor.data.repository.games.local.GamesLocalDataSource;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

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

    public GamesDataRepository(GamesRemoteDataSource gamesRemoteDataSource, GamesLocalDataSource gamesLocalDataSource) {
        this.gamesRemoteDataSource = gamesRemoteDataSource;
        this.gamesLocalDataSource = gamesLocalDataSource;
        this.compositeDisposable = new CompositeDisposable();
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
                for(GameEntity ge:gameEntities)
                    games.add(mapGameEntityToGame(ge));

                return games;
            }
        });

    }

    public Completable addGameToFavoritesById(String id){
        return gamesRemoteDataSource.getGameById(id).flatMapCompletable(new Function<SingleGame, CompletableSource>() {
            @Override
            public CompletableSource apply(SingleGame singleGame) throws Exception {
                Game newGame = new Game();
                newGame.copyGame(singleGame);
               return gamesLocalDataSource.insertGameInFavorites(mapGameToGameEntity(newGame));
            }
        });
    }

    public Completable removeGameFromFavoritesById(String id){
        return this.gamesLocalDataSource.deleteGameById(id);
    }



    /************* Methods for mapping  (game entity) <---> (game) **************/


    private List<GameEntity> mapListGameToListGameEntity(List<Game> games){
        List<GameEntity> gameEntities = new ArrayList<>();
        for(Game game:games)
            gameEntities.add(mapGameToGameEntity(game));

        return gameEntities;
    }

    private List<Game> mapListGameEntityToListGame(List<GameEntity> gameEntities){
        List<Game> games = new ArrayList<>();
        for(GameEntity gameEntity:gameEntities)
            games.add(mapGameEntityToGame(gameEntity));

        return games;
    }

    private GameEntity mapGameToGameEntity(Game game){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.getId());
        gameEntity.setBackground_image(game.getBackground_image());
        gameEntity.setName(game.getName());
        gameEntity.setRating(game.getRating());
        gameEntity.setRating_count(game.getRatings_count());
        return gameEntity;
    }

    private Game mapGameEntityToGame(GameEntity gameEntity){
        Game game = new Game();
        game.setId(gameEntity.getId());
        game.setBackground_image(gameEntity.getBackground_image());
        game.setName(gameEntity.getName());
        game.setRating(gameEntity.getRating());
        game.setRatings_count(gameEntity.getRating_count());
        return game;
    }

}
