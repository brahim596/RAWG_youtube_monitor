package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideosAdapter extends RecyclerView.Adapter<YoutubeVideoViewHolder> {

    private List<YoutubeVideoItemViewModel> youtubeVideoItemViewModelList;

    public YoutubeVideosAdapter() {
        this.youtubeVideoItemViewModelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YoutubeVideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        holder.bindViewModel(youtubeVideoItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return youtubeVideoItemViewModelList.size();
    }

    public void bindViewModels(List<YoutubeVideoItemViewModel> youtubeVideoItemViewModels){
        this.youtubeVideoItemViewModelList.clear();
        this.youtubeVideoItemViewModelList.addAll(youtubeVideoItemViewModels);
        this.notifyDataSetChanged();
    }

    public void addSingleViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel){
        this.youtubeVideoItemViewModelList.add(youtubeVideoItemViewModel);
        this.notifyDataSetChanged();
    }
}
