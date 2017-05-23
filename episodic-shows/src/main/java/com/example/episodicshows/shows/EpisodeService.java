package com.example.episodicshows.shows;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public Episode createAnEpisode(Long showId, Episode episode) {
        episode.setShowId(showId);
        return episodeRepository.save(episode);
    }

    public List<Episode> listAllEpisodesbyShow(Long showId) {
        return episodeRepository.findByShowId(showId);
    }

    public Episode findById(Long episodeId) {
        return episodeRepository.findOne(episodeId);
    }
}
