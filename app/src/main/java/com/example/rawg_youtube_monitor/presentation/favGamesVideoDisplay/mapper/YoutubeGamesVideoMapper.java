package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.mapper;

import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.Game;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.adapter.YoutubeVideoItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class YoutubeGamesVideoMapper {

    public YoutubeGamesVideoMapper() {}

    public List<YoutubeVideoItemViewModel> mapListYoutubeEntitiesToListYoutubeVideoItemViewModel(List<YoutubeVideoEntity> youtubeVideoEntities){
        List<YoutubeVideoItemViewModel> youtubeVideoItemViewModelList = new ArrayList<>();
        for(YoutubeVideoEntity ye:youtubeVideoEntities)
            youtubeVideoItemViewModelList.add(this.mapYoutubeGameVideoEntityToYoutubeVideoItemViewModel(ye));

        return  youtubeVideoItemViewModelList;
    }

    public YoutubeVideoItemViewModel mapYoutubeGameVideoEntityToYoutubeVideoItemViewModel(YoutubeVideoEntity yt) {
        return new YoutubeVideoItemViewModel(yt.getYoutube_id(), yt.getChannel_title(), yt.getTitle(), yt.getDescription(), yt.getCreated(), yt.getView_count(), yt.getLike_count(), yt.getDislike_count(), yt.getThumbnail());
    }

}
