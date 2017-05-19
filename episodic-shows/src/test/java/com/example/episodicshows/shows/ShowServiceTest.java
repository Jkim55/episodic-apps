package com.example.episodicshows.shows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowServiceTest {

    private ShowService showService;

    @Mock
    private ShowRepository showRepository;

    @Before
    public void init() {
        showService = new ShowService(showRepository);
    }

    @Test
    public void itListsShows() throws Exception {
        Show show1   = new Show();
        show1.setName("Master of None");

        Show show2 = new Show();
        show2.setName("The Mindy Project");

        when(showRepository.findAll()).thenReturn(Arrays.asList(show1, show2));

        assertThat(showService.listAllShows(), equalTo(Arrays.asList(show1, show2)));
    }

    @Test
    public void itCreatesAShow() throws Exception {
        Show show1 = new Show();
        show1.setName("Master of None");

        when(showRepository.save(any(Show.class))).thenReturn(show1);

        assertThat(showService.createAShow(show1), equalTo(show1));
    }
}