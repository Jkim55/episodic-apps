package com.example.episodicshows.viewings;

import com.example.episodicshows.shows.Episode;
import com.example.episodicshows.shows.EpisodeRepository;
import com.example.episodicshows.shows.Show;
import com.example.episodicshows.shows.ShowRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewingServiceTest {

    private ViewingService viewingService;

    @Mock
    private ViewingRepository viewingRepository;

    @Mock
    private EpisodeRepository episodeRepository;

    @Mock
    private ShowRepository showRepository;

    @Before
    public void init() {
        viewingService = new ViewingService(viewingRepository, episodeRepository, showRepository);
    }

    @Test
    public void itCanCreateAViewingEntry() {
        Episode mockEpisode = new Episode();
        mockEpisode.setId(12L);
        mockEpisode.setShowId(1L);
        mockEpisode.setEpisodeNumber(2);
        mockEpisode.setSeasonNumber(3);
        when(episodeRepository.findOne(any())).thenReturn(mockEpisode);

        Show mockShow = new Show();
        mockShow.setId(1L);
        mockShow.setName("Mind of A Chef");
        when(showRepository.findOne(any())).thenReturn(mockShow);

        LocalDateTime dateTime = LocalDateTime.now();
        Viewing mockedViewing = new Viewing();
        mockedViewing.setId(1L);
        mockedViewing.setEpisodeId(12L);
        mockedViewing.setShowId(1L);
        mockedViewing.setUserId(8L);
        mockedViewing.setShow(mockShow);
        mockedViewing.setEpisode(mockEpisode);
        mockedViewing.setUpdatedAt(dateTime);
        mockedViewing.setTimecode(79);
        when(viewingRepository.save(any(Viewing.class))).thenReturn(mockedViewing);

        Viewing viewingPayload = new Viewing();
        viewingPayload.setEpisodeId(12L);
        viewingPayload.setUpdatedAt(dateTime);
        viewingPayload.setTimecode(79);

        ResponseEntity response = viewingService.createAViewingEntry(8L, viewingPayload);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void itCanListAllViewingEntriesByUserId() {
        //create out new viewing obj WITHOUT show & episode obj
        Viewing viewing = new Viewing();
        viewing.setId(1L);
        viewing.setEpisodeId(12L);
        viewing.setShowId(1L);
        viewing.setUserId(22L);
        viewing.setUpdatedAt(LocalDateTime.now());
        viewing.setTimecode(79);
        when(viewingRepository.findAllByUserId(anyLong())).thenReturn(asList(viewing));

        Episode mockEpisode = new Episode();
        mockEpisode.setId(12L);
        mockEpisode.setShowId(1L);
        mockEpisode.setEpisodeNumber(2);
        mockEpisode.setSeasonNumber(3);
        when(episodeRepository.findOne(any())).thenReturn(mockEpisode);

        Show mockShow = new Show();
        mockShow.setId(1L);
        mockShow.setName("Mind of A Chef");
        when(showRepository.findOne(any())).thenReturn(mockShow);


        //create out new viewing obj WITH show & episode obj
        viewing.setShow(mockShow);
        viewing.setEpisode(mockEpisode);
        List<Viewing> viewingResponse = asList(viewing);

        assertThat(viewingService.getViewingDataByUserID(22L), equalTo(viewingResponse));

    }
}
