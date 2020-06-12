package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;

import java.util.ArrayList;
import java.util.List;

public class SearchGameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    List<GameItemViewModel> gameItemViewModels;


    public SearchGameAdapter() {
        gameItemViewModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false));
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
