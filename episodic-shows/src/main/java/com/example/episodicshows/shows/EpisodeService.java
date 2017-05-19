package com.example.episodicshows.shows;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public EpisodeResponse createAnEpisode(Long showId, Episode episode) {
        episode.setShowId(showId);
        Episode savedEpisode = episodeRepository.save(episode);

        String savedEpisodeTitle = String.format("S%d E%d", savedEpisode.getSeasonNumber(), savedEpisode.getEpisodeNumber());

        EpisodeResponse episodeResponse = new EpisodeResponse(
                savedEpisode.getId(),
                savedEpisode.getSeasonNumber(),
                savedEpisode.getEpisodeNumber(),
                savedEpisodeTitle);

        // return should include episode's id & title; no show title
        return episodeResponse;
    }

    public List<EpisodeResponse> listAllEpisodesbyShow(Long showId) {
        List<Episode> episodes = episodeRepository.findByShowId(showId);
        // map thru every episode in the list
            // create a new episodeResponse  //TODO: could you pull creating episodeResponse out into a method?
                // set episodeResponse.id
                // set episodeResponse.seasonNumber
                // set episodeResponse.episodeNumber
                // form the title w episode info & set episodeResponse.title
        //return a list of episode responses

        return null;
    }
}
