package com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment.FavGamesViewContract;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameViewHolder;

public class FavGameViewHolder extends GameViewHolder {

    FavGamesViewContract favGamesViewContract;
    ImageView deletButton;

    public FavGameViewHolder(View view, FavGamesViewContract favGamesViewContract) {
        super(view);
        this.favGamesViewContract = favGamesViewContract;
        deletButton = view.findViewById(R.id.addButton);
        deletButton.setImageResource(R.drawable.ic_baseline_delete_24);
        addAddButtonListener();
    }

    private void addAddButtonListener(){
        deletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favGamesViewContract.deleteGameById(getGameItemViewModel().getId());
            }
        });
    }
}
