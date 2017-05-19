package com.example.episodicshows.shows;

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

    private Long showId;
    private int seasonNumber;
    private int episodeNumber;

}
