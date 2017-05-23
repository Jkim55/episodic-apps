package com.example.episodicshows.shows;

import org.springframework.stereotype.Service;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Iterable<Show> listAllShows() {
        return showRepository.findAll();
    }

    public Show createAShow(Show show) {
        return showRepository.save(show);
    }

    public Show findById(Long showId) {
        return showRepository.findOne(showId);
    }
}
