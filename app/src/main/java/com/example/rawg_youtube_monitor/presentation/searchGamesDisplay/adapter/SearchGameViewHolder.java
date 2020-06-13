package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.SearchGamesViewContract;

public class SearchGameViewHolder extends GameViewHolder {

    SearchGamesViewContract searchGamesViewContract;
    ImageView addButton;

    public SearchGameViewHolder(View view, SearchGamesViewContract searchGamesViewContract) {
        super(view);
        this.searchGamesViewContract = searchGamesViewContract;
        addButton = view.findViewById(R.id.addButton);
        addAddButtonListener();
    }

    private void addAddButtonListener(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGamesViewContract.addGameToFavorite(gameItemViewModel.getId());
            }
        });
    }
}
