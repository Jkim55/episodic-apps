package com.example.episodicshows.shows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EpisodeServiceTest {

    private EpisodeService episodeService;

    @Mock
    private EpisodeRepository episodeRepository;

    @Before
    public void init() {
        episodeService = new EpisodeService(episodeRepository);
    }

    @Test
    public void itCreatesAShow() {

        Episode savedEpisode = new Episode();
        savedEpisode.setId(1L);
        savedEpisode.setShowId(5L);
        savedEpisode.setSeasonNumber(3);
        savedEpisode.setEpisodeNumber(1);

        when(episodeRepository.save(any(Episode.class))).thenReturn(savedEpisode);

        assertThat(episodeService.createAnEpisode(5L, new Episode()), equalTo(savedEpisode));
    }

    @Test
    public void itCanListAllEpisodesGivenAShowId() {

        Episode savedEpisode1 = new Episode();
        savedEpisode1.setId(1L);
        savedEpisode1.setShowId(5L);
        savedEpisode1.setSeasonNumber(3);
        savedEpisode1.setEpisodeNumber(1);

        Episode savedEpisode2 = new Episode();
        savedEpisode2.setId(2L);
        savedEpisode2.setShowId(5L);
        savedEpisode2.setSeasonNumber(3);
        savedEpisode2.setEpisodeNumber(2);

        when(episodeRepository.findByShowId(any())).thenReturn(Arrays.asList(savedEpisode1, savedEpisode2));

        assertThat(episodeService.listAllEpisodesbyShow(5L), equalTo(Arrays.asList(savedEpisode1, savedEpisode2)));

    }

}