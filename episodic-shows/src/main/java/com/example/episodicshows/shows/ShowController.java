package com.example.episodicshows.shows;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<Show> listAllShows(){
        return showService.listAllShows();
    }

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.createAShow(show);
    }
}