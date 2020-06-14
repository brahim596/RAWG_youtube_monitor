package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_youtube_monitor.R;

public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView title;
    private TextView channelTitle;
    private TextView nbViews;
    private ImageView thumbnail;

    private YoutubeVideoItemViewModel youtubeVideoItemViewModel;

    public YoutubeVideoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        this.title = view.findViewById(R.id.videoTitle);
        this.channelTitle = view.findViewById(R.id.channelTitle);
        this.nbViews = view.findViewById(R.id.nbViews);
        this.thumbnail = view.findViewById(R.id.thumbnail);
    }

    public void bindViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel){
        this.youtubeVideoItemViewModel = youtubeVideoItemViewModel;
        this.title.setText(youtubeVideoItemViewModel.getTitle());
        this.nbViews.setText(youtubeVideoItemViewModel.getView_count());
        this.channelTitle.setText(youtubeVideoItemViewModel.getChannel_title());
        Glide.with(view).load(this.youtubeVideoItemViewModel.getThumbnail()).fitCenter().transition(DrawableTransitionOptions.withCrossFade(100)).into(this.thumbnail);
    }

}
