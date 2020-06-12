package com.example.rawg_youtube_monitor;

import android.app.Application;

public class RAWGApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjection.setContext(this);
    }
}
