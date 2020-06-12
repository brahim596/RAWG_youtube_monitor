package com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rawg_youtube_monitor.R;

public class FavGamesCollectionFragment extends Fragment {

    private View view;

    public FavGamesCollectionFragment() {}

    public static FavGamesCollectionFragment newInstance(){
        return new FavGamesCollectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_fav_games_collection,container,false);
        return view;
    }
}
