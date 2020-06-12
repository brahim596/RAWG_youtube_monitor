package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.FavGamesVideoFragment;

public class SearchGamesFragment extends Fragment {

    private View view;
    private RecyclerView searchGamesResultRecyclerView;
    private EditText searchGamesEditText;

    public SearchGamesFragment() {}

    public static SearchGamesFragment newInstance(){
        return new SearchGamesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_search_games,container,false);
        this.searchGamesResultRecyclerView = view.findViewById(R.id.searchGamesResultRecyclerView);
        this.searchGamesEditText = view.findViewById(R.id.searchGamesEditText);
        return view;
    }

}
