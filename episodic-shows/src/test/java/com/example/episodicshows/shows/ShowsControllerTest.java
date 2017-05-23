package com.example.episodicshows.shows;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Test
    @Transactional
    @Rollback
    public void itCanCreateNewShows() throws Exception {
        final Long initialCount = showRepository.count();
        ObjectMapper mapper = new ObjectMapper();
        Map payload = singletonMap("name", "The Mindy Project");

        MockHttpServletRequestBuilder postRequest = post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(payload));

        this.mvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", IsNull.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("The Mindy Project")));

        assertEquals(initialCount + 1, showRepository.count());

    }

    @Test
    @Transactional
    @Rollback
    public void itCanListAllShows() throws Exception {
        final Long initialCount = showRepository.count();

        Show show1 = new Show();
        show1.setName("The Mindy Project");
        showRepository.save(show1);

        Show show2 = new Show();
        show2.setName("Master of None");
        showRepository.save(show2);

        MockHttpServletRequestBuilder getRequest = get("/shows")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..name", containsInAnyOrder("The Mindy Project", "Master of None")));

        Assert.assertEquals(initialCount + 2, showRepository.count());
    }

    @Test
    @Transactional
    @Rollback
    public void itCanCreateAnEpisodeByShowID() throws Exception {
        Show show = new Show();
        show.setName("Master of None");
        showRepository.save(show);
        Long showId = show.getId();

        final Long initialCount = episodeRepository.count();

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("seasonNumber", 2);
                put("episodeNumber", 1);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder postRequest = post(String.format("/shows/%d/episodes", showId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload);


        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", IsNull.notNullValue()))
                .andExpect(jsonPath("$.id", IsInstanceOf.instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seasonNumber", equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeNumber", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", equalTo("S2 E1")));

        Assert.assertEquals(initialCount + 1, episodeRepository.count());

    }

    @Test
    @Transactional
    @Rollback
    public void itCanListAllEpisodesGivenAShowId() throws Exception {
        Show show = new Show();
        show.setName("Master of None");
        showRepository.save(show);
        Long showId = show.getId();

        final Long initialCount = episodeRepository.count();

        Episode savedEpisode1 = new Episode();
        savedEpisode1.setId(1L);
        savedEpisode1.setShowId(showId);
        savedEpisode1.setSeasonNumber(3);
        savedEpisode1.setEpisodeNumber(1);
        episodeRepository.save(savedEpisode1);

        Episode savedEpisode2 = new Episode();
        savedEpisode2.setId(2L);
        savedEpisode2.setShowId(showId);
        savedEpisode2.setSeasonNumber(3);
        savedEpisode2.setEpisodeNumber(2);
        episodeRepository.save(savedEpisode2);

        MockHttpServletRequestBuilder getRequest = get(String.format("/shows/%d/episodes", showId))
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..seasonNumber", containsInAnyOrder(3, 3)))
                .andExpect(jsonPath("$..episodeNumber", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$..title", containsInAnyOrder("S3 E1", "S3 E2")));
    }
}