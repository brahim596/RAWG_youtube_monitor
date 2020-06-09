package com.example.rawg_youtube_monitor.presentation;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.rawg_youtube_monitor.MainActivity;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.FavGamesVideoFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    Drawable image;

    public MainPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FavGamesVideoFragment.newInstance();
    }


    @Override
    public int getCount() {
        return 3;
    }
}
