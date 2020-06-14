package com.example.rawg_youtube_monitor.presentation;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.YoutubeVideoFragment;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment.FavGamesCollectionFragment;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment.SearchGamesFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    Drawable image;

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               return YoutubeVideoFragment.newInstance();
            case 1:
               return FavGamesCollectionFragment.newInstance();
            case 2:
               return SearchGamesFragment.newInstance();
            default: return YoutubeVideoFragment.newInstance();
        }

    }


    @Override
    public int getCount() {
        return 3;
    }
}
