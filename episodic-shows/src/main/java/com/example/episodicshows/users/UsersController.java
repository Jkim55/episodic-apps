package com.example.episodicshows.users;

import com.example.episodicshows.shows.EpisodeService;
import com.example.episodicshows.shows.ShowService;
import com.example.episodicshows.viewings.Viewing;
import com.example.episodicshows.viewings.ViewingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final ViewingService viewingService;

    public UsersController(UserService userService, ViewingService viewingService) {
        assert userService != null;
        assert viewingService != null;
        this.userService = userService;
        this.viewingService = viewingService;
    }

    @GetMapping
    public Iterable<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createAUser(user);
    }

    @PatchMapping("/{id}/viewings")
    public ResponseEntity createAViewEntry(@PathVariable("id") Long userId, @RequestBody Viewing viewing){
        return viewingService.createAViewingEntry(userId, viewing);
    }

    @GetMapping("/{id}/recently-watched")
    public List<Viewing> getAllViewingDataForAUser(@PathVariable("id") Long userId){
        return viewingService.getViewingDataByUserID(userId);
    }
}
