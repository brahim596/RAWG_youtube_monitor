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
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.YoutubeVideoGamesContract;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideosAdapter;

public class YoutubeVideoFragment extends Fragment implements YoutubeVideoGamesContract {

    private View view;
    private RecyclerView youtubeVideoRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private YoutubeVideosAdapter favGameAdapter;




    public YoutubeVideoFragment() {}

    public static YoutubeVideoFragment newInstance(){
        return new YoutubeVideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_fav_games_collection,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        this.favGameAdapter=new YoutubeVideosAdapter();
        this.youtubeVideoRecyclerView = view.findViewById(R.id.youtube_video_recycler_view);
        this.youtubeVideoRecyclerView.setAdapter(favGameAdapter);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        this.youtubeVideoRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void displayAllVideo() {

    }
}
