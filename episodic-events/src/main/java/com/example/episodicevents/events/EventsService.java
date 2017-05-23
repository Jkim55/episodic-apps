package com.example.episodicevents.events;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {
    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<Event> listEventsSortedByCreatedAt() {
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }

    public Event createNewOrUpdateExistingEvent(Event event) {
        return eventsRepository.save(event);
    }
}
