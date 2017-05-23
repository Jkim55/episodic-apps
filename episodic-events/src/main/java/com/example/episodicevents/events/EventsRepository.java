package com.example.episodicevents.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventsRepository extends MongoRepository<Event,Long> {

    @Query("{}")
    List<Event> findRecent(Pageable page);

}