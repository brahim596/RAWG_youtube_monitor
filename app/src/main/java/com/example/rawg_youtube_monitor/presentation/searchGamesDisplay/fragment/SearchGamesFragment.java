package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.DependencyInjection;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.SearchGamesPresenter;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.SearchGameAdapter;

public class SearchGamesFragment extends Fragment {

    private View view;
    private RecyclerView searchGamesResultRecyclerView;
    private EditText searchGamesEditText;

    private SearchGameAdapter searchGameAdapter;
    private SearchGamesPresenter searchGamesPresenter;

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
        setUpSearchFieldListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchGamesPresenter = new SearchGamesPresenter(DependencyInjection.getGamesRepository());
    }

    public void setUpSearchFieldListener(){
        this.searchGamesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchGamesPresenter.getGamesByName(s.toString());
            }
        });
    }
}
