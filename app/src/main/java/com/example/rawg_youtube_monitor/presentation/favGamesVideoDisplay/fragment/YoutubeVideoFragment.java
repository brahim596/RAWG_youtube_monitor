package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.DependencyInjection;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.YoutubeVideoGamesContract;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.YoutubeVideoGamesPresenter;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideoItemViewModel;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideosAdapter;

import java.util.List;

public class YoutubeVideoFragment extends Fragment implements YoutubeVideoGamesContract {

    private View view;
    private RecyclerView youtubeVideoRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private YoutubeVideosAdapter youtubeVideosAdapter;
    private YoutubeVideoGamesPresenter youtubeVideoGamesPresenter;




    public YoutubeVideoFragment() {}

    public static YoutubeVideoFragment newInstance(){
        return new YoutubeVideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_fav_games_video,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.youtubeVideoGamesPresenter = new YoutubeVideoGamesPresenter(DependencyInjection.getGamesRepository(),DependencyInjection.getYoutubeVideoRepository());
        this.youtubeVideoGamesPresenter.setYoutubeVideoGamesContract(this);
        setUpRecyclerView();
       // this.youtubeVideoGamesPresenter.getAllFavoriteGamesYoutubeVideo();
        this.youtubeVideoGamesPresenter.getYoutubeVideoGamePage();
    }

    public void setUpRecyclerView(){
        this.youtubeVideosAdapter=new YoutubeVideosAdapter();
        this.youtubeVideosAdapter.setYoutubeVideoGamesContract(this);
        this.youtubeVideoRecyclerView = view.findViewById(R.id.youtube_video_recycler_view);
        this.youtubeVideoRecyclerView.setAdapter(youtubeVideosAdapter);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        this.youtubeVideoRecyclerView.setLayoutManager(linearLayoutManager);
        this.youtubeVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        youtubeVideoGamesPresenter.getYoutubeVideoGamePage();
                    }
                }
            }
        });
    }

    @Override
    public void displayAllVideo(List<YoutubeVideoItemViewModel> youtubeVideoItemViewModels) {
        this.youtubeVideosAdapter.bindViewModels(youtubeVideoItemViewModels);
    }

    @Override
    public void addYoutubeVideo(YoutubeVideoItemViewModel youtubeVideoItemViewModel) {
        this.youtubeVideosAdapter.addSingleViewModel(youtubeVideoItemViewModel);
        this.youtubeVideoRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void viewMoreVideo(String id) {
        this.youtubeVideosAdapter.displayMoreVideoById(id);
        this.youtubeVideoRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void reduceMoreVideo(String id) {
        this.youtubeVideosAdapter.reduceMoreVideo(id);
        this.youtubeVideoRecyclerView.scheduleLayoutAnimation();
    }
}
