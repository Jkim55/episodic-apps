package com.example.episodicshows.shows;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EpisodeResponse {
    private Long id;
    private int seasonNumber;
    private int episodeNumber;
    private String title;

    @JsonCreator
    public EpisodeResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("seasonNumber") int seasonNumber,
            @JsonProperty("episodeNumber") int episodeNumber,
            @JsonProperty("title") String title) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.title = title;
    }

}
