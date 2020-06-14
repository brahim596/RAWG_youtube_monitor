package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
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
    private ImageView playButton;
    StringBuilder util = new StringBuilder();
    private FrameLayout flagChild;

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
        this.flagChild = view.findViewById(R.id.flagColor);
        this.playButton = view.findViewById(R.id.playButton);
    }

    public void bindViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel){
        this.youtubeVideoItemViewModel = youtubeVideoItemViewModel;
        this.title.setText(youtubeVideoItemViewModel.getTitle());
        this.nbViews.setText(reformatNbViewsString(youtubeVideoItemViewModel.getView_count()+""));
        this.channelTitle.setText(youtubeVideoItemViewModel.getChannel_title());
        this.moreVideo.setText("Voir plus");
        this.moreVideo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_baseline_arrow_drop_down_24);

        if(youtubeVideoItemViewModel.isMorevideoClicked()){
            if(!youtubeVideoItemViewModel.isChild()) {
                this.moreVideo.setText("Réduire");
                this.moreVideo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_arrow_drop_up_24, 0, 0);
            }else {
                this.moreVideo.setText("");
                this.moreVideo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }

        if(!youtubeVideoItemViewModel.isChild()){
            this.flagChild.setBackgroundColor(view.getResources().getColor(R.color.mainYoutubeVideoCardFlag));
        }else {
            this.flagChild.setBackgroundColor(view.getResources().getColor(R.color.childYoutubeVideoCardFlag));

        }
        Glide.with(view).load(this.youtubeVideoItemViewModel.getThumbnail()).fitCenter().transition(DrawableTransitionOptions.withCrossFade(100)).into(this.thumbnail);
        setUpListenner();
    }

    public void setUpListenner(){

        /**
         * click on 'more video' or 'reduce'
         */
        this.moreVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!youtubeVideoItemViewModel.isMorevideoClicked())
                    youtubeVideoGamesContract.viewMoreVideo(youtubeVideoItemViewModel.getYoutube_id());
                else {
                    youtubeVideoGamesContract.reduceMoreVideo(youtubeVideoItemViewModel.getYoutube_id());
                    youtubeVideoItemViewModel.setMorevideoClicked(false);
                }
            }
        });

        /**
         * click on button 'play'
         */
        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeVideoItemViewModel.getExternal_id()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" +youtubeVideoItemViewModel.getExternal_id()));
                try {
                    view.getContext().startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    view.getContext().startActivity(webIntent);
                }
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
