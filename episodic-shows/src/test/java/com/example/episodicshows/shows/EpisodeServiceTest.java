package com.example.episodicshows.shows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EpisodeServiceTest {

    private EpisodeService episodeService;

    @Mock
    private EpisodeResponse episodeResponse;

    @Mock
    private EpisodeRepository episodeRepository;

    @Before
    public void init() {
        episodeService = new EpisodeService(episodeRepository);
    }

    @Test
    public void itCreatesAShow() {
        Episode payload = new Episode();
        payload.setSeasonNumber(2);
        payload.setEpisodeNumber(1);

        Episode savedEpisode = new Episode();
        savedEpisode.setId((long) 1);
        savedEpisode.setShowId((long) 5);
        savedEpisode.setSeasonNumber(3);
        savedEpisode.setEpisodeNumber(1);

        when(episodeRepository.save(any(Episode.class))).thenReturn(savedEpisode);

        EpisodeResponse episodeResponse = new EpisodeResponse((long) 1, 3, 1, "S3 E1");

        when(new EpisodeResponse(any(), any(), any(), any())).thenReturn(episodeResponse);

        assertThat(episodeService.createAnEpisode((long) 5, payload), equalTo(episodeResponse));
    }

    @Test
    public void itCanListAllEpisodesGivenAShowId() {

        Episode savedEpisode1 = new Episode();
        savedEpisode1.setId((long) 1);
        savedEpisode1.setShowId((long) 5);
        savedEpisode1.setSeasonNumber(3);
        savedEpisode1.setEpisodeNumber(1);

        Episode savedEpisode2 = new Episode();
        savedEpisode2.setId((long) 2);
        savedEpisode2.setShowId((long) 5);
        savedEpisode2.setSeasonNumber(3);
        savedEpisode2.setEpisodeNumber(2);

        EpisodeResponse episodeResponse1 = new EpisodeResponse((long) 1, 3, 1, "S3 E1");
        EpisodeResponse episodeResponse2 = new EpisodeResponse((long) 2, 3, 2, "S3 E2");

        when(episodeRepository.findByShowId((long)5)).thenReturn(Arrays.asList(savedEpisode1, savedEpisode2));

        assertThat(episodeService.listAllEpisodesbyShow((long)5), equalTo(Arrays.asList(episodeResponse1, episodeResponse2)));

    }

}