package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.YoutubeVideoGamesContract;

public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView title;
    private TextView channelTitle;
    private TextView nbViews;
    private TextView moreVideo;
    private ImageView thumbnail;
    StringBuilder util = new StringBuilder();

    private YoutubeVideoItemViewModel youtubeVideoItemViewModel;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;

    public YoutubeVideoViewHolder(@NonNull View itemView, YoutubeVideoGamesContract youtubeVideoGamesContract) {
        super(itemView);
        this.view = itemView;
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
        this.title = view.findViewById(R.id.videoTitle);
        this.channelTitle = view.findViewById(R.id.channelTitle);
        this.nbViews = view.findViewById(R.id.nbViews);
        this.thumbnail = view.findViewById(R.id.thumbnail);
        this.moreVideo = view.findViewById(R.id.moreVideo);
    }

    public void bindViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel){
        this.youtubeVideoItemViewModel = youtubeVideoItemViewModel;
        this.title.setText(youtubeVideoItemViewModel.getTitle());
        this.nbViews.setText(reformatNbViewsString(youtubeVideoItemViewModel.getView_count()+""));
        this.channelTitle.setText(youtubeVideoItemViewModel.getChannel_title());
        Glide.with(view).load(this.youtubeVideoItemViewModel.getThumbnail()).fitCenter().transition(DrawableTransitionOptions.withCrossFade(100)).into(this.thumbnail);
        setUpListenner();
    }

    public void setUpListenner(){
        this.moreVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeVideoGamesContract.viewMoreVideo(youtubeVideoItemViewModel.getYoutube_id());
            }
        });
    }

    /**
     * from 1254687 views
     * to   1 254 687
     * @return
     */
    private String reformatNbViewsString(String s) {
        util.setLength(0);
        util.append(s);
        util.reverse();
        s = util.toString();
        s = s.replaceAll("...(?!$)", "$0 ");
        util.setLength(0);
        util.append(s);
        util.reverse();

        return util.toString();
    }


}
