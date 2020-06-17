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

    //contain all youtube video model to display
    private List<YoutubeVideoItemViewModel> youtubeVideoItemViewModelList;
    private YoutubeVideoGamesContract youtubeVideoGamesContract;

    /**
     * This map is use to save main youtube video id
     * with his childs video when they are displayed
     * <p>
     * Explanation : The Api return us multiple video for one game
     * I decide to display just one video for each favorite game
     * on the video card view there is a "see more" button which allow the user
     * to display the others "childs" video of the game which are save in this map
     */
    private Map<String, List<YoutubeVideoItemViewModel>> saveMainYoutubeIdWithChildsVideoMap;

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

    /**
     * Display the childs youtube video of the given youtube id
     *
     * @param youtubeVideoId
     */
    public void displayMoreVideoById(String youtubeVideoId) {

        //Search the youtubeVideoViewModel corresponding to the given id
        YoutubeVideoItemViewModel yt = null;
        for (YoutubeVideoItemViewModel youtubeVideoItemViewModel : youtubeVideoItemViewModelList)
            if (youtubeVideoItemViewModel.getYoutube_id().equals(youtubeVideoId)) {
                yt = youtubeVideoItemViewModel;
                break;
            }

        int position = this.youtubeVideoItemViewModelList.indexOf(yt);

        /**
         * add the childs video to the list just after le main video
         * the position of the childs video is important to see them appear
         * just below the main video
         */
        this.youtubeVideoItemViewModelList.addAll(position + 1, yt.getMoreVideo());
        List<YoutubeVideoItemViewModel> copyList = new ArrayList<>();
        copyList.addAll(yt.getMoreVideo());

        /**
         * save the added childs video to a map link to main video id to remove them
         * if the user want with the button 'reduce'
         */
        this.saveMainYoutubeIdWithChildsVideoMap.put(yt.getYoutube_id(), copyList);
        yt.setMorevideoClicked(true);
        this.notifyDataSetChanged();
    }

    /**
     * Remove the childs youtube video of given id
     * to stop display them
     * @param id
     */
    public void reduceMoreVideo(String id) {
        this.youtubeVideoItemViewModelList.removeAll(this.saveMainYoutubeIdWithChildsVideoMap.get(id));
        this.saveMainYoutubeIdWithChildsVideoMap.remove(id);
        this.notifyDataSetChanged();
    }

    public void bindViewModels(List<YoutubeVideoItemViewModel> youtubeVideoItemViewModels) {
        if (youtubeVideoItemViewModels.size() == 0)
            this.youtubeVideoGamesContract.noYoutubeVideosInFavoriteMessage();
        else this.youtubeVideoGamesContract.disablenoYoutubeVideosInFavoriteMessage();
        this.youtubeVideoItemViewModelList.clear();
        this.youtubeVideoItemViewModelList.addAll(youtubeVideoItemViewModels);
        this.notifyDataSetChanged();
    }

    public void addSingleViewModel(YoutubeVideoItemViewModel youtubeVideoItemViewModel) {
        this.youtubeVideoGamesContract.disablenoYoutubeVideosInFavoriteMessage();
        this.youtubeVideoItemViewModelList.add(youtubeVideoItemViewModel);
        this.notifyDataSetChanged();
    }
}
