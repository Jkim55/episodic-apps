package com.example.episodicshows.shows;

import java.util.ArrayList;
import java.util.List;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }


    public List<Show> listAllShows() {
        List<Show> showList = new ArrayList<>();
        showRepository.findAll().forEach(showList::add);
        return showList;
    }

    public Show createAShow(Show show) {
        return showRepository.save(show);
    }
}
