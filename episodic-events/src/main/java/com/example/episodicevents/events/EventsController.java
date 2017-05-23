package com.example.episodicevents.events;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EventsController {
    private final EventsRepository eventsRepository;

    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventsRepository.save(event);
    }

    @GetMapping("/recent")
    public List<Event> getEvents() {
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }

}
