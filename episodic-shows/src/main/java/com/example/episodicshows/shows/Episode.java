package com.example.episodicshows.shows;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "episodes")
@Getter
@Setter
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private Long showId;
    private int seasonNumber;
    private int episodeNumber;


    public String getTitle () {
        StringBuilder sb = new StringBuilder();
        sb.append("S");
        sb.append(((Integer)seasonNumber).toString());
        sb.append(" ");
        sb.append("E");
        sb.append(((Integer)episodeNumber).toString());
        return sb.toString();
    }
}
