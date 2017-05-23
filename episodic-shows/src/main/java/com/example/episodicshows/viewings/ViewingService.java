package com.example.episodicshows.viewings;

import com.example.episodicshows.shows.Episode;
import com.example.episodicshows.shows.EpisodeRepository;
import com.example.episodicshows.shows.Show;
import com.example.episodicshows.shows.ShowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewingService {
    private final ViewingRepository viewingRepository;  //TODO: is this a weird pattern?
    private final EpisodeRepository episodeRepository;
    private final ShowRepository showRepository;

    public ViewingService(
            ViewingRepository viewingRepository,
            EpisodeRepository episodeRepository,
            ShowRepository showRepository) {
        this.viewingRepository = viewingRepository;
        this.episodeRepository = episodeRepository;
        this.showRepository = showRepository;
    }

    public ResponseEntity createAViewingEntry(
            Long userId,
            Viewing viewingPayload) {
        Viewing existingViewingEntry = viewingRepository.findByUserIdAndEpisodeId(userId, viewingPayload.getEpisodeId());
        Episode episode = episodeRepository.findOne(viewingPayload.getEpisodeId());
        Show show = showRepository.findOne(episode.getShowId());

        if (existingViewingEntry == null) {
            viewingPayload.setUserId(userId);
            viewingPayload.setShowId(show.getId());
            viewingPayload.setEpisodeId(episode.getId());
            viewingPayload.setEpisode(episode);
            viewingPayload.setShow(show);
            viewingRepository.save(viewingPayload);
        } else {
            existingViewingEntry.setUpdatedAt(viewingPayload.getUpdatedAt());
            existingViewingEntry.setTimecode(viewingPayload.getTimecode());
            viewingRepository.save(existingViewingEntry);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    public List<Viewing> getViewingDataByUserID(Long userId) {
        List<Viewing> viewings = viewingRepository.findAllByUserId(userId);

        List<Viewing> viewingResponses = viewings
                .stream()
                .map(viewing ->
                {
                    Show show = showRepository.findOne(viewing.getShowId());
                    Episode episode = episodeRepository.findOne(viewing.getEpisodeId());
                    viewing.setShow(show);
                    viewing.setEpisode(episode);
                    return viewing;
                })
                .collect(Collectors.toList());
        return viewingResponses;

    }

}