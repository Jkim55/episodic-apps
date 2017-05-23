package com.example.episodicevents.events;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EventsController {
    private final EventsService eventService;

    public EventsController(EventsService eventService) {
        assert eventService != null;
        this.eventService = eventService;
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.createNewOrUpdateExistingEvent(event);
    }

    @GetMapping("/recent")
    public List<Event> getEvents() {
        return eventService.listEventsSortedByCreatedAt();
    }

}
