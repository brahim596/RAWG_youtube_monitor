package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.SearchGamesViewContract;

import java.util.ArrayList;
import java.util.List;

public class SearchGameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    List<GameItemViewModel> gameItemViewModels;
    SearchGamesViewContract searchGamesViewContract;


    public SearchGameAdapter(SearchGamesViewContract searchGamesViewContract) {
        gameItemViewModels = new ArrayList<>();
        this.searchGamesViewContract = searchGamesViewContract;
    }

    @NonNull
    @Override
    public SearchGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchGameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false),searchGamesViewContract);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bindViewModel(this.gameItemViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return gameItemViewModels.size();
    }

    public void bindViewModels(List<GameItemViewModel> gameItemViewModels){
        this.gameItemViewModels.clear();
        this.gameItemViewModels.addAll(gameItemViewModels);
        this.notifyDataSetChanged();
    }
}
