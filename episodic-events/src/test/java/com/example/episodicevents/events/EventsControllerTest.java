package com.example.episodicevents.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    EventsRepository eventsRepository;

    @Before
    public void setUp() {
        eventsRepository.deleteAll();
    }

    @Test
    public void canCreateAPlayEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> playData = new HashMap<String, Object>() {
            {
                put("offset", 10);
            }
        };

        Map<String, Object> playPaylaod = new HashMap<String, Object>() {
            {
                put("type", "play");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", playData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonPlayPayload = mapper.writeValueAsString(playPaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPlayPayload);


        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("play")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(52)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(987)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(456)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-08T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.offset", equalTo(10)));

        assertEquals(initialCount + 1, eventsRepository.count());

    }

    @Test
    public void canCreateAPauseEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> pauseData = new HashMap<String, Object>() {
            {
                put("offset", 1023);
            }
        };

        Map<String, Object> pausePaylaod = new HashMap<String, Object>() {
            {
                put("type", "pause");
                put("userId", 53);
                put("showId", 988);
                put("episodeId", 457);
                put("createdAt", "2017-11-09T15:59:13.0091745");
                put("data", pauseData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonPausePayload = mapper.writeValueAsString(pausePaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPausePayload);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("pause")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.offset", equalTo(1023)));

        assertEquals(initialCount + 1, eventsRepository.count());

    }

    @Test
    public void canCreateAFastForwardEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> fastForwardData = new HashMap<String, Object>() {
            {
                put("startOffset", 4);
                put("endOffset", 408);
                put("speed", 2.5);
            }
        };

        Map<String, Object> fastForwardPaylaod = new HashMap<String, Object>() {
            {
                put("type", "fastForward");
                put("userId", 53);
                put("showId", 988);
                put("episodeId", 457);
                put("createdAt", "2017-11-09T15:59:13.0091745");
                put("data", fastForwardData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonFastForwardPayload = mapper.writeValueAsString(fastForwardPaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonFastForwardPayload);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("fastForward")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.startOffset", equalTo(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.endOffset", equalTo(408)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.speed", equalTo(2.5)));

        assertEquals(initialCount + 1, eventsRepository.count());
    }

    @Test
    public void canCreateARewindEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> rewindData = new HashMap<String, Object>() {
            {
                put("startOffset", 4);
                put("endOffset", 408);
                put("speed", 2.5);
            }
        };

        Map<String, Object> rewindPaylaod = new HashMap<String, Object>() {
            {
                put("type", "rewind");
                put("userId", 53);
                put("showId", 988);
                put("episodeId", 457);
                put("createdAt", "2017-11-09T15:59:13.0091745");
                put("data", rewindData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonRewindPayload = mapper.writeValueAsString(rewindPaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRewindPayload);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("rewind")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.startOffset", equalTo(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.endOffset", equalTo(408)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.speed", equalTo(2.5)));

        assertEquals(initialCount + 1, eventsRepository.count());
    }

    @Test
    public void canCreateAProgressEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> progressData = new HashMap<String, Object>() {
            {
                put("offset", 4);
            }
        };

        Map<String, Object> progressPaylaod = new HashMap<String, Object>() {
            {
                put("type", "progress");
                put("userId", 53);
                put("showId", 988);
                put("episodeId", 457);
                put("createdAt", "2017-11-09T15:59:13.0091745");
                put("data", progressData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonProgressPayload = mapper.writeValueAsString(progressPaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonProgressPayload);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("progress")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.offset", equalTo(4)));

        assertEquals(initialCount + 1, eventsRepository.count());

    }

    @Test
    public void canCreateAScrubEvent() throws Exception {

        final Long initialCount = eventsRepository.count();

        Map<String, Object> scrubData = new HashMap<String, Object>() {
            {
                put("startOffset", 4);
                put("endOffset", 408);
            }
        };

        Map<String, Object> scrubPaylaod = new HashMap<String, Object>() {
            {
                put("type", "scrub");
                put("userId", 53);
                put("showId", 988);
                put("episodeId", 457);
                put("createdAt", "2017-11-09T15:59:13.0091745");
                put("data", scrubData);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonScrubPayload = mapper.writeValueAsString(scrubPaylaod);

        MockHttpServletRequestBuilder postRequest = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonScrubPayload);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("scrub")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.startOffset", equalTo(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.endOffset", equalTo(408)));

        assertEquals(initialCount + 1, eventsRepository.count());
    }

    @Test
    public void canGetAListOfEvents() throws Exception {
        HashMap<String, Object> playData = new HashMap<String, Object>() {
            {
                put("offset", 10);
            }
        };
        PlayEvent playEvent = new PlayEvent();
        playEvent.setShowId(1L);
        playEvent.setEpisodeId(1L);
        playEvent.setCreatedAt(LocalDateTime.now());
        playEvent.setUserId(2L);
        playEvent.setData(playData);
        eventsRepository.save(playEvent);

        MockHttpServletRequestBuilder getRequest = get("/recent")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", equalTo("play")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].showId", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].episodeId", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt", equalTo(playEvent.getCreatedAt().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].data.offset", equalTo(10)));
    }
}