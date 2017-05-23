package com.example.episodicshows.viewings;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
