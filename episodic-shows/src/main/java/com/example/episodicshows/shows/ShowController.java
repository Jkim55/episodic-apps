package com.example.episodicshows.shows;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping
    public List<Show> listAllShows(){
        List<Show> showList = new ArrayList<>();
        this.showRepository.findAll().forEach(showList::add);
        return showList;
    }

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return this.showRepository.save(show);
    }
}