package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;

public class GameViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView gameTitle;
    TextView gameRate;
    ImageView gameImage;
    GameItemViewModel gameItemViewModel;

    public GameViewHolder(View view) {
        super(view);
        this.view = view;
        gameTitle = view.findViewById(R.id.gameTitle);
        gameRate = view.findViewById(R.id.gameRate);
        gameImage = view.findViewById(R.id.gameImage);
    }

    public void bindViewModel(GameItemViewModel gameItemViewModel){
        this.gameItemViewModel = gameItemViewModel;
        gameTitle.setText(gameItemViewModel.getGameTitle());
        gameRate.setText(gameItemViewModel.getGameRate());
        //Set up image with Glide
    }
}
