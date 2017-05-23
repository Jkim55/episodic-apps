package com.example.episodicevents.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
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

    @Test
    @Transactional
    @Rollback
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
    @Transactional
    @Rollback
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

        final MvcResult mvcResult = this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andReturn();

//                .andExpect(MockMvcResultMatchers.jsonPath("$.type", equalTo("pause")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", equalTo(53)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.showId", equalTo(988)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId", equalTo(457)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt", equalTo("2017-11-09T15:59:13.0091745")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.offset", equalTo(1023)));

        assertEquals(initialCount + 1, eventsRepository.count());

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