package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.YoutubeVideoGamesContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideoViewHolder> {

    private List<YoutubeVideoItemViewModel> youtubeVideoItemViewModelList;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;

    /**
     * This map is use to save main youtube video id
     * with his childs video when they are displayed
     */
    private Map<String,List<YoutubeVideoItemViewModel>> saveMainYoutubeIdWithChildsVideoMap;

    public YoutubeVideosAdapter() {
        this.youtubeVideoItemViewModelList = new ArrayList<>();
        this.saveMainYoutubeIdWithChildsVideoMap = new HashMap<>();
    }

    public void setYoutubeVideoGamesContract(YoutubeVideoGamesContract youtubeVideoGamesContract) {
        this.youtubeVideoGamesContract = youtubeVideoGamesContract;
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YoutubeVideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_card, parent, false), youtubeVideoGamesContract);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        holder.bindViewModel(youtubeVideoItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return youtubeVideoItemViewModelList.size();
    }

    public void displayMoreVideoById(String id) {
        YoutubeVideoItemViewModel yt = null;
        for (YoutubeVideoItemViewModel youtubeVideoItemViewModel : youtubeVideoItemViewModelList)
            if (youtubeVideoItemViewModel.getYoutube_id().equals(id)) {
                yt = youtubeVideoItemViewModel;
                break;
            }
        int position = this.youtubeVideoItemViewModelList.indexOf(yt);
        this.youtubeVideoItemViewModelList.addAll(position+1,yt.getMoreVideo());
        List<YoutubeVideoItemViewModel> copyList = new ArrayList<>();
        copyList.addAll(yt.getMoreVideo());
        this.saveMainYoutubeIdWithChildsVideoMap.put(yt.getYoutube_id(),copyList);
        yt.setMorevideoClicked(true);
        this.notifyDataSetChanged();
    }

    public void reduceMoreVideo(String id){
        this.youtubeVideoItemViewModelList.removeAll(this.saveMainYoutubeIdWithChildsVideoMap.get(id));
        this.saveMainYoutubeIdWithChildsVideoMap.remove(id);
        this.notifyDataSetChanged();
    }

    public void bindViewModels(List<YoutubeVideoItemViewModel> youtubeVideoItemViewModels) {
        this.youtubeVideoItemViewModelList.clear();
        this.youtubeVideoItemViewModelList.addAll(youtubeVideoItemViewModels);
        this.notifyDataSetChanged();
    }

    public void addSingleViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel) {
        this.youtubeVideoItemViewModelList.add(youtubeVideoItemViewModel);
        this.notifyDataSetChanged();
    }
}
