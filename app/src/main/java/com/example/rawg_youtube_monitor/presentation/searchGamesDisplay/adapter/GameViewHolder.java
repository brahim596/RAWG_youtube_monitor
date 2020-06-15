package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter;

import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_youtube_monitor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameViewHolder extends RecyclerView.ViewHolder {

    LinearLayout mainLayoutGameCard;
    LinearLayout detailsLayout;
    View view;
    TextView gameTitle;
    TextView gameRate;
    TextView gameRateScore;
    TextView ratingCounts;
    TextView moreDetails;
    ImageView gameImage;

    GameItemViewModel gameItemViewModel;
    LinearLayout iconPlatformLayout;

    Map<String, Integer> iconsAvailble;
    List<Integer> iconsIdAdded;

     float scale;


    public GameViewHolder(View view) {
        super(view);
        this.view = view;
        gameTitle = view.findViewById(R.id.gameTitle);
        gameRate = view.findViewById(R.id.gameRate);
        gameRateScore = view.findViewById(R.id.gameRateScore);
        gameImage = view.findViewById(R.id.gameImage);
        ratingCounts = view.findViewById(R.id.ratingCount);
        iconPlatformLayout = view.findViewById(R.id.platforms_icon_layout);
        moreDetails = view.findViewById(R.id.moreDetails);
        mainLayoutGameCard = view.findViewById(R.id.mainLayoutGameCard);
        detailsLayout = view.findViewById(R.id.detailsLayout);
        initIconsAvaible();
        setUpListeners();

        //For using dp unit programatically
        scale=view.getContext().getResources().getDisplayMetrics().density;
    }

    public void bindViewModel(GameItemViewModel gameItemViewModel) {
        this.gameItemViewModel = gameItemViewModel;
        gameTitle.setText(gameItemViewModel.getGameTitle());
        gameRate.setText(gameItemViewModel.getGameRate());
        if (gameItemViewModel.getRatings_count() == 0) {
            gameRateScore.setText("NC");
            ratingCounts.setText("( 0 avis )");
        } else {
            gameRateScore.setText((int) (Double.parseDouble(gameItemViewModel.getGameRate()) * 20) + "");
            ratingCounts.setText("( "+gameItemViewModel.getRatings_count()+" avis )");
        }
        setUpGameScoreColor();
        Glide.with(view).load(this.gameItemViewModel.gameImageUrl).fitCenter().transition(DrawableTransitionOptions.withCrossFade(100)).into(this.gameImage);
        bindPlatformIcons();
    }

    private void bindPlatformIcons() {
        for (String iconKey : iconsAvailble.keySet())
            for (String platform : gameItemViewModel.platforms) {
                if (platform.matches("(.*)" + iconKey + "(.*)") && (!iconsIdAdded.contains(iconsAvailble.get(iconKey)) )) {
                    addIcon(iconsAvailble.get(iconKey));
                    break;
                }
            }
    }

    private void addIcon(int id) {
        ImageView icon = new ImageView(view.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
        layoutParams.setMargins(10, 40, 10, 0);
        icon.setLayoutParams(layoutParams);
        Glide.with(view).load(id).fitCenter().transition(DrawableTransitionOptions.withCrossFade(100)).into(icon);
        iconPlatformLayout.addView(icon);
        iconsIdAdded.add(id);
    }

    private void initIconsAvaible() {
        iconsIdAdded = new ArrayList<>();
        iconsAvailble = new HashMap<>();
        iconsAvailble.put("nintendo", R.drawable.ic_nintendo);
        iconsAvailble.put("wii", R.drawable.ic_nintendo);
        iconsAvailble.put("3ds", R.drawable.ic_nintendo);
        iconsAvailble.put("pc", R.drawable.ic_pc);
        iconsAvailble.put("playstation", R.drawable.ic_playstation);
        iconsAvailble.put("xbox", R.drawable.ic_xbox);
    }

    private void setUpGameScoreColor(){
        if(gameItemViewModel.getRatings_count()==0)gameRateScore.setTextColor(view.getResources().getColor(R.color.colorAccent));
        else if(Double.parseDouble(gameItemViewModel.gameRate)*20 > 72)gameRateScore.setTextColor(view.getResources().getColor(R.color.highScore));
        else if(Double.parseDouble(gameItemViewModel.gameRate)*20 > 50)gameRateScore.setTextColor(view.getResources().getColor(R.color.mediumScore));
        else gameRateScore.setTextColor(view.getResources().getColor(R.color.lowScore));
    }

    public GameItemViewModel getGameItemViewModel() {
        return gameItemViewModel;
    }

    private void setUpListeners(){
        moreDetails.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(mainLayoutGameCard,new AutoTransition());

                if(gameItemViewModel.isMoreDetailsOpen()){
                    detailsLayout.setVisibility(View.GONE);
                    mainLayoutGameCard.getLayoutParams().height= (int) (400 * scale + 0.5f);
                    gameItemViewModel.setMoreDetailsOpen(false);
                }else{
                    detailsLayout.setVisibility(View.VISIBLE);
                    mainLayoutGameCard.getLayoutParams().height=(int) (600 * scale + 0.5f);;
                    gameItemViewModel.setMoreDetailsOpen(true);
                }
                mainLayoutGameCard.requestLayout();
            }
        });
    }
}
