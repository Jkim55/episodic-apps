package com.example.episodicshows.users;

import com.example.episodicshows.viewings.Viewing;
import com.example.episodicshows.viewings.ViewingRepository;
import com.example.episodicshows.viewings.ViewingResponse;
import com.example.episodicshows.viewings.ViewingService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}/recently-watched", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ViewingResponse> getAllViewingDataForAUser(@PathVariable("id") Long userId){
        return viewingService.getViewingDataByUserID(userId);
    }
}
