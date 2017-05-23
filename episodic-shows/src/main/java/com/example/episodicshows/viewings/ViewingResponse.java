package com.example.episodicshows.viewings;

import com.example.episodicshows.shows.Episode;
import com.example.episodicshows.shows.Show;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewingResponse {
    private Show show;
    private Episode episode;
    private LocalDateTime updatedAt;
    private int timecode;

}
