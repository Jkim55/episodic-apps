package com.example.episodicshows.shows;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;
    private final EpisodeService episodeService;

    public ShowController(ShowService showService, EpisodeService episodeService) {
        assert showService != null;
        assert episodeService != null;
        this.showService = showService;
        this.episodeService = episodeService;
    }

    @GetMapping
    public Iterable<Show> listAllShows(){
        return showService.listAllShows();
    }

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.createAShow(show);
    }

    @GetMapping("/{id}/episodes")
    public List<Episode> listAllEpisodesForSpecificShow(@PathVariable("id") Long showId){
        return episodeService.listAllEpisodesbyShow(showId);
    }

    @PostMapping("/{id}/episodes")
    public Episode createAnEpisodeForExistingShow(@PathVariable("id") Long showId, @RequestBody Episode episode) {
        return episodeService.createAnEpisode(showId, episode);
    }
}