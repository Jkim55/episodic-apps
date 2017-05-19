package com.example.episodicshows.users;

import com.example.episodicshows.users.User;
import com.example.episodicshows.users.UserRepository;
import com.example.episodicshows.users.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@WebMvcTest(UserService.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void itListsUsers() {
        User fakeUser1 = new User();
        fakeUser1.setEmail("jammin@jamaicatravel.com");

        User fakeUser2 = new User();
        fakeUser2.setEmail("dancin@arubatravel.com");

        userRepository.save(fakeUser1);
        userRepository.save(fakeUser2);

        Iterable<User> fakeGetResponse = userRepository.findAll();

        assertThat(userService.listAllUsers(), equalTo(fakeGetResponse));
    }

    @Test
    public void itCreatesAUser() {
        User fakeUser = new User();
        fakeUser.setEmail("jammin@jamaicatravel.com");

        User fakeResponse = userRepository.save(fakeUser);

        assertThat(userService.createAUser(fakeUser), equalTo(fakeResponse));
    }

}