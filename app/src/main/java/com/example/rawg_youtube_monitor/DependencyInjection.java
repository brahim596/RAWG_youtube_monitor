package com.example.rawg_youtube_monitor;

import android.content.Context;

import androidx.room.Room;

import com.example.rawg_youtube_monitor.data.db.GameDatabase;
import com.example.rawg_youtube_monitor.data.repository.games.GamesDataRepository;
import com.example.rawg_youtube_monitor.data.repository.games.GamesRepository;
import com.example.rawg_youtube_monitor.data.repository.games.remote.GamesRemoteDataSource;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoDataRepository;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.YoutubeVideoRepository;
import com.example.rawg_youtube_monitor.data.repository.youtubeVideo.remote.YoutubeVideoRemoteDataSource;
import com.example.rawg_youtube_monitor.data.service.SearchGamesDisplayService;
import com.example.rawg_youtube_monitor.data.service.YoutubeVideoDisplayService;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DependencyInjection {

    private static Context context;
    private static String url_api = "https://api.rawg.io/api";

    private static Retrofit retrofit;
    private static Gson gson;

    private static GamesRepository gamesRepository;
    private static SearchGamesDisplayService searchGamesDisplayService;

    private static YoutubeVideoRepository youtubeVideoRepository;
    private static YoutubeVideoDisplayService youtubeVideoDisplayServicee;

    private static GameDatabase gameDatabase;


    public static GamesRepository getGamesRepository() {
        if (gamesRepository == null)
            gamesRepository = new GamesDataRepository(new GamesRemoteDataSource(getSearchGamesDisplayService()));
        return gamesRepository;
    }

    public static SearchGamesDisplayService getSearchGamesDisplayService() {
        if(searchGamesDisplayService==null)
            searchGamesDisplayService = getRetrofit().create(SearchGamesDisplayService.class);
        return searchGamesDisplayService;
    }

    public static YoutubeVideoRepository getYoutubeVideoRepository() {
        if(youtubeVideoRepository==null)
            youtubeVideoRepository = new YoutubeVideoDataRepository(new YoutubeVideoRemoteDataSource(getYoutubeVideoDisplayServicee()));
        return youtubeVideoRepository;
    }

    public static YoutubeVideoDisplayService getYoutubeVideoDisplayServicee() {
        if(youtubeVideoDisplayServicee==null)
            youtubeVideoDisplayServicee = getRetrofit().create(YoutubeVideoDisplayService.class);
        return youtubeVideoDisplayServicee;
    }

    public static GameDatabase getGameDatabase() {
        if(gameDatabase==null)
            gameDatabase = Room.databaseBuilder(context,GameDatabase.class,"game-database").build();
        return gameDatabase;
    }

    public String getUrl_api() {
        return url_api;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //  .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.rawg.io/api")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
