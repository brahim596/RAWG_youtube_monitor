package com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.DependencyInjection;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.FavGamesPresenter;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.adapter.FavGameAdapter;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.List;

public class FavGamesCollectionFragment extends Fragment implements FavGamesViewContract{

    private View view;
    private RecyclerView favGamesResultRecyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;

    private FavGameAdapter favGameAdapter;
    private FavGamesPresenter favGamesPresenter;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favGamesPresenter = new FavGamesPresenter(DependencyInjection.getGamesRepository());
        favGamesPresenter.setFavGamesViewContract(this);
        setUpRecyclerView();
        favGamesPresenter.getAllFavoriteGames();
    }

    @Override
    public void displayGames(List<GameItemViewModel> gameItemViewModelList) {
        this.favGameAdapter.bindViewModels(gameItemViewModelList);
    }

    private void setUpRecyclerView(){
        this.favGameAdapter = new FavGameAdapter(this);
        this.favGamesResultRecyclerView = view.findViewById(R.id.favGamesRecyclerView);
        this.favGamesResultRecyclerView.setAdapter(favGameAdapter);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        this.favGamesResultRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
