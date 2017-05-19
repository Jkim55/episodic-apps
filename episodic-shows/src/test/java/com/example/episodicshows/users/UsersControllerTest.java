package com.example.episodicshows.users;

import com.example.episodicshows.users.User;
import com.example.episodicshows.users.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNull;
import org.junit.Before;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup(){
        userRepository.deleteAll();
    }

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

        MockHttpServletRequestBuilder request = get("/users")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", IsInstanceOf.instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", equalTo("JJ@email.com")))
                .andExpect(jsonPath("$.[1].id", IsInstanceOf.instanceOf(Number.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].email", equalTo("JT@email.com")));

        assertEquals(initialCount+2, userRepository.count());
    }

}
