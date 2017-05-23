package com.example.episodicshows.viewings;

import com.example.episodicshows.shows.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "viewings")
@Getter
@Setter
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;

    private Long userId;

    private Long episodeId;

    private LocalDateTime updatedAt;

    private int timecode;

    @Transient
    private Episode episode;

    @Transient
    private Show show;

}
