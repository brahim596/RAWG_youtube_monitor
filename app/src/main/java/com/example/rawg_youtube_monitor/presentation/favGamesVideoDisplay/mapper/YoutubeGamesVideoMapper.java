package com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.mapper;

import com.example.rawg_youtube_monitor.data.db.entity.YoutubeVideoEntity;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideo;
import com.example.rawg_youtube_monitor.data.model.YoutubeVideoGamesResponse;
import com.example.rawg_youtube_monitor.exceptions.YoutubeVideoNotFoundException;
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
        return new YoutubeVideoItemViewModel(yt.getYoutube_id(), yt.getChannel_title(), yt.getTitle(), yt.getDescription(), yt.getCreated(), yt.getView_count(), yt.getLike_count(), yt.getDislike_count(), yt.getThumbnail(),null);
    }

    public List<YoutubeVideoItemViewModel> mapYoutubeGameVideoListToYoutubeVideoItemViewModelList(List<YoutubeVideo> yts) {
        List<YoutubeVideoItemViewModel> youtubeVideoItemViewModelList = new ArrayList<>();
        for(YoutubeVideo yt:yts)
            youtubeVideoItemViewModelList.add(mapYoutubeGameVideoToYoutubeVideoItemViewModel(yt));

        return  youtubeVideoItemViewModelList;
    }

    public YoutubeVideoItemViewModel mapYoutubeGameVideoToYoutubeVideoItemViewModel(YoutubeVideo yt) {
        return new YoutubeVideoItemViewModel(yt.getId()+"", yt.getChannel_title(), yt.getName(), yt.getDescription(), yt.getCreated(), yt.getView_count(), yt.getLike_count(), yt.getDislike_count(), yt.getThumbnails().get("medium").getUrl(),yt.getExternal_id());
    }

    public YoutubeVideoItemViewModel mapYoutubeGameVideoResponseToYoutubeVideoItemViewModel(YoutubeVideoGamesResponse ytrs) throws YoutubeVideoNotFoundException {
        if(ytrs.getResults().size()==0) throw new YoutubeVideoNotFoundException("Youtube video not found");
        YoutubeVideo yt = ytrs.getResults().get(0);
        ytrs.getResults().remove(0);
        YoutubeVideoItemViewModel youtubeVideoItemViewModel = mapYoutubeGameVideoToYoutubeVideoItemViewModel(yt);
        youtubeVideoItemViewModel.getMoreVideo().addAll(mapYoutubeGameVideoListToYoutubeVideoItemViewModelList(ytrs.getResults()));
        youtubeVideoItemViewModel.setMorevideoClicked(false);
        youtubeVideoItemViewModel.setChild(false);

        return youtubeVideoItemViewModel;
    }

}
