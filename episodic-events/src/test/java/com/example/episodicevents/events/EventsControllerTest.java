package com.example.episodicevents.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    @Rollback
    public void canCreateAPlayEvent() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void canCreateAPauseEvent() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void canCreateAFastForwardEvent() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void canCreateARewindEvent() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void canCreateAProgressEvent() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void canCreateAScrubEvent() throws Exception {

    }

}