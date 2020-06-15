package com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment.FavGamesViewContract;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavGameAdapter extends RecyclerView.Adapter<FavGameViewHolder> {

    List<GameItemViewModel> gameItemViewModels;
    FavGamesViewContract favGamesViewContract;

    public FavGameAdapter(FavGamesViewContract favGamesViewContract) {
        this.gameItemViewModels = new ArrayList<>();
        this.favGamesViewContract = favGamesViewContract;
    }

    @NonNull
    @Override
    public FavGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavGameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false), favGamesViewContract);
    }

    @Override
    public void onBindViewHolder(@NonNull FavGameViewHolder holder, int position) {
        holder.bindViewModel(this.gameItemViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return gameItemViewModels.size();
    }

    public void bindViewModels(List<GameItemViewModel> gameItemViewModels) {
        if (gameItemViewModels.size() == 0) this.favGamesViewContract.noGamesInFavoriteMessage();
        else this.favGamesViewContract.disableNoGamesInFavoriteMessage();
        this.gameItemViewModels.clear();
        this.gameItemViewModels.addAll(gameItemViewModels);
        this.notifyDataSetChanged();
    }
}
