package com.example.episodicshows.users;

import com.example.episodicshows.shows.Episode;
import com.example.episodicshows.shows.EpisodeRepository;
import com.example.episodicshows.shows.Show;
import com.example.episodicshows.shows.ShowRepository;
import com.example.episodicshows.viewings.Viewing;
import com.example.episodicshows.viewings.ViewingRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Test
    @Transactional
    @Rollback
    public void itCanCreateNewUsers() throws Exception {
        final Long intialCount = userRepository.count();
        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("email", "jiggy@example.com");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload);


        this.mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", IsNull.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", equalTo("jiggy@example.com")));

        assertEquals(intialCount+1, userRepository.count());
    }

    @Test
    @Transactional
    @Rollback
    public void itCanGetUsers() throws Exception {
        final Long initialCount = userRepository.count();

        User user1 = new User();
        user1.setEmail("JJ@email.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("JT@email.com");
        userRepository.save(user2);

        MockHttpServletRequestBuilder getRequest = get("/users")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", IsInstanceOf.instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", equalTo("JJ@email.com")))
                .andExpect(jsonPath("$.[1].id", IsInstanceOf.instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].email", equalTo("JT@email.com")));

        assertEquals(initialCount+2, userRepository.count());
    }

    @Test
    @Transactional
    @Rollback
    public void itCanCreateAViewingEntry() throws Exception{
        User user = new User();
        user.setEmail("mintsForAll@purmints.co");
        userRepository.save(user);
        Long userId = user.getId();

        Show show = new Show();
        show.setName("Infomercial for mints");
        showRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setSeasonNumber(2);
        episode.setEpisodeNumber(1);
        episodeRepository.save(episode);

        final Long initialCount = viewingRepository.count();
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("episodeId", episode.getId());
                put("updatedAt", "2017-05-04T11:45:34.9182");
                put("timecode", 79);
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder patchRequest = patch("/users/{userId}/viewings", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonPayload);

        this.mvc.perform(patchRequest)
                .andExpect(status().isOk());

        Assert.assertEquals(initialCount + 1, viewingRepository.count());

    }

    @Test
    @Transactional
    @Rollback
    public void itCanRetrieveAViewingEntry() throws Exception {
        User user = new User();
        user.setEmail("mintsForAll@purmints.co");
        userRepository.save(user);
        Long userId = user.getId();

        Show show = new Show();
        show.setName("Infomercial for mints");
        showRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setSeasonNumber(2);
        episode.setEpisodeNumber(1);
        episodeRepository.save(episode);

        Viewing viewing1 = new Viewing();
        viewing1.setShowId(episode.getShowId());
        viewing1.setUserId(userId);
        viewing1.setEpisodeId(episode.getId());
        viewing1.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        viewing1.setTimecode(210);
        viewingRepository.save(viewing1);

        MockHttpServletRequestBuilder getRequest = get("/users/{userId}/recently-watched", userId)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].updatedAt", equalTo(viewing1.getUpdatedAt().toString())))
                .andExpect(jsonPath("$[0].timecode", equalTo(210)))
                .andExpect(jsonPath("$[0].show.name", equalTo("Infomercial for mints")))
                .andExpect(jsonPath("$[0].episode.seasonNumber", equalTo(2)))
                .andExpect(jsonPath("$[0].episode.episodeNumber", equalTo(1)));
    }
}
